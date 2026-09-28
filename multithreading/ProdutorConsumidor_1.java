import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProdutorConsumidor_1 {

    private static final int MAX_ARRAY = 10;    
    private static final List<Integer> LISTA = new ArrayList<>(MAX_ARRAY);

    public static void main(String[] args){

        Thread produtor = new Thread(() -> {
            while(true){
                try {
                    simulaProcessamento();
                    synchronized(LISTA) {
                        if(LISTA.size() == MAX_ARRAY){
                            System.out.println("!!! Produtor dormindo (Lista cheia)...");
                            LISTA.wait();
                        }
                        System.out.println("Produzindo...");
                        Random random = new Random();
                        int numero = random.nextInt(10000);
                        LISTA.add(numero);

                        LISTA.notifyAll();
                    }
                } catch(Exception e){
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        Thread consumidor = new Thread(() -> {
            while(true){
                try {
                    simulaProcessamento();
                    synchronized(LISTA) {
                        if(LISTA.isEmpty()){
                            System.out.println("??? Consumidor dormindo?");
                            LISTA.wait();
                        }

                        System.out.println("Consumindo...");
                        // Remove o primeiro elemento da lista (índice 0)
                        int numero = LISTA.remove(0); 

                        LISTA.notifyAll();
                    }
                } catch(Exception e){
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        Janelas.monitore(() -> String.valueOf(LISTA.size()));

        produtor.start();
        consumidor.start();
    }

    private static void simulaProcessamento(){
        int tempo = new Random().nextInt(40);
        try{
            Thread.sleep(tempo);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}