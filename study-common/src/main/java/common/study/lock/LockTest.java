package common.study.lock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    private static final Lock lock = new ReentrantLock();

    CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) {

        lock.lock();
        try {
            System.out.println("lock");
        } finally {
            lock.unlock();
        }


    }

    @Test
    public void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 3; i++)
            new Thread() {
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Inner thread execute");
                }
            }.start();
        System.out.println("Mian Thread await CountDownLatch.........");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
        System.out.println("Mian Thread await CountDownLatch Finish.......");
    }

}
