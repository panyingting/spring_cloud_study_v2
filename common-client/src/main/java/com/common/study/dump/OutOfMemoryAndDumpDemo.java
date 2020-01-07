package com.common.study.dump;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OutOfMemoryAndDumpDemo {

    public static void main(String[] args) {


        ExecutorService executorService =  Executors.newFixedThreadPool(6);

        executorService.submit(new DemoA());
        executorService.submit(new DemoB());
        executorService.submit(new DemoC());

//        executorService.submit(new DemoA());
//        executorService.submit(new DemoB());
//        executorService.submit(new DemoC());

        System.out.println("Finish.........");

    }


    static class DemoA implements Runnable{
        private final Map<String, Object> map = new ConcurrentHashMap<>();
        @Override
        public void run() {

            for(int i=0; i<Integer.MAX_VALUE; i++){
                map.put("hepo"+i, new UserBean());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" : "+ Thread.currentThread().getId());
            }

            System.out.println("ok");
        }
    }

    static class DemoB implements Runnable{
        private  Map<String, Object> map2 = new ConcurrentHashMap<>();
        @Override
        public void run() {

            for(int i=0; i<Integer.MAX_VALUE; i++){

                if(i%10 == 0){
                    map2 = new HashMap<>();
                }
                System.out.println(Thread.currentThread().getName()+" : "+ Thread.currentThread().getId());
                map2.put("hepo"+i, new UserBean());
                new UserBean();
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("ok");
        }

    }

    static class DemoC implements Runnable{
        private  Map<String, Object> map2 = new ConcurrentHashMap<>();

        @Override
        public void run() {
            for(int i=0; i<Integer.MAX_VALUE; i++){
                map2.put(i+"12312", new TmpBean());
                try {
                    Thread.sleep(20L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(i%100 == 0)
                    map2.clear();
                System.out.println(Thread.currentThread().getName()+" : "+ Thread.currentThread().getId());
            }
            System.out.println("ok");
        }
    }

    static class TmpBean{

        private byte[] mm = new byte[1024];

        public byte[] get(){
            return mm;
        }
    }

}