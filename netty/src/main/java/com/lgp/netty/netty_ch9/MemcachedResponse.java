package com.lgp.netty.netty_ch9;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/3 15:57
 * @DESCRIPTION
 **/
public class MemcachedResponse {  //1 该类,代表从 Memcached 服务器返回的响应
    private final byte magic;
    private final byte opCode;
    private byte dataType;
    private final short status;
    private final int id;
    private final long cas;
    private final int flags;
    private final int expires;
    private final String key;
    private final String data;

    public MemcachedResponse(byte magic, byte opCode, byte dataType, short status, int id, long cas, int flags, int expires, String key, String data) {
        this.magic = magic;
        this.opCode = opCode;
        this.dataType = dataType;
        this.status = status;
        this.id = id;
        this.cas = cas;
        this.flags = flags;
        this.expires = expires;
        this.key = key;
        this.data = data;
    }

    public byte magic() { //2 幻数
        return magic;
    }

    public byte opCode() { //3 opCode,这反映了创建操作的响应
        return opCode;
    }

    public byte dataType() { //4 数据类型,这表明这个是基于二进制还是文本
        return dataType;
    }

    public short status() {  //5 响应的状态,这表明如果请求是成功的
        return status;
    }

    public int id() {  //6 惟一的 id
        return id;
    }

    public long cas() {  //7 compare-and-set 值
        return cas;
    }

    public int flags() {  //8 使用额外的 flag
        return flags;
    }

    public int expires() { //9 表示该值存储的一个有效期
        return expires;
    }

    public String key() {  //10 响应创建的 key
        return key;
    }

    public String data() {  //11 实际数据
        return data;
    }
}
