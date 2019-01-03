package com.lgp.netty;

import com.lgp.netty.netty_ch5.FixedLengthFrameDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/29 17:38
 * @DESCRIPTION
 **/
public class FixedLengthFrameDecoderTest {

    @Test
    public void testFramesDecoded() {
        System.out.println("begin!");
        /*设置netty接受ByteBuf的方式 Heap堆内存*/
        System.setProperty("io.netty.noUnsafe","true");

        //2 新建 ByteBuf 并用字节填充它
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();
        //3 新增 EmbeddedChannel 并添加 FixedLengthFrameDecoder 用于测试
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        //4 写数据到 EmbeddedChannel
        Assert.assertFalse(channel.writeInbound(input.readBytes(2)));
        Assert.assertTrue(channel.writeInbound(input.readBytes(7)));
        //5 标记 channel 已经完成
        Assert.assertTrue(channel.finish());
        ByteBuf read = (ByteBuf) channel.readInbound();
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();

        read = (ByteBuf) channel.readInbound();
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();

        read = (ByteBuf) channel.readInbound();
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();
//6 读产生的消息并且校验
        Assert.assertNull(channel.readInbound());
        buf.release();
        System.out.println("end!");
    }

}
