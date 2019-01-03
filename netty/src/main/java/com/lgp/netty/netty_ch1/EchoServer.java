package com.lgp.netty.netty_ch1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @AUTHOR lgp
 * @DATE 2018/10/24 16:28
 * @DESCRIPTION
 **/
public class EchoServer {
    public final int port;

    public EchoServer(int port) {
        this.port = port;
    }


    public static void main(String[] args) throws Exception {
        //2启动服务器
        new EchoServer(1234).start();
    }

    public void start() throws Exception {
        //3 创建同步非阻塞事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            //4 新建服务器引导类
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    //5 指定服务器的传输连接类型
                    .channel(NioServerSocketChannel.class)
                    //6 根据端口 设置socket的端口
                    .localAddress(new InetSocketAddress(port))
                    //7 添加处理器 我们自定义的EchoServerHandler ps.此处可以添加多个处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            //8 绑定服务器，直到服务器关闭 并得到ChannelFuture，ChannelFuture是netty的一个回调，不管是否成功调用
            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
            //9 当服务器关闭时，关闭频道
            f.channel().closeFuture().sync();
        } finally {
            //10 同步非阻塞事件循环组关闭并释放所有资源
            group.shutdownGracefully().sync();
        }
    }

}
