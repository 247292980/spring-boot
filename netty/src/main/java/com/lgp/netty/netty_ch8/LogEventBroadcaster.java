package com.lgp.netty.netty_ch8;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/3 14:43
 * @DESCRIPTION
 **/
public class LogEventBroadcaster {
    private final Bootstrap bootstrap;
    private final File file;
    private final EventLoopGroup group;

    public LogEventBroadcaster(InetSocketAddress address, File file) {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        //1 导 NioDatagramChannel 。为了使用广播，我们设置 SO_BROADCAST 的 socket 选项
        bootstrap.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new LogEventEncoder(address));

        this.file = file;
    }

    public void run() throws IOException {
        //2 绑定管道。注意当使用 Datagram Channel 时，是没有连接的
        Channel ch = bootstrap.bind(0).syncUninterruptibly().channel();
        System.out.println("LogEventBroadcaster running");
        long pointer = 0;
        for (; ; ) {
            long len = file.length();
            if (len < pointer) {
                // file was reset //3 如果需要，可以设置文件的指针指向文件的最后字节
                pointer = len;
            } else if (len > pointer) {
                // Content was added
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                //4 设置当前文件的指针，这样不会把旧的发出去
                raf.seek(pointer);
                String line;
                while ((line = raf.readLine()) != null) {
                    //5 v写一个 LogEvent 到管道用于保存文件名和文件实体。(我们期望每个日志实体是一行长度)
                    ch.writeAndFlush(new LogEvent(null, -1, file.getAbsolutePath(), line));
                }
                //6 存储当前文件的位置，这样，我们可以稍后继续
                pointer = raf.getFilePointer();
                raf.close();
            }
            try {
                //7 睡 1 秒。如果其他中断退出循环就重新启动它。
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.interrupted();
                break;
            }
        }
    }

    public void stop() {
        group.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
//        if (args.length != 2) {
//            throw new IllegalArgumentException();
//        }
        //8 构造一个新的实例 LogEventBroadcaster 并启动它
          String input_path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "bigdata.txt";

        LogEventBroadcaster broadcaster = new LogEventBroadcaster(new InetSocketAddress("255.255.255.255", 44444), new File(input_path));
        try {
            broadcaster.run();
        } finally {
            broadcaster.stop();
        }
    }
}
