package com.lgp.netty.netty_ch5;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/29 17:31
 * @DESCRIPTION 继承 ByteToMessageDecoder 用来处理入站的字节并将他们解码为消息
 **/
public class FixedLengthFrameDecoder extends ByteToMessageDecoder {
    private final int frameLength;

    public FixedLengthFrameDecoder(int frameLength) {
        //2 指定产出的帧的长度
        if (frameLength <= 0) {
            throw new IllegalArgumentException("frameLength must be a positive integer: " + frameLength);
        }
        this.frameLength = frameLength;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //3 检查是否有足够的字节用于读到下个帧
        if (in.readableBytes() >= frameLength) {
            //4 从 ByteBuf 读取新帧
            ByteBuf buf = in.readBytes(frameLength);
            //5 添加帧到解码好的消息 List
            out.add(buf);
        }
    }
}
