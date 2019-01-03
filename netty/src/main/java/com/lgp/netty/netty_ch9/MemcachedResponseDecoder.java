package com.lgp.netty.netty_ch9;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/3 16:00
 * @DESCRIPTION 类负责创建的 MemcachedResponse 读取字节
 **/
public class MemcachedResponseDecoder extends ByteToMessageDecoder {
    private enum State {  //2 代表当前解析状态,这意味着我们需要解析的头或 body
        Header,
        Body
    }

    private State state = State.Header;
    private int totalBodySize;
    private byte magic;
    private byte opCode;
    private short keyLength;
    private byte extraLength;
    private short status;
    private int id;
    private long cas;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        switch (state) { //3 根据解析状态切换
            case Header:
                if (in.readableBytes() < 24) {
                    // 4 如果不是至少24个字节是可读的,它不可能读整个头部,所以返回这里,等待再通知一次数据准备阅读
                    return;//response header is 24  bytes
                }
                //5 阅读所有头的字段
                magic = in.readByte();
                opCode = in.readByte();
                keyLength = in.readShort();
                extraLength = in.readByte();
                in.skipBytes(1);
                status = in.readShort();
                totalBodySize = in.readInt();
                id = in.readInt(); //referred to in the protocol spec as opaque
                cas = in.readLong();

                state = State.Body;
                break;
            case Body:
                // 6 检查是否足够的数据是可读用来读取完整的响应的 body。长度是从头读取
                if (in.readableBytes() < totalBodySize) {
                    return; //until we have the entire payload return
                }
                int flags = 0, expires = 0;
                int actualBodySize = totalBodySize;
                //7 检查如果有任何额外的 flag 用于读，如果是这样做
                if (extraLength > 0) {
                    flags = in.readInt();
                    actualBodySize -= 4;
                }
                //8 检查如果响应包含一个 expire 字段，有就读它
                if (extraLength > 4) {
                    expires = in.readInt();
                    actualBodySize -= 4;
                }
                String key = "";
                //9 检查响应是否包含一个 key ,有就读它
                if (keyLength > 0) {
                    ByteBuf keyBytes = in.readBytes(keyLength);
                    key = keyBytes.toString(CharsetUtil.UTF_8);
                    actualBodySize -= keyLength;
                }
                //10 读实际的 body 的 payload
                ByteBuf body = in.readBytes(actualBodySize);
                String data = body.toString(CharsetUtil.UTF_8);
                //11 从前面读取字段和数据构造一个新的 MemachedResponse
                out.add(new MemcachedResponse(magic, opCode, (byte) 0, status, id, cas, flags, expires, key, data));
                state = State.Header;
                break;
            default:
                break;
        }

    }
}
