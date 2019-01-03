package com.lgp.netty.netty_ch3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/28 14:46
 * @DESCRIPTION 实现继承了 ByteToMessageDecode 用于将字节解码为消息
 **/
public class ToIntegerDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        //2 检查可读的字节是否至少有4个 ( int 是4个字节长度)
        if (in.readableBytes() >= 4) {
            //3 从入站 ByteBuf 读取 int ， 添加到解码消息的 List 中
            out.add(in.readInt());
        }
    }
}
