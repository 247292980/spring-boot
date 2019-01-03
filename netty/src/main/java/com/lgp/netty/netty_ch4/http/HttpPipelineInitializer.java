package com.lgp.netty.netty_ch4.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/29 11:22
 * @DESCRIPTION ChannelPipeline 的初始化
 **/
public class HttpPipelineInitializer extends ChannelInitializer<Channel> {

    private final boolean client;

    public HttpPipelineInitializer(boolean client) {
        this.client = client;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (client) {
            //1 添加 HttpResponseDecoder 用于处理来自 server 响应
            pipeline.addLast("decoder", new HttpResponseDecoder());
            //2 添加 HttpRequestEncoder 用于发送请求到 server
            pipeline.addLast("encoder", new HttpRequestEncoder());
        } else {
            //3 添加 HttpRequestDecoder 用于接收来自 client 的请求
            pipeline.addLast("decoder", new HttpRequestDecoder());
            //4 添加 HttpResponseEncoder 用来发送响应给 client
            pipeline.addLast("encoder", new HttpResponseEncoder());
        }
    }
}