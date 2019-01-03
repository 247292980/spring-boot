package com.lgp.netty.netty_ch8;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/3 14:51
 * @DESCRIPTION
 **/
public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket> {
    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket datagramPacket, List<Object> out) throws Exception {
        //1 获取 DatagramPacket 中数据的引用
        ByteBuf data = datagramPacket.content();
        //2 获取 SEPARATOR 的索引
        int i = data.indexOf(0, data.readableBytes(), LogEvent.SEPARATOR);
        //3 从数据中读取文件名
        String filename = data.slice(0, i).toString(CharsetUtil.UTF_8);
        //4 读取数据中的日志消息
        String logMsg = data.slice(i + 1, data.readableBytes()).toString(CharsetUtil.UTF_8);
        //5 构造新的 LogEvent 对象并将其添加到列表中
        LogEvent event = new LogEvent(datagramPacket.recipient(), System.currentTimeMillis(), filename, logMsg);
        out.add(event);
    }
}
