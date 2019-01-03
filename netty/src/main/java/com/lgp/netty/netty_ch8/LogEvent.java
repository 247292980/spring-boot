package com.lgp.netty.netty_ch8;

import java.net.InetSocketAddress;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/3 11:43
 * @DESCRIPTION
 **/
public final class LogEvent {
    public static final byte SEPARATOR = (byte) ':';

    private final InetSocketAddress source;
    private final String logfile;
    private final String msg;
    private final long received;

    public LogEvent(String logfile, String msg) { //1 构造器用于出站消息
        this(null, -1, logfile, msg);
    }

    public LogEvent(InetSocketAddress source, long received, String logfile, String msg) {  //2 构造器用于入站消息
        this.source = source;
        this.received = received;
        this.logfile = logfile;
        this.msg = msg;
    }

    public InetSocketAddress getSource() { //3 返回发送 LogEvent 的 InetSocketAddress 的资源
        return source;
    }

    public String getLogfile() { //4 返回用于发送 LogEvent 的日志文件的名称
        return logfile;
    }

    public String getMsg() {  //5 返回消息的内容
        return msg;
    }

    public long getReceivedTimestamp() {  //6 返回 LogEvent 接收到的时间
        return received;
    }
}
