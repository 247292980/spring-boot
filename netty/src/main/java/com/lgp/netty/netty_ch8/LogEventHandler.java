package com.lgp.netty.netty_ch8;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/3 14:53
 * @DESCRIPTION 继承 SimpleChannelInboundHandler 用于处理 LogEvent 消息
 **/
public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //2 在异常时，输出消息并关闭 channel
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead0(ChannelHandlerContext channelHandlerContext, LogEvent event) throws Exception {
        //3 建立一个 StringBuilder 并构建输出
        StringBuilder builder = new StringBuilder();
        builder.append(event.getReceivedTimestamp());
        builder.append(" [");
        builder.append(event.getSource().toString());
        builder.append("] [");
        builder.append(event.getLogfile());
        builder.append("] : ");
        builder.append(event.getMsg());
        //4 打印出 LogEvent 的数据
        System.out.println(builder.toString());
    }
}
