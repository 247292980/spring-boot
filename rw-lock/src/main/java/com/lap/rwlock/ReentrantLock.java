package com.lap.rwlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static com.lap.rwlock.ReadWriteLockConstant.READ_MODEL;
import static com.lap.rwlock.ReadWriteLockConstant.WRITE_MODEL;

/**
 * @AUTHOR lgp
 * @DATE 2018/11/28 17:49
 * @DESCRIPTION 可重入 读写互斥
 **/
public class ReentrantLock implements Lock {

    @Autowired
    RedisTemplate redisTemplate;
    String name;
    Long timeInterval;
    Long timeout = 0L;

    public ReentrantLock(String name, Long timeInterval) {
        this.name = name;
        this.timeInterval = timeInterval;
    }

    @Override
    public void lock() {
        try {
            lockInterruptibly();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        if (getLockModel().equals(READ_MODEL)) {
            if (tryLock()) {
                throw new InterruptedException();
            }
            setTimeout(System.currentTimeMillis() + getTimeInterval());
            redisTemplate.opsForValue().getAndSet(getName(), getTimeout());
        } else {
            if (tryLock()) {
                throw new InterruptedException();
            }
            setTimeout(System.currentTimeMillis() + getTimeInterval());
            redisTemplate.opsForValue().getAndSet(getName(), getTimeout());
            /*避免脏读*/
            while (null != redisTemplate.opsForValue().get(getOpposeLockName())) {
                Thread.sleep(300);
            }

        }
    }

    @Override
    public boolean tryLock() {
        if (getLockModel().equals(READ_MODEL)) {
            /*写锁不存在，返回false*/
            return null != redisTemplate.opsForValue().get(getOpposeLockName());
        } else {
            Long writeTimeout = (Long) redisTemplate.opsForValue().get(getName());
            /*没人持有写锁*/
            if (null == writeTimeout) {
                return false;
            }
            /*重入*/
            if (writeTimeout < getTimeout()) {
                return false;
            }
            return true;
        }
    }

    @Override
    public void unlock() {
        if (getLockModel().equals(READ_MODEL)) {
            if (System.currentTimeMillis() > getTimeout()) {
                redisTemplate.delete(getName());
            }
        } else {
            if (System.currentTimeMillis() < getTimeout()) {
                redisTemplate.delete(getName());
            }
        }
    }

    /**
     * 分布式不考虑自旋
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    /**
     * 不提供控制锁线程的操作
     */
    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        return name;
    }

    public Long getTimeInterval() {
        return timeInterval;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public String getLockModel() {
        if (getName().contains(WRITE_MODEL)) {
            return WRITE_MODEL;
        }
        return READ_MODEL;
    }

    private String getOpposeLockName() {
        if (getName().contains(WRITE_MODEL)) {
            return getName().replace(WRITE_MODEL, READ_MODEL);
        }
        return getName().replace(READ_MODEL, WRITE_MODEL);
    }
}
