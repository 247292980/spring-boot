package com.lgp.netty.netty_ch7;

import org.eclipse.jetty.npn.NextProtoNego;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/2 17:52
 * @DESCRIPTION 需要导入jetty的 npn-boot包
 *
 **/
public class DefaultServerProvider implements NextProtoNego.ServerProvider {
    //1 定义所有的 ServerProvider 实现的协议
    private static final List<String> PROTOCOLS = Collections.unmodifiableList(Arrays.asList("spdy/2", "spdy/3", "http/1.1"));

    private String protocol;
    //2 设置如果 SPDY 协议失败了就转到 http/1.1
    @Override
    public void unsupported() {
        protocol = "http/1.1";
    }
    //3 返回支持的协议的列表
    @Override
    public List<String> protocols() {
        return PROTOCOLS;
    }
    //4 设置选择的协议
    @Override
    public void protocolSelected(String protocol) {
        this.protocol = protocol;
    }
    //5 返回选择的协议
    public String getSelectedProtocol() {
        return protocol;
    }
}
