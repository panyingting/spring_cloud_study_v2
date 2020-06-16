package com.my.study.leetcode.thread;

import java.util.ArrayList;

public class CompareSleepZeroAndYield {

    private ArrayList<Integer> list1 = new ArrayList<>();
    private ArrayList<Integer> list2 = new ArrayList<>();

    public ArrayList<Integer> getList1() {
        return list1;
    }

    public ArrayList<Integer> getList2() {
        return list2;
    }

    public CompareSleepZeroAndYield() {
        list1.add(0);
        list2.add(0);
    }

    public void tryFieldLock1() {
        synchronized (this.list1) {
            list1.add(list2.get(list2.size() - 1) + 1);
        }
    }

    public void tryFieldLock2() {
        synchronized (this.list2) {
            list2.add(list1.get(list1.size() - 1) + 1);
        }
    }

    public static void main(String[] args) {
        CompareSleepZeroAndYield obj = new CompareSleepZeroAndYield();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 100;
                while (--count > 0) {
                    obj.tryFieldLock1();
//                    try {
//                        Thread.sleep(0);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    // compare above and below
                     Thread.yield();
                }
                System.out.println(obj.getList1());
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 100;
                while (--count > 0) {
                    obj.tryFieldLock2();

//                    try {
//                        Thread.sleep(0);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    // compare above and below
                     Thread.yield();
                }
                System.out.println(obj.getList2());
            }
        });
        t1.start();
        t2.start();


    }
}
