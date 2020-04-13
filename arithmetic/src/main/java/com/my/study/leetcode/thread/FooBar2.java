package com.my.study.leetcode.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class FooBar2 {
    private int n;

    private volatile CountDownLatch latch1 = new CountDownLatch(1);
    private volatile CountDownLatch latch2 = new CountDownLatch(1);

    public FooBar2(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            latch1.await();
            printFoo.run();
            latch1 = new CountDownLatch(1);
            latch2.countDown();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            latch1.countDown();
            latch2.await();
            printBar.run();
            latch2 = new CountDownLatch(1);
        }
    }
}
