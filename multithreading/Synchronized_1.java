public class Synchronized_1 {
    static int i = - 1;
    public static void main(String[] args) {
        Runnable_1 runnable = new Runnable_1();
        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        Thread t4 = new Thread(runnable);
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        }
    public static class Runnable_1 implements Runnable {
        @Override
        // public void run() acess simultnously (paralelismo)
        public synchronized void run() { // acess one-by-one point has a lock (concorrencia), a boa pratica é ter isso apenas em metodos especificos e nao no run()
            i++;
            String name = Thread.currentThread().getName();
            System.out.println(name + ":" + i);
            }
        }
}