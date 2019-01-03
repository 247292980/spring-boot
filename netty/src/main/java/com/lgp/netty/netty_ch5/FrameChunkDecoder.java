package com.lgp.netty.netty_ch5;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/2 16:16
 * @DESCRIPTION   继承 ByteToMessageDecoder 用于解码入站字节到消息
 **/
public class FrameChunkDecoder extends ByteToMessageDecoder {

    private final int maxFrameSize;

    public FrameChunkDecoder(int maxFrameSize) {
        this.maxFrameSize = maxFrameSize;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //2 指定最大需要的帧产生的体积
        int readableBytes = in.readableBytes();
        if (readableBytes > maxFrameSize) {
            // discard the bytes   //3 如果帧太大就丢弃并抛出一个 TooLongFrameException 异常
            in.clear();
            throw new TooLongFrameException();
        }
        //4 从 ByteBuf 读到新帧
        ByteBuf buf = in.readBytes(readableBytes);
        //5 添加帧到解码消息 List
        out.add(buf);
    }
}
