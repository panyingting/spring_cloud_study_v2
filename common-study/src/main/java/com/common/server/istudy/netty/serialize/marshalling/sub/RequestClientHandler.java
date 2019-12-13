package com.common.server.istudy.netty.serialize.marshalling.sub;

import com.common.server.istudy.netty.bean.RequestBean;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Random;

/**
 * @ 创建人 贾红平
 * @ 创建时间 2018/6/24
 * @ 功能描述 client handler
 */
public class RequestClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端连接上服务端之后会调用此方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 5; i++) {
            RequestBean bean = generatorRequestBean(i);
            System.out.println("Client really send data to Server:" + bean);
            TestAnnotation testAnnotation = RequestClientHandler.class.getMethod("f").getAnnotation(TestAnnotation.class);
//            ctx.writeAndFlush(bean);
            ctx.writeAndFlush(testAnnotation);
        }
    }

    /**
     * 构建要发送给服务端的数据
     *
     * @param i
     * @return
     */
    private RequestBean generatorRequestBean(int i) {
        String[] unames = {"jhp", "bruce", "lyb"};
        RequestBean req = new RequestBean();
        req.setAddress("南京市浦口区天润城2街区47栋1单元");
        req.setPhoneNumber("13803456728");
        req.setProductName("人工只能AI实践");
        req.setSubReqID(i);
        req.setUserName(unames[new Random().nextInt(3)]);
        return req;
    }

    /**
     * 客户端读取服务端写入的数据
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println();
        System.out.println("Receive server response : [" + msg + "]");
    }

    /**
     * 连接或者发送读取数据出现异常
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @TestAnnotation()
    public static void f(){}
}
// ————————————————
//         版权声明：本文为CSDN博主「贾红平」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
//         原文链接：https://blog.csdn.net/qq_18603599/article/details/80768403
