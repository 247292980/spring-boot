package com.lgp.netty.netty_ch3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.*;

import java.util.List;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/28 17:45
 * @DESCRIPTION 编码 LocalWebSocketFrame 消息转为 WebSocketConvertHandler.LocalWebSocketFrame 消息
 **/
public class WebSocketConvertHandler extends MessageToMessageCodec<WebSocketFrame, WebSocketConvertHandler.LocalWebSocketFrame> {

    public static final WebSocketConvertHandler INSTANCE = new WebSocketConvertHandler();

    @Override
    protected void encode(ChannelHandlerContext ctx, LocalWebSocketFrame msg, List<Object> out) throws Exception {
        ByteBuf payload = msg.getData().duplicate().retain();
        //2 检测 LocalWebSocketFrame 的 FrameType 类型，并且创建一个新的响应的 FrameType 类型的 WebSocketFrame
        switch (msg.getType()) {
            case BINARY:
                out.add(new BinaryWebSocketFrame(payload));
                break;
            case TEXT:
                out.add(new TextWebSocketFrame(payload));
                break;
            case CLOSE:
                out.add(new CloseWebSocketFrame(true, 0, payload));
                break;
            case CONTINUATION:
                out.add(new ContinuationWebSocketFrame(payload));
                break;
            case PONG:
                out.add(new PongWebSocketFrame(payload));
                break;
            case PING:
                out.add(new PingWebSocketFrame(payload));
                break;
            default:
                throw new IllegalStateException("Unsupported websocket msg " + msg);
        }
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out) throws Exception {
        //3 通过 instanceof 来检测正确的 FrameType
        if (msg instanceof BinaryWebSocketFrame) {
            out.add(new LocalWebSocketFrame(LocalWebSocketFrame.FrameType.BINARY, msg.content().copy()));
        } else if (msg instanceof CloseWebSocketFrame) {
            out.add(new LocalWebSocketFrame(LocalWebSocketFrame.FrameType.CLOSE, msg.content().copy()));
        } else if (msg instanceof PingWebSocketFrame) {
            out.add(new LocalWebSocketFrame(LocalWebSocketFrame.FrameType.PING, msg.content().copy()));
        } else if (msg instanceof PongWebSocketFrame) {
            out.add(new LocalWebSocketFrame(LocalWebSocketFrame.FrameType.PONG, msg.content().copy()));
        } else if (msg instanceof TextWebSocketFrame) {
            out.add(new LocalWebSocketFrame(LocalWebSocketFrame.FrameType.TEXT, msg.content().copy()));
        } else if (msg instanceof ContinuationWebSocketFrame) {
            out.add(new LocalWebSocketFrame(LocalWebSocketFrame.FrameType.CONTINUATION, msg.content().copy()));
        } else {
            throw new IllegalStateException("Unsupported websocket msg " + msg);
        }
    }

    //4  自定义消息类型 LocalWebSocketFrame
    public static final class LocalWebSocketFrame {
        //5 枚举类明确了 LocalWebSocketFrame 的类型
        public enum FrameType {
            BINARY,
            CLOSE,
            PING,
            PONG,
            TEXT,
            CONTINUATION
        }

        private final FrameType type;
        private final ByteBuf data;

        public LocalWebSocketFrame(FrameType type, ByteBuf data) {
            this.type = type;
            this.data = data;
        }

        public FrameType getType() {
            return type;
        }

        public ByteBuf getData() {
            return data;
        }
    }
}
