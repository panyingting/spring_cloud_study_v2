package com.common.server.istudy.hessian.client.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/pay")
public class ApplePayController {

    // 购买凭证验证地址
    private static final String certificateUrl = "https://buy.itunes.apple.com/verifyReceipt";
    // 测试的购买凭证验证地址
    private static final String certificateUrlTest = "https://sandbox.itunes.apple.com/verifyReceipt";

    /**
     * 重写X509TrustManager
     */
    private static TrustManager myX509TrustManager = new X509TrustManager() {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    };

    /**
     * 接收iOS端发过来的购买凭证
     */
    @ResponseBody
    @RequestMapping("/iap")
    public void sendHttpsCoon(long uid, String receipt, boolean chooseEnv)
    {
        if( 0 == uid || null == receipt)
        {
            System.out.println("请求参数错误");
        }
        else
        {
            String url = chooseEnv == true ? certificateUrl : certificateUrlTest;
            try
            {
                // 设置SSLContext
                SSLContext ssl = SSLContext.getInstance("SSL");
                ssl.init(null, new TrustManager[] { myX509TrustManager }, null);

                // 打开连接
                HttpsURLConnection conn = (HttpsURLConnection) new URL(url).openConnection();
                // 设置套接工厂
                conn.setSSLSocketFactory(ssl.getSocketFactory());
                // 加入数据
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-type", "application/json");
                conn.setRequestProperty("Proxy-Connection", "Keep-Alive");
                JSONObject obj = new JSONObject();
                obj.put("receipt-data", receipt);
                // 获取输出流
                BufferedOutputStream buffOutStr = new BufferedOutputStream(conn.getOutputStream());
                buffOutStr.write(obj.toString().getBytes());
                buffOutStr.flush();
                buffOutStr.close();
                // 获取输入流
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String line = null;
                StringBuffer sb = new StringBuffer();
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line);
                }
                String stringadsf = sb.toString();
                conn.getInputStream().close();
                System.err.println("苹果返回的验证信息-->  " + stringadsf);

                JSONObject job = JSONObject.parseObject(stringadsf);// App Store的返回值
                String money = "";
                String orderId = "";
                if(job.getString("status").equals("0"))// 跟苹果验证有返回结果--验证成功
                {
                    JSONObject code = JSONObject.parseObject(job.getString("receipt"));
                    String creation_date_ms = code.getString("receipt_creation_date_ms"); // receipt创建日期(ms)
                    JSONArray jsonArray = (JSONArray) code.get("in_app");// 订单列表
                    JSONObject targetOrder = null;
                    if(1 == jsonArray.size())//订单列表为一个,直接取出
                    {
                        targetOrder = jsonArray.getJSONObject(0);
                    }
                    else//订单列表为多个,根据支付订单创建时间戳获取本次订单
                    {
                        for (int i = 0; i < jsonArray.size(); i++)
                        {
                            JSONObject orderItem = jsonArray.getJSONObject(i);
                            if(orderItem.getString("purchase_date_ms").equals(creation_date_ms))
                            {
                                targetOrder = orderItem;
                            }
                        }
                    }
                    if(null == targetOrder)
                    {
                        System.out.println("验证结果中不存在订单信息");
                    }
                    else
                    {
                        String product_id = targetOrder.getString("product_id");
                        money = product_id.substring(4, product_id.length());
                        orderId = uid + "_" + targetOrder.getString("transaction_id");// transaction_id交易号
                    }

                }
                else if(job.getString("status").equals("21007"))
                {
                    System.out.println("21007: 收据信息是测试用（sandbox），但却被发送到产品环境中验证");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
/*
————————————————
        版权声明：本文为CSDN博主「LLLiiv」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
        原文链接：https://blog.csdn.net/llllvvv/article/details/76021841 */