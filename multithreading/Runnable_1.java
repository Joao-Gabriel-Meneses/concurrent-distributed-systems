public class Runnable_1 implements Runnable{
    public void run(){
        String name = Thread.currentThread().getName();
        System.out.println(name);
    }
}