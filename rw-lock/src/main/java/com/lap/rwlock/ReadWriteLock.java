package com.lap.rwlock;

import java.util.concurrent.locks.Lock;

/**
 * @AUTHOR lgp
 * @DATE 2018/11/28 11:40
 * @DESCRIPTION
 **/
public class ReadWriteLock implements java.util.concurrent.locks.ReadWriteLock {

    String name;
    /**
     * 毫秒
    * */
    Long timeInterval;

    public ReadWriteLock(String name, Long timeInterval) {
        this.name = name;
        this.timeInterval = timeInterval;
    }

    @Override
    public Lock readLock() {
        return new ReentrantLock(this, LockModel.READ);
    }

    @Override
    public Lock writeLock() {
        return new ReentrantLock(this, LockModel.WRITE);
    }

    public Long getTimeInterval() {
        return timeInterval;
    }

    public String getName() {
        return name;
    }

}
