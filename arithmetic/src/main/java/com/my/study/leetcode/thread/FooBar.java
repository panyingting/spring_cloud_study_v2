package com.my.study.leetcode.thread;

public class FooBar {
    private int n;

    private volatile boolean flag = false;
    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            while (flag){
                Thread.yield();
            };
            printFoo.run();
            flag = true;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            while (!flag);
            printBar.run();
            flag = false;
        }
    }
}
