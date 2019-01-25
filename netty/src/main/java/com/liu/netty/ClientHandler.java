package com.liu.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

class ClientHandler extends ChannelInboundHandlerAdapter {

    private final ByteBuf msg;

    public ClientHandler() {
        byte[] req = "Client message".getBytes();
        msg = Unpooled.buffer(req.length);
        msg.writeBytes(req);
    }

    /**
     * 当客户端和服务端TCP链路建立成功之后，Netty的NIO线程会调用此方法
     * @param ctx
     */
    public void channelActive(ChannelHandlerContext ctx) {
        //发送消息到服务端
        ctx.writeAndFlush(msg);
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "utf-8");
        System.out.println("Client receive :" + body);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
/*--------------------- 
作者：JasonPhui 
来源：CSDN 
原文：https://blog.csdn.net/PH15045125/article/details/83692409 
版权声明：本文为博主原创文章，转载请附上博文链接！*/
