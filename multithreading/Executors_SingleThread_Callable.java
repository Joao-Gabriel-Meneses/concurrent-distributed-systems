import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Executors_SingleThread_Callable {
    /*
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();
            executor.execute(new MeuRunnable()); // "execute" sem retorno
            Future<?> future = executor.submit(new MeuRunnable()); // "sumbit" me retornando uma forma de verif o status
            System.out.println(future.isDone());
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println(future.isDone());
        } catch (Exception e) {
        throw e;
        } finally {
        if (executor != null) {
        executor.shutdownNow();
        }
        }
    }
        */

    public static void main(String[] args) throws Exception {
        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();
            Future<String> future = executor.submit((Callable<T>) new MeuCallable());
            System.out.println(future.isDone());
            System.out.println(future.get());
            // System.out.println(future.get(1, TimeUnit.SECONDS));
            System.out.println(future.isDone());
        } catch (Exception e) {
        throw e;
        } finally {
        if (executor != null) {
        executor.shutdownNow();
        }
        }
    }
    public static class MeuRunnable implements Runnable {
        public void run() {
            String nome = Thread.currentThread().getName();
            System.out.println(nome + ": LP-III");
        }
    }
    public static class MeuCallable implements Callable<String> {
        public String call() throws Exception {
        // Thread.sleep(1000);
        String nome = Thread.currentThread().getName();
        int nextInt = new Random().nextInt();
        return nome + ": LP-III " + nextInt;
        }
    }
}

