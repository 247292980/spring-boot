package com.lgp.netty.netty_ch3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @AUTHOR lgp
 * @DESCRIPTION 实现继承自 MessageToMessageEncoder
 **/
public class IntegerToStringEncoder extends MessageToMessageEncoder<Integer> {

    @Override
    public void encode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
        //2 转 Integer 为 String，并添加到 MessageBuf
        out.add(String.valueOf(msg));
    }
}