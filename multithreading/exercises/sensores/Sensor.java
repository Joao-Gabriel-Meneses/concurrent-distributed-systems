import java.util.concurrent.ThreadLocalRandom;

public class Sensor implements Runnable {
    private final String nome;
    private final Painel painel;

    Sensor(String nome, Painel painel) {
        this.nome = nome;
        this.painel = painel;
    }

    String getNome() {
        return nome;
    }

    @Override
    public void run() {
        try {
            while (!painel.estaParado() && !Thread.currentThread().isInterrupted()) {
                int valor = ThreadLocalRandom.current().nextInt(0, 100);
                if (!painel.publicar(nome, valor)) {
                    return;
                }
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
