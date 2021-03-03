package com.nettyDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {
    public static void main(String[] args) throws Exception {

        // 定义事件循环组[死循环]--控制流，多线程
        // 不断接受连接
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // 死循环--类似于Tomcat服务器
        // 实际对连接进行处理
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            // Netty提供的比较方便的启动服务端的启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            // 提供自己的服务端初始化器--通过反射创建NIO--定义子处理器
            serverBootstrap.group(bossGroup, workGroup).
                    channel(NioServerSocketChannel.class).childHandler(new TestServerInitializer());

            // 绑定端口---sync()同步
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();

            // 关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 优雅关闭
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
