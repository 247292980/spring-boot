package com.lgp.netty.netty_ch7;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import org.eclipse.jetty.npn.NextProtoNego;

import javax.net.ssl.SSLEngine;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/3 10:35
 * @DESCRIPTION 继承 ChannelInitializer 是一个简单的开始
 **/
public class SpdyChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final SslContext context;

    public SpdyChannelInitializer(SslContext context) {//2传递 SSLContext 用于创建 SSLEngine
        this.context = context;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //3新建 SSLEngine,用于新的管道和连接
        SSLEngine engine = context.newEngine(ch.alloc());
        //4 配置 SSLEngine 用于非客户端使用
        engine.setUseClientMode(false);
        //5 通过 NextProtoNego helper 类绑定 DefaultServerProvider 到 SSLEngine
        NextProtoNego.put(engine, new DefaultServerProvider());
        NextProtoNego.debug = true;
        //6 添加 SslHandler 到 ChannelPipeline 这将会在协议检测到时保存在 ChannelPipeline
        pipeline.addLast("sslHandler", new SslHandler(engine));
        //7 添加 DefaultSpyOrHttpChooser 到 ChannelPipeline 。这个实现将会监测协议。添加正确的 ChannelHandler 到 ChannelPipeline,并且移除自身
        pipeline.addLast("chooser", new DefaultSpdyOrHttpChooser(1024 * 1024, 1024 * 1024));
    }
}
