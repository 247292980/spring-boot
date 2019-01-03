package com.lgp.netty.netty_ch4.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/29 11:47
 * @DESCRIPTION 启用https
 **/
public class HttpsCodecInitializer extends ChannelInitializer<Channel> {

    private final SslContext context;
    private final boolean client;

    public HttpsCodecInitializer(SslContext context, boolean client) {
        this.context = context;
        this.client = client;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        SSLEngine engine = context.newEngine(ch.alloc());
        //1 添加 SslHandler 到 pipeline 来启用 HTTPS
        pipeline.addFirst("ssl", new SslHandler(engine));

        if (client) {
            //2 添加 HttpClientCodec
            pipeline.addLast("codec", new HttpClientCodec());
        } else {
            //3  添加 HttpServerCodec ，如果是 server 模式的话
            pipeline.addLast("codec", new HttpServerCodec());
        }
    }
}
