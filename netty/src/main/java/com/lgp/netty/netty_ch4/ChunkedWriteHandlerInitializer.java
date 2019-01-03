package com.lgp.netty.netty_ch4;

import io.netty.channel.*;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedStream;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.io.File;
import java.io.FileInputStream;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/29 16:06
 * @DESCRIPTION 编写大型数据
 **/
public class ChunkedWriteHandlerInitializer extends ChannelInitializer<Channel> {
    private final File file;
    private final SslContext sslCtx;

    public ChunkedWriteHandlerInitializer(File file, SslContext sslCtx) {
        this.file = file;
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //1 添加 SslHandler 到 ChannelPipeline.
        pipeline.addLast(new SslHandler(sslCtx.newEngine(ch.alloc())));
        //2 添加 ChunkedWriteHandler 用来处理作为 ChunkedInput 传进的数据
        pipeline.addLast(new ChunkedWriteHandler());
        //3 当连接建立时，WriteStreamHandler 开始写文件的内容
        pipeline.addLast(new WriteStreamHandler());
    }
    //4 当连接建立时，channelActive() 触发使用 ChunkedInput 来写文件的内容 (插图显示了 FileInputStream;也可以使用任何 InputStream )
    public final class WriteStreamHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            ctx.writeAndFlush(new ChunkedStream(new FileInputStream(file)));
        }
    }
}
