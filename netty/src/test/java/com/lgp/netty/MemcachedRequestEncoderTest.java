package com.lgp.netty;

import com.lgp.netty.netty_ch9.MemcachedRequest;
import com.lgp.netty.netty_ch9.MemcachedRequestEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.util.CharsetUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/3 16:25
 * @DESCRIPTION
 **/
public class MemcachedRequestEncoderTest {

    @Test
    public void testMemcachedRequestEncoder() {
        //1 新建 MemcachedRequest 用于编码为 ByteBuf
        MemcachedRequest request = new MemcachedRequest(MemcachedRequest.Opcode.SET, "key1", "value1");
        //2 新建 EmbeddedChannel 用于保持 MemcachedRequestEncoder 到测试
        EmbeddedChannel channel = new EmbeddedChannel(new MemcachedRequestEncoder());
        //3 写请求到 channel 并且判断是否产生了编码的消息
        channel.writeOutbound(request);

        ByteBuf encoded = (ByteBuf) channel.readOutbound();

        //4 检查 ByteBuf 是否 null
        Assert.assertNotNull(encoded);
        //5 判断 magic 是否正确写入 ByteBuf
        Assert.assertEquals(request.magic(), encoded.readUnsignedByte());
        //6 判断 opCode (SET) 是否写入正确
        Assert.assertEquals(request.opCode(), encoded.readByte());
        //7 检查 key 是否写入长度正确
        Assert.assertEquals(4, encoded.readShort());
        //8 检查写入的请求是否额外包含
        Assert.assertEquals((byte) 0x08, encoded.readByte());
        //9 检查数据类型是否写
        Assert.assertEquals((byte) 0, encoded.readByte());
        //10 检查是否保留数据插入
        Assert.assertEquals(0, encoded.readShort());
        //11 检查 body 的整体大小 计算方式是 key.length + body.length + extras
        Assert.assertEquals(4 + 6 + 8, encoded.readInt());
        //12 检查是否正确写入 id
        Assert.assertEquals(request.id(), encoded.readInt());
        //13 检查是否正确写入 Compare and Swap (CAS)
        Assert.assertEquals(request.cas(), encoded.readLong());
        //14 检查是否正确的 flag
        Assert.assertEquals(request.flags(), encoded.readInt());
        //15 检查是否正确设置到期时间的
        Assert.assertEquals(request.expires(), encoded.readInt());
        //16 v检查 key 和 body 是否正确
        byte[] data = new byte[encoded.readableBytes()];
        encoded.readBytes(data);
        Assert.assertArrayEquals((request.key() + request.body()).getBytes(CharsetUtil.UTF_8), data);
        //17 检查是否可读
        Assert.assertFalse(encoded.isReadable());
        Assert.assertFalse(channel.finish());
        Assert.assertNull(channel.readInbound());
    }
}