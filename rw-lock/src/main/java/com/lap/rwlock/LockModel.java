package com.lap.rwlock;


public enum LockModel {
    READ("%s:READ"),
    WRITE("%s:WRITE"),;

    String lockFormat;
    LockModel(String lockFormat) {
        this.lockFormat = lockFormat;
    }
    public String getLockModelName() {
        return super.name();
    }
    public String getLockFormat() {
        return lockFormat;
    }

    public static void main(String[] args) {
        LockModel read = LockModel.READ;
        System.out.println(read.getLockFormat());
        System.out.println(read.getLockModelName());
    }
}