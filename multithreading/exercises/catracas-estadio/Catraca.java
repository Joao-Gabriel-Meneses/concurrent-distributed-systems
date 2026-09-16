public class Catraca extends Thread {
    private final Estadio estadio;

    Catraca(String nome, Estadio estadio){
        super(nome);
        this.estadio = estadio;
    }

    @Override
    public void run(){
        while (estadio.liberarEntrada(getName())){
            try{
                Thread.sleep(5);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}