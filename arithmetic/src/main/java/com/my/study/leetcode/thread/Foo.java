package com.my.study.leetcode.thread;

import java.util.concurrent.CountDownLatch;

class Foo {

    CountDownLatch latch = new CountDownLatch(1);
    CountDownLatch latch2 = new CountDownLatch(1);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        latch.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        latch.await();
        printSecond.run();
        latch2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        latch2.await();
        printThird.run();
    }
}
