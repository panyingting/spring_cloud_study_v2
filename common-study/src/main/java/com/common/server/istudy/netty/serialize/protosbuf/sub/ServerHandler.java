package com.common.server.istudy.netty.serialize.protosbuf.sub;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import com.common.server.istudy.netty.bean.AttachmentRequest;
import com.common.server.istudy.netty.bean.ResponseBean;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    //channelActive()：客户端连接服务器后被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channel active... ");
    }

    //从服务器接收到数据后调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        try {
            AttachmentRequest buf = (AttachmentRequest) msg;

            System.out.println("Server :" + buf);
            String response = "进行返回给客户端的响应：" + buf.getName() + "读取成功，长度为：" + buf.getAttachment().length;
            System.out.println(response);
            ResponseBean rep = new ResponseBean();

            rep.setSubReqID(Integer.parseInt(buf.getId()));
            rep.setDesc("服务端回复信息");
            rep.setRespCode(200);

            ctx.writeAndFlush(rep);
        } finally {

            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
            throws Exception {
        System.out.println("读完了");
        ctx.flush();
    }

    //	发生异常时被调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable t)
            throws Exception {
        t.printStackTrace();
        ctx.close();
    }

}

// ————————————————
//         版权声明：本文为CSDN博主「青鸟&飞鱼」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
//         原文链接：https://blog.csdn.net/u014401141/article/details/79224789
