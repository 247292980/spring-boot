package com.lgp.netty.netty_ch3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/28 17:23
 * @DESCRIPTION 实现继承自 MessageToByteEncoder
 **/
public class ShortToByteEncoder extends MessageToByteEncoder<Short> {
    @Override
    public void encode(ChannelHandlerContext ctx, Short msg, ByteBuf out) throws Exception {
        //2写 Short 到 ByteBuf
        out.writeShort(msg);
    }
}