package com.lgp.netty.netty_ch4;

import com.google.protobuf.MessageLite;
import io.netty.channel.*;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/29 16:26
 * @DESCRIPTION ProtoBuf 序列化
 **/
public class ProtoBufInitializer extends ChannelInitializer<Channel> {

    private final MessageLite lite;

    public ProtoBufInitializer(MessageLite lite) {
        this.lite = lite;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        添加 ProtobufVarint32FrameDecoder 用来分割帧
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
//        添加 ProtobufEncoder 用来处理消息的编码
        pipeline.addLast(new ProtobufEncoder());
//        添加 ProtobufDecoder 用来处理消息的解码
        pipeline.addLast(new ProtobufDecoder(lite));
//        添加 ObjectHandler 用来处理解码了的消息
        pipeline.addLast(new ObjectHandler());
    }

    public static final class ObjectHandler extends SimpleChannelInboundHandler<Object> {
        @Override
        public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
            // Do something with the object
        }
    }
}
