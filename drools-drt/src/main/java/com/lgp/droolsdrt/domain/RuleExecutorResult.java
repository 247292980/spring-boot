package com.lgp.droolsdrt.domain;

/**
 * 规则执行结果
 *
 */
public class RuleExecutorResult {

    // 匹配总数
    private int count;

    // 执行成功
    private int success;

    // 执行失败
    private int failure;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public void addCount() {
        this.count += 1;
    }

    public void addFailure() {
        this.failure += 1;
    }

    public void addSuccess() {
        this.success += 1;
    }
}
