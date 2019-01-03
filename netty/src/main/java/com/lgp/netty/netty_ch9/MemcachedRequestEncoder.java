package com.lgp.netty.netty_ch9;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/3 15:49
 * @DESCRIPTION 该类是负责编码 MemachedRequest 为一系列字节
 **/
public class MemcachedRequestEncoder extends MessageToByteEncoder<MemcachedRequest> {
    //2 转换的 key 和实际请求的 body 到字节数组
    @Override
    protected void encode(ChannelHandlerContext ctx, MemcachedRequest msg, ByteBuf out) throws Exception {
        byte[] key = msg.key().getBytes(CharsetUtil.UTF_8);
        byte[] body = msg.body().getBytes(CharsetUtil.UTF_8);
        //total size of the body = key size + content size + extras size   //3 计算 body 大小
        int bodySize = key.length + body.length + (msg.hasExtras() ? 8 : 0);

        //write magic byte  //4 写幻数到 ByteBuf 字节
        out.writeByte(msg.magic());
        //write opcode byte  //5 写 opCode 作为字节
        out.writeByte(msg.opCode());
        //write key length (2 byte) //6 写 key 长度z作为 short
        out.writeShort(key.length); //key length is max 2 bytes i.e. a Java short  //7 编写额外的长度作为字节
        //write extras length (1 byte)
        int extraSize =  msg.hasExtras() ? 0x08 : 0x0;
        out.writeByte(extraSize);
        //byte is the data type, not currently implemented in Memcached but required
        // 8 写数据类型,这总是0,因为目前不是在 Memcached,但可用于使用 后来的版本
        out.writeByte(0);
        //next two bytes are reserved, not currently implemented but are required
        // 9 为保留字节写为 short ,后面的 Memcached 版本可能使用
        out.writeShort(0);

        //write total body length ( 4 bytes - 32 bit int)
        // 10 写 body 的大小作为 long
        out.writeInt(bodySize);
        //write opaque ( 4 bytes)  -  a 32 bit int that is returned in the response
        // 11 写 opaque 作为 int
        out.writeInt(msg.id());

        //write CAS ( 8 bytes)  // 12 写 cas 作为 long。这个是头文件的最后部分，在 body 的开始
        out.writeLong(msg.cas());   //24 byte header finishes with the CAS


        if (msg.hasExtras()) {
            //write extras (flags and expiry, 4 bytes each) - 8 bytes total
            //13 编写额外的 flag 和到期时间为 int
            out.writeInt(msg.flags());
            out.writeInt(msg.expires());
        }
        //write key   //14 写 key
        out.writeBytes(key);
        //write value  //15 这个请求完成后 写 body
        out.writeBytes(body);
    }
}
