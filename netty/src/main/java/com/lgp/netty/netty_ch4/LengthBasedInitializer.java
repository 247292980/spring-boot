package com.lgp.netty.netty_ch4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/29 15:52
 * @DESCRIPTION
 **/
public class LengthBasedInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //1 添加一个 LengthFieldBasedFrameDecoder ,用于提取基于帧编码长度8个字节的帧。
        pipeline.addLast(new LengthFieldBasedFrameDecoder(65 * 1024, 0, 8));
        //2 添加一个 FrameHandler 用来处理每帧
        pipeline.addLast(new FrameHandler());
    }

    public static final class FrameHandler extends SimpleChannelInboundHandler<ByteBuf> {
        @Override
        public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
            // Do something with the frame //3 处理帧数据
        }
    }
}
