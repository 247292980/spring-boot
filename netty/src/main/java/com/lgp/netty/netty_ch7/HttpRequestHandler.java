package com.lgp.netty.netty_ch7;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/2 18:01
 * @DESCRIPTION
 **/
@ChannelHandler.Sharable
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    //1重写 channelRead0() ,可以被所有的接收到的 FullHttpRequest 调用
    @Override
    public void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        if (HttpHeaders.is100ContinueExpected(request)) {
            //2 检查如果接下来的响应是预期的，就写入
            send100Continue(ctx);
        }
        //3 新建 FullHttpResponse,用于对请求的响应
        FullHttpResponse response = new DefaultFullHttpResponse(request.getProtocolVersion(), HttpResponseStatus.OK);
        //4 生成响应的内容，将它写入 payload
        response.content().writeBytes(getContent().getBytes(CharsetUtil.UTF_8));
        //5 设置头文件，这样客户端就能知道如何与 响应的 payload 交互
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");

        boolean keepAlive = HttpHeaders.isKeepAlive(request);
        //6 检查请求设置是否启用了 keepalive;如果是这样,将标题设置为符合HTTP RFC
        if (keepAlive) {
            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, response.content().readableBytes());
            response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        }
        //7 写响应给客户端，并获取到 Future 的引用，用于写完成时，获取到通知
        ChannelFuture future = ctx.writeAndFlush(response);
        //8 如果响应不是 keepalive，在写完成时关闭连接
        if (!keepAlive) {
            future.addListener (ChannelFutureListener.CLOSE);
        }
    }
    //9 返回内容作为响应的 payload
    protected String getContent() {
        return "This content is transmitted via HTTP\r\n";
    }
    //10 Helper 方法生成了100 持续的响应，并写回给客户端
    private static void send100Continue(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);
    }
    //11 若执行阶段抛出异常，则关闭管道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
