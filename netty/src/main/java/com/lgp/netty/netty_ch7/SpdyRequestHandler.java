package com.lgp.netty.netty_ch7;

import io.netty.channel.ChannelHandler;

/**
 * @AUTHOR lgp
 * @DESCRIPTION 继承 HttpRequestHandler 这样就能共享相同的逻辑
 **/
@ChannelHandler.Sharable
public class SpdyRequestHandler extends HttpRequestHandler {
    @Override
    protected String getContent() {
        //2生产内容写到 payload。这个重写了 HttpRequestHandler 的 getContent() 的实现
        return "This content is transmitted via SPDY\r\n";
    }
}
