package com.lap.rwlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @AUTHOR lgp
 * @DATE 2018/11/28 17:49
 * @DESCRIPTION 可重入 读写互斥
 **/
public class ReentrantLock implements Lock {

    @Autowired
    RedisTemplate redisTemplate;

    ReadWriteLock rwLock;
    LockModel lockModel;
    String lockName;
    Long deadTime = 0L;
    boolean localWriteLocked = false;

    public ReentrantLock(ReadWriteLock rwLock, LockModel lockModel) {
        this.rwLock = rwLock;
        this.lockModel = lockModel;
        setLockName(lockModel);
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
        switch (getLockModel()) {
            case WRITE:
                if (!isLocalWriteLocked()) {
                    setLockModel(LockModel.READ);
                    while (!tryLock()) {
                        Thread.sleep(500);
                    }
                    redisTemplate.opsForValue().set(getLockName(), getDeadTime(), getRwLock().getTimeInterval());

                    setLockModel(LockModel.WRITE);
                    while (!tryLock()) {
                        Thread.sleep(500);
                    }
                    setLocalWriteLocked(true);
                } else {
                    /**
                     * 本机持有写锁,等待之前的写操作完成
                     * */
                    while (!isLocalWriteLocked()) {
                        Thread.sleep(500);
                    }

                    /**
                     * 更新写锁的过期时间
                     * */
                    redisTemplate.opsForValue().set(getLockName(), getDeadTime(), getRwLock().getTimeInterval());
                    setLocalWriteLocked(true);
                }
                break;
            case READ:
                while (!tryLock()) {
                    Thread.sleep(500);
                }
                setDeadTime();
                redisTemplate.opsForValue().set(getLockName(), getDeadTime(), getRwLock().getTimeInterval());
                break;
        }
    }

    @Override
    public boolean tryLock() {
        boolean lockExist = false;
        switch (getLockModel()) {
            case WRITE:
                lockExist = null != redisTemplate.opsForValue().get(getOpposeLockName());
                break;
            case READ:
                lockExist = null != redisTemplate.opsForValue().get(getOpposeLockName());
                break;
            default:
                break;
        }
        return lockExist;
    }

    @Override
    public void unlock() {
        switch (getLockModel()) {
            case WRITE:
                if (isLocalWriteLocked()) {
                    setLocalWriteLocked(false);
                }
                redisTemplate.delete(getLockName());
                break;
            case READ:
                redisTemplate.delete(getLockName());
                break;
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
     * 不提供控制锁的操作
     */
    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }

    public Long getTimeInterval() {
        return rwLock.getTimeInterval();
    }

    public Long getDeadTime() {
        return deadTime;
    }

    public void setDeadTime() {
        this.deadTime = System.currentTimeMillis() + getTimeInterval();
    }

    private String getOpposeLockName() {
        String opposeLockName = "";
        switch (getLockModel()) {
            case READ:
                opposeLockName = String.format(LockModel.WRITE.getLockFormat(), getRwLock().getName());
                break;
            case WRITE:
                opposeLockName = String.format(LockModel.READ.getLockFormat(), getRwLock().getName());
                break;
            default:
                break;
        }
        return opposeLockName;
    }

    public String getLockName() {
        return lockName;
    }

    public void setLockName(LockModel lockModel) {
        this.lockName = String.format(lockModel.getLockFormat(), getRwLock().getName());
    }


    public ReadWriteLock getRwLock() {
        return rwLock;
    }

    public void setLockModel(LockModel lockModel) {
        this.lockModel = lockModel;
    }

    public LockModel getLockModel() {
        return lockModel;
    }

    public boolean isLocalWriteLocked() {
        return localWriteLocked;
    }

    public void setLocalWriteLocked(boolean localWriteLocked) {
        this.localWriteLocked = localWriteLocked;
    }
}
