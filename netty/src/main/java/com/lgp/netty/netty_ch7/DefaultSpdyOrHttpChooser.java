package com.lgp.netty.netty_ch7;

import io.netty.channel.ChannelInboundHandler;
import io.netty.handler.codec.spdy.SpdyOrHttpChooser;
import org.eclipse.jetty.npn.NextProtoNego;

import javax.net.ssl.SSLEngine;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/3 9:26
 * @DESCRIPTION
 **/
public class DefaultSpdyOrHttpChooser extends SpdyOrHttpChooser {

    public DefaultSpdyOrHttpChooser(int maxSpdyContentLength, int maxHttpContentLength) {
        super(maxSpdyContentLength, maxHttpContentLength);
    }

    @Override
    protected SelectedProtocol getProtocol(SSLEngine engine) {
        //1 使用 NextProtoNego 用于获取 DefaultServerProvider 的引用, 用于 SSLEngine
        DefaultServerProvider provider = (DefaultServerProvider) NextProtoNego.get(engine);
        String protocol = provider.getSelectedProtocol();
        if (protocol == null) {
            //2 协议不能被检测到。一旦字节已经准备好读,检测过程将重新开始。
            return SelectedProtocol.UNKNOWN;
        }
        switch (protocol) {
            case "http/1.0":
                return SelectedProtocol.HTTP_1_0; //3 HTTP 1.0 被检测到
            case "spdy/3.1":
                return SelectedProtocol.SPDY_3_1; //4 SPDY 3 被检测到

            case "http/1.1":
                return SelectedProtocol.HTTP_1_1; //5 HTTP 1.1 被检测到
            default:
                return SelectedProtocol.UNKNOWN; //6 知协议被检测到
        }
    }

    @Override
    protected ChannelInboundHandler createHttpRequestHandlerForHttp() {
        //7 将会被调用给 FullHttpRequest 消息添加处理器。该方法只会在不支持 SPDY 时调用，那么将会使用 HTTPS
        return new HttpRequestHandler();
    }

    @Override
    protected ChannelInboundHandler createHttpRequestHandlerForSpdy() {
        //8 将会被调用给 FullHttpRequest 消息添加处理器。该方法在支持 SPDY 时调用
        return new SpdyRequestHandler();
    }

}
