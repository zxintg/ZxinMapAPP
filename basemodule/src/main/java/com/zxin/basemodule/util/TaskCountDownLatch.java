package com.zxin.basemodule.util;

public class TaskCountDownLatch {
    private volatile int mCount;
    private Object mLock = new Object();
    public TaskCountDownLatch(int count) {
        synchronized (mLock) {
            mCount = count;
        }
    }

    public void countDown() {
        synchronized (mLock) {
            mCount--;
            if (mCount <= 0) {
                mLock.notify();
            }
        }
    }

    public void await() {
        synchronized (mLock) {
            while (mCount > 0) {
                try {
                    mLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
