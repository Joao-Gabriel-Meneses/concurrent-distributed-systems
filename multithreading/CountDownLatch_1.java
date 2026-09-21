import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountDownLatch_1 {
    private static volatile int i = 0;
    private static volatile CountDownLatch latch = new CountDownLatch(3);
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        Runnable r1 = () -> {
            int j = new Random().nextInt();
            int x = i * j;
            System.out.println(i + " x " + j + " = " + x);
            latch.countDown();
        };
        executor.scheduleAtFixedRate(r1, 0, 1, TimeUnit.SECONDS);
        while (true) { 
            await();
            i = new Random().nextInt();
            latch = new CountDownLatch(3);
        }
    }
    public static void await(){ // espera até que uma condição específica aconteça (diferente de join e sleep)
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}