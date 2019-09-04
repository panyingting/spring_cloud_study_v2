package com.commmon.server.istudy.netty.serialize.marshalling.sub;

import com.commmon.server.istudy.netty.bean.RequestBean;
import com.commmon.server.istudy.netty.bean.ResponseBean;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
//import com.commmon.server.istudy.istudy.netty.bean.RequestBean;
//import com.commmon.server.istudy.istudy.netty.bean.ResponseBean;


/**
 * @ 创建人 贾红平
 * @ 创建时间 2018/6/24
 * @ 功能描述 服务端处理业务的handler
 */
public class RequestServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 服务端连接到客户端戳发该函数调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端连接到客户度:" + ctx.channel().remoteAddress());
        ctx.fireChannelActive();
    }

    /**
     * 服务端接收客户端的数据 并处理完之后写入处理结果给客户端
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("读取数据信息：" + msg);
        RequestBean requestBean = (RequestBean) msg;
        if (requestBean.getUserName().equals("jhp")) {
            System.out.println("Service accept client subscrib req : ["
                    + requestBean.toString() + "]");
            ctx.writeAndFlush(genertorResponseBean(requestBean.getSubReqID()));
        }
    }

    /**
     * 构建响应信息
     *
     * @param id
     * @return
     */
    private ResponseBean genertorResponseBean(int id) {
        ResponseBean resp = new ResponseBean();
        resp.setSubReqID(id);
        resp.setRespCode(0);
        resp.setDesc("这本书籍是介绍和ai相关的实践项目集合");
        return resp;
    }

    /**
     * 发生异常 打印信息 关闭所有链路
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常......");
        cause.printStackTrace();
        ctx.close();
    }

}
// ————————————————
//         版权声明：本文为CSDN博主「贾红平」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
//         原文链接：https://blog.csdn.net/qq_18603599/article/details/80768403
