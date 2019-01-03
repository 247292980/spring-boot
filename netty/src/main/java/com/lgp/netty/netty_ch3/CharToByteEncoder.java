package com.lgp.netty.netty_ch3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/28 17:56
 * @DESCRIPTION 继承 MessageToByteEncoder
 **/
public class CharToByteEncoder extends MessageToByteEncoder<Character> {

    @Override
    public void encode(ChannelHandlerContext ctx, Character msg, ByteBuf out) throws Exception {
        //2 写 char 到 ByteBuf
        out.writeChar(msg);
    }
}