package com.liu.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ChannelInboundHandlerAdapter实现自ChannelInboundHandler
 * ChannelInboundHandler提供了不同的事件处理方法可通过重写来自定义处理方式
 */
class ServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 接受客户端发送的消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //类似JDK中的ByteBuffer对象，不过它提供了更加强大和灵活的功能
        ByteBuf buf = (ByteBuf) msg;
        //通过readableBytes()方法获取缓冲区可读的字节数
        byte[] req = new byte[buf.readableBytes()];
        //将缓冲区中的字节数组复制到新建的byte数组中
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("Server receive: " + body);
        //获得ByteBuf类型的数据
        ByteBuf resp = Unpooled.copiedBuffer("Server message".getBytes());
        //向客户端发送消息，不直接将消息写入SocketChannel中，只是把待发送的消息放到发送缓存数组中，
        //再通过调用flush方法将缓冲区中的消息全部写到SocketChannel中
        ctx.write(resp);
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将消息发送队列中的消息写入到SocketChannel中发送给对方
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //当发生异常时释放资源
        ctx.close();
    }
}

