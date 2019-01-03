package com.lgp.netty.netty_ch3;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * @AUTHOR lgp
 * @DATE 2018/12/28 17:58
 * @DESCRIPTION CombinedByteCharCodec 的参数是解码器和编码器的实现用于处理进站字节和出站消息
 **/
public class CombinedByteCharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {
//    传递 ByteToCharDecoder 和 CharToByteEncoder 实例到 super 构造函数来委托调用使他们结合起来。
    public CombinedByteCharCodec() {
        super(new ByteToCharDecoder(), new CharToByteEncoder());
    }
}
