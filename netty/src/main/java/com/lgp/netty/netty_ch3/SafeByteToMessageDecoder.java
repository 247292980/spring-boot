package com.lgp.netty.netty_ch3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/28 17:20
 * @DESCRIPTION 实现继承 ByteToMessageDecoder 来将字节解码为消息
 **/
public class SafeByteToMessageDecoder extends ByteToMessageDecoder {
    private static final int MAX_FRAME_SIZE = 1024;

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int readable = in.readableBytes();
        //2 检测缓冲区数据是否大于 MAX_FRAME_SIZE
        if (readable > MAX_FRAME_SIZE) {
            //3 忽略所有可读的字节，并抛出 TooLongFrameException 来通知 ChannelPipeline 中的 ChannelHandler 这个帧数据超长
            in.skipBytes(readable);
            throw new TooLongFrameException("Frame too big!");
        }
        // do something
    }
}
