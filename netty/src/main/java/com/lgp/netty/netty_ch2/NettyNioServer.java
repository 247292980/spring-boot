package com.lgp.netty.netty_ch2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @AUTHOR lgp
 * @DESC Reactor 多线程模型
 * https://blog.csdn.net/Lonely_Devil/article/details/69264438
 * https://www.cnblogs.com/ivaneye/p/5731432.html 建议去这个网站看图理解 Reactor 多线程模型和 Reactor 主从多线程模型
 * https://segmentfault.com/a/1190000007283053
 *
 * 1.为什么不是Reactor 主从多线程模型？
 * Netty 的服务器端的 acceptor 阶段, 没有使用到多线程
 * 因为多线程分配channel的时候，一个channel在一个acceptor里面是已连接的并保存，那么下次分配到另一个acceptor里面，会再次保存，浪费资源。
 * 还存在acceptor不是多线程实现，进入了一个acceptor之后，过了很久才会处理另一个acceptor的channel，那么对于进入到第二个acceptor的请求则会等待很久，甚至永不激活
 **/
public class NettyNioServer {
    public static void main(String[] args) throws Exception {
        server(1234);
    }

    public static void server(int port) throws Exception {
        final ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi!\r\n", Charset.forName("UTF-8")));
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //            1 创建一个 ServerBootstrap 服务器引导类
            ServerBootstrap b = new ServerBootstrap();
            //          2 使用 NioEventLoopGroup 允许非阻塞模式（NIO）ps.Reactor 多线程模型理解
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    //3 指定 ChannelInitializer 将给每个接受的连接调用
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            //4 添加的 ChannelHandler 拦截事件，并允许他们作出反应
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    //5 写信息到客户端，并添加 ChannelFutureListener 当一旦消息写入就关闭连接
                                    ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);
                                }
                            });
                        }
                    });
            //6 绑定服务器来接受连接
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } finally {
            //7 释放所有资源
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }
    }
}
