package com.lap.rwlock.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR lgp
 * @DATE 2018/11/27 14:57
 * @DESCRIPTION 用户看系统能否加载配置
 **/
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConn {
    private String database;
    private String host;
    private int port;
    private String passport;
    private int timeout;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
