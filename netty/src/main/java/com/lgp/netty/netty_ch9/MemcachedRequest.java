package com.lgp.netty.netty_ch9;

import java.util.Random;

/**
 * @AUTHOR lgp
 * @DATE 2019/1/3 15:43
 * @DESCRIPTION  这个类将会发送请求到 Memcached server
 **/
public class MemcachedRequest {
    private static final Random rand = new Random();
    private final int magic = 0x80;//fixed so hard coded
    private final byte opCode; //the operation e.g. set or get
    private final String key; //the key to delete, get or set
    private final int flags = 0xdeadbeef; //random
    private final int expires; //0 = item never expires
    private final String body; //if opCode is set, the value
    private final int id = rand.nextInt(); //Opaque
    private final long cas = 0; //data version check...not used
    private final boolean hasExtras; //not all ops have extras

    public MemcachedRequest(byte opcode, String key, String value) {
        this.opCode = opcode;
        this.key = key;
        this.body = value == null ? "" : value;
        this.expires = 0;
        //only set command has extras in our example
        hasExtras = opcode == Opcode.SET;
    }

    public MemcachedRequest(byte opCode, String key) {
        this(opCode, key, null);
    }

    public int magic() { //2 幻数，它可以用来标记文件或者协议的格式
        return magic;
    }

    public int opCode() {  //3 opCode,反应了响应的操作已经创建了
        return opCode;
    }

    public String key() {  //4 执行操作的 key
        return key;
    }

    public int flags() {  //5 使用的额外的 flag
        return flags;
    }

    public int expires() {  //6 表明到期时间
        return expires;
    }

    public String body() {  //7 body
        return body;
    }

    public int id() {  //8 请求的 id。这个id将在响应中回显。
        return id;
    }

    public long cas() {  //9 compare-and-check 的值
        return cas;
    }

    public boolean hasExtras() {  //10 如果有额外的使用，将返回 true
        return hasExtras;
    }

    public class Status {
        public static final short NO_ERROR = 0x0000;
        public static final short KEY_NOT_FOUND = 0x0001;
        public static final short KEY_EXISTS = 0x0002;
        public static final short VALUE_TOO_LARGE = 0x0003;
        public static final short INVALID_ARGUMENTS = 0x0004;
        public static final short ITEM_NOT_STORED = 0x0005;
        public static final short INC_DEC_NON_NUM_VAL = 0x0006;
    }

    public class Opcode {
        public static final byte GET = 0x00;
        public static final byte SET = 0x01;
        public static final byte DELETE = 0x04;
    }
}
