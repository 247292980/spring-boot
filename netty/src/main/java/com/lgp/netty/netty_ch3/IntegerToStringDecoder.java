package com.lgp.netty.netty_ch3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/28 17:15
 * @DESCRIPTION 实现继承自 MessageToMessageDecoder
 **/
public class IntegerToStringDecoder extends MessageToMessageDecoder<Integer> {

    @Override
    public void decode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
        //2通过 String.valueOf() 转换 Integer 消息字符串
        out.add(String.valueOf(msg));
    }
}