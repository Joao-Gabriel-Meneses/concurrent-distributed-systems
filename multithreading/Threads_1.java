public class Threads_1 {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println(t.getName());
        //new thread
        Thread t0 = new Thread(new Runnable_1());

        // Runnable com lambda
        Thread t1 = new Thread(() -> System.out.println("LP-III"));

        Thread t2 = new Thread(new Runnable_1());

        //t0.run(); exec in the same thread 
        t0.start(); // exec in a new thread
        t1.start();
        t2.start();
    }
}
