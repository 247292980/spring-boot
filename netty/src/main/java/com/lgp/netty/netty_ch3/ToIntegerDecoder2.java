package com.lgp.netty.netty_ch3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/28 17:13
 * @DESCRIPTION 实现继承自 ReplayingDecoder 用于将字节解码为消息
 * <p>
 * ReplayingDecoder 是 byte-to-message 解码的一种特殊的抽象基类，读取缓冲区的数据之前需要检查缓冲区是否有足够的字节，
 * 使用ReplayingDecoder就无需自己检查；若ByteBuf中有足够的字节，则会正常读取；若没有足够的字节则会停止解码。
 **/
public class ToIntegerDecoder2 extends ReplayingDecoder<Void> {
    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //2 从入站 ByteBuf 读取整型，并添加到解码消息的 List 中
        out.add(in.readInt());
    }
}
