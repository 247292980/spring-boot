package com.lgp.netty.netty_ch4;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/29 10:02
 * @DESCRIPTION 使用 SSL/TLS 加密 Netty 程序
 **/
public class SslChannelInitializer extends ChannelInitializer<Channel> {

    private final SslContext context;
    private final boolean startTls;

    //1 使用构造函数来传递 SSLContext 用于使用(startTls 是否启用)
    public SslChannelInitializer(SslContext context, boolean client, boolean startTls) {
        this.context = context;
        this.startTls = startTls;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        //2 从 SslContext 获得一个新的 SslEngine 。给每个 SslHandler 实例使用一个新的 SslEngine
        SSLEngine engine = context.newEngine(ch.alloc());
        //3 设置 SslEngine 是 client(TRUE 或者是 server 模式
        engine.setUseClientMode(true);
        //4 添加 SslHandler 到 pipeline 作为第一个处理器
        ch.pipeline().addFirst("ssl", new SslHandler(engine, startTls));
    }
}
