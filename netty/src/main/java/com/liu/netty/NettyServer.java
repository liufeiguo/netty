package com.liu.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if(args !=null && args.length>0) {
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e) {
                //采用默认值
            }
        }
        new NettyServer().bind(port);
    }

    public void bind(int port) throws Exception{
        //NioEventLoopGroup是一个线程组，包含了一组NIO线程，专门用于网络事件的处理，
        //实际上他们就是Reactor线程组
        //bossGroup仅接收客户端连接，不做复杂的逻辑处理，为了尽可能减少资源的占用，取值越小越好
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //用于进行SocketChannel的网络读写
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //是Netty用于启动NIO服务端的辅助启动类，目的是降低服务端的开发复杂度
            ServerBootstrap b = new ServerBootstrap();
            //配置NIO服务端
            b.group(bossGroup, workerGroup)
                    //指定使用NioServerSocketChannel产生一个Channel用来接收连接，他的功能对应于JDK
                    // NIO类库中的ServerSocketChannel类。
                    .channel(NioServerSocketChannel.class)
                    //BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，
                    // 用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，
                    // Java将使用默认值50。
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    //绑定I/O事件处理类，作用类似于Reactor模式中的Handler类，主要用于处理网络I/O事件
                    .childHandler(new ChildChannelHandler());
            //绑定端口，同步等待绑定操作完成，完成后返回一个ChannelFuture,用于异步操作的通知回调
            ChannelFuture f = b.bind(port).sync();
            //等待服务端监听端口关闭之后才退出main函数
            f.channel().closeFuture().sync();
        } finally {
            //退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        protected void initChannel(SocketChannel arg0) throws Exception {
            arg0.pipeline().addLast(new ServerHandler());
        }
    }

}

