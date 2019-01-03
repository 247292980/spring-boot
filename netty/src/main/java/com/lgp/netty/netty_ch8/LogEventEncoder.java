package com.lgp.netty.netty_ch8;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/3 14:24
 * @DESCRIPTION
 **/
public class LogEventEncoder extends MessageToMessageEncoder<LogEvent> {
    private final InetSocketAddress remoteAddress;

    //1 LogEventEncoder 创建了 DatagramPacket 消息类发送到指定的 InetSocketAddress
    public LogEventEncoder(InetSocketAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, LogEvent logEvent, List<Object> out) throws Exception {
        //2 写文件名到 ByteBuf
        byte[] file = logEvent.getLogfile().getBytes(CharsetUtil.UTF_8);
        byte[] msg = logEvent.getMsg().getBytes(CharsetUtil.UTF_8);
        ByteBuf buf = channelHandlerContext.alloc().buffer(file.length + msg.length + 1);
        buf.writeBytes(file);
        //3 添加一个 SEPARATOR 分隔符
        buf.writeByte(LogEvent.SEPARATOR);
        //4 写一个日志消息到 ByteBu
        buf.writeBytes(msg);
        //5 添加新的 DatagramPacket 到出站消息
        out.add(new DatagramPacket(buf, remoteAddress));
    }
}
