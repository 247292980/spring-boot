package com.lgp.netty.netty_ch4.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/29 11:43
 * @DESCRIPTION HTTP消息聚合, 使用了HTTP 压缩
 **/
public class HttpAggregatorInitializer2 extends ChannelInitializer<Channel> {

    private final boolean isClient;

    public HttpAggregatorInitializer2(boolean isClient) {
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (isClient) {
            //1 添加 HttpClientCodec
            pipeline.addLast("codec", new HttpClientCodec());
            //2添加 HttpContentDecompressor 用于处理来自服务器的压缩的内容
            pipeline.addLast("decompressor", new HttpContentDecompressor());
        } else {
            //3 添加 HttpServerCodec 作为我们是 server 模式时
            pipeline.addLast("codec", new HttpServerCodec());
            //4 HttpContentCompressor 用于压缩来自 client 支持的 HttpContentCompressor
            pipeline.addLast("compressor", new HttpContentCompressor());
        }
    }
}