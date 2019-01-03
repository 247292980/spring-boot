package com.lgp.netty.netty_ch3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/28 17:52
 * @DESCRIPTION 继承 ByteToMessageDecoder
 **/
public class ByteToCharDecoder extends ByteToMessageDecoder {

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //2 写 char 到 MessageBuf
        if (in.readableBytes() >= 2) {
            out.add(in.readChar());
        }
    }
}
