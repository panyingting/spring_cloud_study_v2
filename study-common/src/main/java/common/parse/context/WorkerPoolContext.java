package common.parse.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkerPoolContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerPoolContext.class);

    /**
     * 当前虚拟机允许最大线程数
     */
    private static final int MAX_THREAD = 15;

    /**
     * 一个context最大分配线程数
     */
    private static final int DEFAULT_THREADS_PER_CONTEXT = 5;


    /**
     * 当前已分配线程数
     */
    private static AtomicInteger occupationThreadNum = new AtomicInteger(0);

    private String workPoolName = null;


    private ThreadPoolExecutor executorService = null;
    private List<Future<String>> futures = new ArrayList<>();

    /**
     * @param workPoolName 执行任务名称
     * @return 返回课可执行任务的线程数 ，分配不到线程池时由主线程执行，也就是 1个线程执行任务
     */
    public int initExecutorService(String workPoolName) {
        return initExecutorService(workPoolName, DEFAULT_THREADS_PER_CONTEXT);
    }

    public int initExecutorService(String workPoolName, int nThreads) {
        this.workPoolName = workPoolName;
        int currThreadNum = occupationThreadNum.get();
        int newThreadNum = currThreadNum + nThreads;
        if (MAX_THREAD >= newThreadNum && occupationThreadNum.compareAndSet(currThreadNum, newThreadNum)) {
            executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(nThreads, new MyThreadFactory(workPoolName));
            int corePoolSize = executorService.getCorePoolSize();
            LOGGER.info("WorkerPoolContext线程池初始化成功， nThreads:{}, workPoolName:{}, corePoolSize:{}", nThreads, workPoolName, corePoolSize);
        }

        // 如果分配线程失败，则返回 1， 让调用的程序基于一个线程分配任务（提交任务由主线程执行）
        return 1;
    }

    public String submitTask(Runnable runnable, String remark) {
        if (executorService != null) {
            Future<String> future = executorService.submit(runnable, remark);
            LOGGER.info("WorkerPoolContext线程池新任务提交成功，remark:{}", remark);
            futures.add(future);
            return "任务提交成功";
        } else {
            runnable.run();
            return "无线程池，当前线程执行成功";
        }

    }

    /**
     * 线程池调用完之后，需要调用此方法进行资源释放
     */
    public void waitTermination() {
        if (executorService != null) {
            try {
                for (Future<String> future : futures) {
                    String remark = future.get();
                    LOGGER.info("线程池任务执行完毕，workPoolName:{}, remake:{}", workPoolName, remark);
                }
            } catch (Exception ex) {
                LOGGER.warn("线程池执行任务出错，ex:{}", ex.getMessage(), ex);
                throw new RuntimeException("线程池执行任务出错, workPoolName:" + workPoolName + ":" + ex.getMessage());
            } finally {
                executorService.shutdownNow();
                try {
                    if (executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                        LOGGER.info("WorkerPoolContext线程池-终止线程成功,workPoolName:{}", workPoolName);
                    } else {
                        LOGGER.error("WorkerPoolContext线程池-等待任务时间过长，线程池强行终止,workPoolName:{}", workPoolName);
                    }
                } catch (InterruptedException e) {
                    LOGGER.error("WorkerPoolContext线程池-关闭线程等待异常，关闭失败,workPoolName:{}", workPoolName, e);
                }
            }
            // 清除线程池信息，避免context重复创建线程池而导致的一些问题
            refreshContext();

        }
    }

    private void refreshContext() {
        boolean success = false;
        while (!success) {
            int currNum = occupationThreadNum.get();
            success = occupationThreadNum.compareAndSet(currNum, currNum - executorService.getCorePoolSize());
        }
        executorService = null;
        futures.clear();
    }

    private class MyThreadFactory implements ThreadFactory {

        private int serial = 1;
        private String name;

        private MyThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, name + "-" + (serial++) + "-");
        }
    }

}
