package com.common.server.istudy.hessian.client.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Controller
@RequestMapping("user/applepay")
public class ApplePayController2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplePayController2.class);
    // 购买凭证验证地址
    private static final String verifyReceiptUrl = "https://buy.itunes.apple.com/verifyReceipt";
    // 测试的购买凭证验证地址
    private static final String verifyReceiptUrlSandbox = "https://sandbox.itunes.apple.com/verifyReceipt";

    private static int requestCounts = 0;

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

//    @Autowired
//    private IOrderService orderService;

    /**
     * 接收iOS端发过来的购买凭证
     *
     */
    @RequestMapping(value="/iap", method = RequestMethod.POST)
    @ResponseBody
    private String sendHttpsCoon(@RequestParam(value="uid") Long uid,
                                     @RequestParam(value="receipt", required=true)String receipt,
                                      @RequestParam(value="chooseEnv", required=true)Boolean chooseEnv) {
        if ( null == receipt ) {
            return "";
        } else {
            String url = chooseEnv ? verifyReceiptUrl : verifyReceiptUrlSandbox;
            try {
                // 设置SSLContext
                SSLContext ssl = SSLContext.getInstance("SSL");
                ssl.init(null, new TrustManager[] { myX509TrustManager }, null);

                // 打开连接
                HttpsURLConnection conn = (HttpsURLConnection) new URL(url).openConnection();
                // 设置套接工厂
                conn.setSSLSocketFactory(ssl.getSocketFactory());
                // 加入数据
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-type", "application/json");
                conn.setRequestProperty("Proxy-Connection", "Keep-Alive");
//				conn.setDoInput(true);
                conn.setDoOutput(true);
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
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                //苹果返回的string
                String stringadsf = sb.toString();
                conn.getInputStream().close();

                JSONObject job = JSONObject.parseObject(stringadsf);// App Store的返回值
                if (job.getString("status").equals("0")) {
                    // 跟苹果验证有返回结果--验证成功
                    JSONObject code = JSONObject.parseObject(job.getString("receipt"));
                    String creation_date_ms = code.getString("receipt_creation_date_ms"); // receipt创建日期(ms)
                    JSONArray jsonArray = (JSONArray) code.get("in_app");// 订单列表
                    JSONObject targetOrder = null;
                    if (1 == jsonArray.size()) {
                        // 订单列表为一个,直接取出
                        targetOrder = jsonArray.getJSONObject(0);
                    } else{
                        // 订单列表为多个,根据支付订单创建时间戳获取本次订单 
                        LOGGER.error("苹果--->订单列表为多个 " );
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JSONObject orderItem = jsonArray.getJSONObject(i);
                            if (orderItem.getString("purchase_date_ms").equals(creation_date_ms)) {
                                targetOrder = orderItem;
                            }
                        }
                    }
                    if (null == targetOrder) {
                        LOGGER.error("验证结果中不存在订单信息 " );
                        return ("验证结果中不存在订单信息-1");
                    } else {
                        //获取apple_product_id
                        String product_id = targetOrder.getString("product_id");
                        // String money = product_id.substring(4, product_id.length());
                        String transaction_id = targetOrder.getString("transaction_id");// transaction_id交易号
                        //将凭证解析出的购买信息和本地服务端作对比
                        //更新order数据库
                        String price = "0";
                        String goodsName = "商品名称";


//                        OrderBean bean = new OrderBean();
//                        bean.setUserId(uid);
//                        bean.setRealFee(new BigDecimal(price));
//                        bean.setTotalProductFee(new BigDecimal(price));
//                        bean.setGoodsName(goodsName);
//                        bean.setPayType((byte) 1);//表示ApplePay
//                        bean.setCode(transaction_id);
//                        requestCounts = 0;
//                        return orderService.addAndFinishOrder(bean);
                    }

                } else if (job.getString("status").equals("21007")) {
                    LOGGER.error("21007: 收据信息是测试用（sandbox），但却被发送到正式环境中验证 " );
                    return ("21007验证支付信息失败-1");
                }  else if (job.getString("status").equals("21008") && requestCounts < 5) {
                    LOGGER.error("21008 receipt是生产receipt，但却发送至Sandbox环境的验证服务 " );
                    requestCounts ++;
                    // 重新请求服务器,requestCounts 计数,使用生产环境的url验证地址(基于ios端无法判断传入 chooseEnv参数)
                    return sendHttpsCoon(uid, receipt, true);


                    //return ("21008验证支付信息失败-1", 500);
                } else {
                    LOGGER.error("苹果没有返回的验证信息 " );
                    return ("验证支付信息失败-2");
                }
            } catch (Exception e) {
                LOGGER.error("苹果返回验证信息 " );
                return ("验证支付信息失败-3");
            }
        }
        return "失败";
    }

}
