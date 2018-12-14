package com.lap.rwlock;

import com.lap.rwlock.constant.RedisConn;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @AUTHOR lgp
 * @DATE 2018/11/27 15:34
 * @DESCRIPTION
 **/
public class ConstantTest extends BaseTest {
    @Autowired
    private RedisConn redisConn;


    @Test
    public void redisConnTest() {
        System.out.println(redisConn.getDatabase());
        System.out.println(redisConn.getHost());
        System.out.println(redisConn.getPassport());
        System.out.println(redisConn.getPort());
        System.out.println(redisConn.getTimeout());
    }

}
