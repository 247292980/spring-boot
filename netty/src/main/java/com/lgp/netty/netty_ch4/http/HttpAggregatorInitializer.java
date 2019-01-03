package com.lgp.netty.netty_ch4.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/29 11:31
 * @DESCRIPTION HTTP消息聚合
 **/
public class HttpAggregatorInitializer extends ChannelInitializer<Channel> {

    private final boolean client;

    public HttpAggregatorInitializer(boolean client) {
        this.client = client;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (client) {
            //1 添加 HttpClientCodec
            pipeline.addLast("codec", new HttpClientCodec());
        } else {
            //2 添加 HttpServerCodec 作为我们是 server 模式时
            pipeline.addLast("codec", new HttpServerCodec());
        }
        //3 添加 HttpObjectAggregator 到 ChannelPipeline, 使用最大消息值是 512kb
        pipeline.addLast("aggegator", new HttpObjectAggregator(512 * 1024));
    }
}
