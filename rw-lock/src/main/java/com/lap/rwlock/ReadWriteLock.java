package com.lap.rwlock;

import java.util.concurrent.locks.Lock;

import static com.lap.rwlock.ReadWriteLockConstant.READ_LOCK;
import static com.lap.rwlock.ReadWriteLockConstant.WRITE_LOCK;

/**
 * @AUTHOR lgp
 * @DATE 2018/11/28 11:40
 * @DESCRIPTION
 **/
public class ReadWriteLock implements java.util.concurrent.locks.ReadWriteLock {

    String name;
    Long timeInterval;

    public ReadWriteLock(String name, Long timeInterval) {
        this.name = name;
        this.timeInterval = timeInterval;
    }

    @Override
    public Lock readLock() {
        return new ReentrantLock(String.format(READ_LOCK, getName()), getTimeInterval());
    }

    @Override
    public Lock writeLock() {
        return new ReentrantLock(String.format(WRITE_LOCK, getName()), getTimeInterval());
    }

    public Long getTimeInterval() {
        return timeInterval;
    }

    public String getName() {
        return name;
    }

}
