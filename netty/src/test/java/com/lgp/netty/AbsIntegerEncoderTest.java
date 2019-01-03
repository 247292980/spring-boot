package com.lgp.netty;

import com.lgp.netty.netty_ch5.AbsIntegerEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * @AUTHOR lgp
 * @DESCRIPTION
 **/
public class AbsIntegerEncoderTest {
    @Test
    public void testEncoded() {
        //2 新建 ByteBuf 并写入负整数
        ByteBuf buf = Unpooled.buffer();
        for (int i = 1; i < 10; i++) {
            buf.writeInt(i * -1);
        }
        //3 新建 EmbeddedChannel 并 AbsIntegerEncoder 来测试
        EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
        //4  写 ByteBuf 并预测 readOutbound() 产生的数据
        Assert.assertTrue(channel.writeOutbound(buf));
        //5 标记 channel 已经完成
        Assert.assertTrue(channel.finish());
        //6 读取产生到的消息，检查负值已经编码为绝对值

//        System.out.println(java.util.Optional.ofNullable(channel.readOutbound()));
        for (int i = 1; i < 10; i++) {
            Assert.assertEquals(java.util.Optional.ofNullable(i), java.util.Optional.ofNullable(channel.readOutbound()));
            Assert.assertEquals(1, 1);
        }
        Assert.assertNull(channel.readOutbound());
    }
}
