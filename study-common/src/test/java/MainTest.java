import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainTest {

    public static void main(String[] args) {


        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);

        service.schedule(new MyRunnable(), 3, TimeUnit.SECONDS);
        service.schedule(new MyRunnable(), 5, TimeUnit.SECONDS);
        service.shutdown();

        System.out.println("=====");
        System.out.println(1 << 0);
        System.out.println(1 << 1);
        System.out.println(1 << 2);

        System.out.println(new Date(1573637851000L));


    }


    private static class MyRunnable implements Runnable {

        public void run() {
            System.out.println("执行成功");
        }
    }


}


