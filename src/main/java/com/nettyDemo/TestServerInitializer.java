package com.nettyDemo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        // Netty提供的处理器---其中的对象是多例的
        pipeline.addLast("httpServerCodec", new HttpServerCodec());

        // 自定义的处理器
        pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());
    }
}
