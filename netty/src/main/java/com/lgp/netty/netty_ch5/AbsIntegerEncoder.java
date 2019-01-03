package com.lgp.netty.netty_ch5;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @AUTHOR lgp
 * @DESCRIPTION 继承 MessageToMessageEncoder 用于编码消息到另外一种格式
 **/
public class AbsIntegerEncoder extends MessageToMessageEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        //2检查是否有足够的字节用于编码
        while (in.readableBytes() >= 4) {
            //3 读取下一个输入 ByteBuf 产出的 int 值，并计算绝对值
            int value = Math.abs(in.readInt());
            //4 写 int 到编码的消息 List
            out.add(value);
        }
    }
}