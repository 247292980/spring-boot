package com.lgp.netty;

import com.lgp.netty.netty_ch9.MemcachedRequest;
import com.lgp.netty.netty_ch9.MemcachedResponse;
import com.lgp.netty.netty_ch9.MemcachedResponseDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.util.CharsetUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/3 16:39
 * @DESCRIPTION
 **/
public class MemcachedResponseDecoderTest {

    @Test
    public void testMemcachedResponseDecoder() {
        //1 新建 EmbeddedChannel ，持有 MemcachedResponseDecoder 到测试
        EmbeddedChannel channel = new EmbeddedChannel(new MemcachedResponseDecoder());

        byte magic = 1;
        byte opCode = MemcachedRequest.Opcode.SET;

        byte[] key = "Key1".getBytes(CharsetUtil.US_ASCII);
        byte[] body = "Value".getBytes(CharsetUtil.US_ASCII);
        int id = (int) System.currentTimeMillis();
        long cas = System.currentTimeMillis();

        //2 创建一个新的 Buffer 并写入数据，与二进制协议的结构相匹配
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeByte(magic);
        buffer.writeByte(opCode);
        buffer.writeShort(key.length);
        buffer.writeByte(0);
        buffer.writeByte(0);
        buffer.writeShort(MemcachedRequest.Status.KEY_EXISTS);
        buffer.writeInt(body.length + key.length);
        buffer.writeInt(id);
        buffer.writeLong(cas);
        buffer.writeBytes(key);
        buffer.writeBytes(body);

        //3 写缓冲区到 EmbeddedChannel 和检查是否一个新的MemcachedResponse 创建由声明返回值
        Assert.assertTrue(channel.writeInbound(buffer));
        MemcachedResponse response = (MemcachedResponse) channel.readInbound();
        //4 判断 MemcachedResponse 和预期的值
        assertResponse(response, magic, opCode, MemcachedRequest.Status.KEY_EXISTS, 0, 0, id, cas, key, body);
    }

    @Test
    public void testMemcachedResponseDecoderFragments() {
        //5 创建一个新的 EmbeddedChannel 持有 MemcachedResponseDecoder 到测试
        EmbeddedChannel channel = new EmbeddedChannel(new MemcachedResponseDecoder());

        byte magic = 1;
        byte opCode = MemcachedRequest.Opcode.SET;

        byte[] key = "Key1".getBytes(CharsetUtil.US_ASCII);
        byte[] body = "Value".getBytes(CharsetUtil.US_ASCII);
        int id = (int) System.currentTimeMillis();
        long cas = System.currentTimeMillis();

        //6 创建一个新的 Buffer 和写入数据的二进制协议的结构相匹配
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeByte(magic);
        buffer.writeByte(opCode);
        buffer.writeShort(key.length);
        buffer.writeByte(0);
        buffer.writeByte(0);
        buffer.writeShort(MemcachedRequest.Status.KEY_EXISTS);
        buffer.writeInt(body.length + key.length);
        buffer.writeInt(id);
        buffer.writeLong(cas);
        buffer.writeBytes(key);
        buffer.writeBytes(body);

        //7 缓冲分割成三个片段
        ByteBuf fragment1 = buffer.readBytes(8);
        ByteBuf fragment2 = buffer.readBytes(24);
        ByteBuf fragment3 = buffer;

        //8 写的第一个片段 EmbeddedChannel 并检查,没有新的MemcachedResponse 创建,因为并不是所有的数据都是准备好了
        Assert.assertFalse(channel.writeInbound(fragment1));
        //9 写第二个片段 EmbeddedChannel 和检查,没有新的MemcachedResponse 创建,因为并不是所有的数据都是准备好了
        Assert.assertFalse(channel.writeInbound(fragment2));
        //10 写最后一段到 EmbeddedChannel 和检查新的 MemcachedResponse 是否创建，因为我们终于收到所有数据
        Assert.assertTrue(channel.writeInbound(fragment3));

        MemcachedResponse response = (MemcachedResponse) channel.readInbound();
        //11 判断 MemcachedResponse 与预期的值
        assertResponse(response, magic, opCode, MemcachedRequest.Status.KEY_EXISTS, 0, 0, id, cas, key, body);
    }

    private static void assertResponse(MemcachedResponse response, byte magic, byte opCode, short status, int expires, int flags, int id, long cas, byte[] key, byte[] body) {
        Assert.assertEquals(magic, response.magic());
        Assert.assertArrayEquals(key, response.key().getBytes(CharsetUtil.US_ASCII));
        Assert.assertEquals(opCode, response.opCode());
        Assert.assertEquals(status, response.status());
        Assert.assertEquals(cas, response.cas());
        Assert.assertEquals(expires, response.expires());
        Assert.assertEquals(flags, response.flags());
        Assert.assertArrayEquals(body, response.data().getBytes(CharsetUtil.US_ASCII));
        Assert.assertEquals(id, response.id());
    }
}
