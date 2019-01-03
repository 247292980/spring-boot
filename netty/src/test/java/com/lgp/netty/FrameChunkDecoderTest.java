package com.lgp.netty;

import com.lgp.netty.netty_ch5.FrameChunkDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.TooLongFrameException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/2 16:22
 * @DESCRIPTION
 **/
public class FrameChunkDecoderTest {

    @Test
    public void testFramesDecoded() {
        ByteBuf buf = Unpooled.buffer();  //2 新建 ByteBuf 写入 9 个字节
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();
//3 新建 EmbeddedChannel 并安装一个 FixedLengthFrameDecoder 用于测试
        EmbeddedChannel channel = new EmbeddedChannel(new FrameChunkDecoder(3));
        //4 写入 2 个字节并预测生产的新帧(消息)
        Assert.assertTrue(channel.writeInbound(input.readBytes(2)));
        try {
            //5 写一帧大于帧的最大容量 (3) 并检查一个 TooLongFrameException 异常
            channel.writeInbound(input.readBytes(4));
            //6 如果异常没有被捕获，测试将失败。注意如果类实现 exceptionCaught() 并且处理了异常 exception，那么这里就不会捕捉异常
            Assert.fail();
        } catch (TooLongFrameException e) {
            // expected
        }
        Assert.assertTrue(channel.writeInbound(input.readBytes(3)));  //7 写剩余的 2 个字节预测一个帧


        Assert.assertTrue(channel.finish());  //8 标记 channel 完成

        ByteBuf read = (ByteBuf) channel.readInbound();
        //9 读到的产生的消息并且验证值。注意 assertEquals(Object,Object)测试使用 equals() 是否相当，不是对象的引用是否相当
        Assert.assertEquals(buf.readSlice(2), read);
        read.release();

        read = (ByteBuf) channel.readInbound();
        Assert.assertEquals(buf.skipBytes(4).readSlice(3), read);
        read.release();

        buf.release();
    }
}
