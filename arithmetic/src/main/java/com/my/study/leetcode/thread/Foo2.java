package com.my.study.leetcode.thread;

import java.util.concurrent.locks.LockSupport;

public class Foo2 {

    private volatile Thread thread2 = null;
    private volatile Thread thread3 = null;
    private volatile boolean finish1 = false;
    private volatile boolean finish2 = false;

    public Foo2() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        finish1 = true;
        if(thread2 != null){
            LockSupport.unpark(thread2);
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        thread2 = Thread.currentThread();
        if(!finish1)
            LockSupport.park();
        printSecond.run();
        finish2 = true;
        if(thread3 != null){
            LockSupport.unpark(thread3);
        }
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        thread3 = Thread.currentThread();
        if(!finish2)
            LockSupport.park();
        printThird.run();
    }
}
