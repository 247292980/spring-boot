package com.lgp.netty.netty_ch7;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import java.net.InetSocketAddress;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/3 10:59
 * @DESCRIPTION
 **/
public class SpdyServer {
    //1 构建新的 NioEventLoopGroup 用于处理 I/O
    private final NioEventLoopGroup group = new NioEventLoopGroup();
    private final SslContext context;
    private Channel channel;
    //2 传递 SSLContext 用于加密
    public SpdyServer(SslContext context) {
        this.context = context;
    }

    public ChannelFuture start(InetSocketAddress address) {
        //3 新建 ServerBootstrap 用于配置服务器
        ServerBootstrap bootstrap = new ServerBootstrap();
        //4 配置 ServerBootstrap
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new SpdyChannelInitializer(context));
        //5 绑定服务器用于接收指定地址的连接
        ChannelFuture future = bootstrap.bind(address);
        future.syncUninterruptibly();
        channel = future.channel();
        return future;
    }
    //6 销毁服务器，用于关闭管道和 NioEventLoopGroup
    public void destroy() {
        if (channel != null) {
            channel.close();
        }
        group.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
//        if (args.length != 1) {
//            System.err.println("Please give port as argument");
//            System.exit(1);
//        }
//        int port = Integer.parseInt(args[0]);
        int port = 33333;

        SelfSignedCertificate cert = new SelfSignedCertificate();
        //7 从 BogusSslContextFactory 获取 SSLContext 。这是一个虚拟实现进行测试。真正的实现将为 SslContext 配置适当的密钥存储库。
        SslContext context = SslContext.newServerContext(cert.certificate(), cert.privateKey());
        final SpdyServer endpoint = new SpdyServer(context);
        ChannelFuture future = endpoint.start(new InetSocketAddress(port));

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                endpoint.destroy();
            }
        });
        future.channel().closeFuture().syncUninterruptibly();
    }
}
