package com.lgp.netty.netty_ch4;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/29 14:49
 * @DESCRIPTION
 **/
public class WebSocketServerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new HttpServerCodec(),
                //1 添加 HttpObjectAggregator 用于提供在握手时聚合 HttpRequest
                new HttpObjectAggregator(65536),
                //2 添加 WebSocketServerProtocolHandler 用于处理请求是发送到"/websocket." 端点，当升级完成后，它将会处理Ping, Pong 和 Close 帧
                new WebSocketServerProtocolHandler("/websocket"),
                //3 TextFrameHandler 将会处理 TextWebSocketFrames
                new TextFrameHandler(),
                //4 BinaryFrameHandler 将会处理 BinaryWebSocketFrames
                new BinaryFrameHandler(),
                //5 ContinuationFrameHandler 将会处理ContinuationWebSocketFrames
                new ContinuationFrameHandler());
    }

    public static final class TextFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
        @Override
        public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
            // Handle text frame
        }
    }

    public static final class BinaryFrameHandler extends SimpleChannelInboundHandler<BinaryWebSocketFrame> {
        @Override
        public void channelRead0(ChannelHandlerContext ctx, BinaryWebSocketFrame msg) throws Exception {
            // Handle binary frame
        }
    }

    public static final class ContinuationFrameHandler extends SimpleChannelInboundHandler<ContinuationWebSocketFrame> {
        @Override
        public void channelRead0(ChannelHandlerContext ctx, ContinuationWebSocketFrame msg) throws Exception {
            // Handle continuation frame
        }
    }
}
