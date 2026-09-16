import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Painel implements Runnable {
    private static final long TIMEOUT_POLL_MS = 100;

    private final BlockingQueue<String> fila;

    private volatile boolean sinalDeParada;

    private final AtomicInteger produzidas = new AtomicInteger(0);
    private final AtomicInteger processadas = new AtomicInteger(0);

    private final Map<String, Integer> leiturasPorSensor = new ConcurrentHashMap<>();

    private final List<String> log = Collections.synchronizedList(new ArrayList<>());

    Painel(int capacidade) {
        this.fila = new ArrayBlockingQueue<>(capacidade);
    }

    boolean estaParado() {
        return sinalDeParada;
    }

    void parar() {
        sinalDeParada = true;
    }

    /** Chamado pelos sensores. Bloqueia se a fila estiver cheia (sem espera ativa). */
    boolean publicar(String sensor, int valor) throws InterruptedException {
        if (sinalDeParada) {
            return false;
        }
        fila.put(sensor + ";" + valor);
        produzidas.incrementAndGet();
        return true;
    }

    /** Laco do painel: consome ate receber o sinal de parada E esvaziar a fila. */
    @Override
    public void run() {
        try {
            while (!sinalDeParada || !fila.isEmpty()) {
                String leitura = fila.poll(TIMEOUT_POLL_MS, TimeUnit.MILLISECONDS);
                if (leitura != null) {
                    consumir(leitura);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void consumir(String leitura) {
        int numero = processadas.incrementAndGet();
        String sensor = leitura.substring(0, leitura.indexOf(';'));
        leiturasPorSensor.merge(sensor, 1, Integer::sum);
        log.add(numero + ";" + leitura);
        System.out.printf("[painel] leitura %d | %s | na fila: %d%n", numero, leitura, fila.size());
    }

    void relatorio() {
        System.out.println("\n--Relatorio--");
        System.out.println("Leituras produzidas: " + produzidas.get());
        System.out.println("Leituras processadas: " + processadas.get());
        System.out.println("Leituras perdidas: " + (produzidas.get() - processadas.get()));
        System.out.println("Registros no log: " + log.size());
        System.out.println("Sobrou na fila: " + fila.size());
        leiturasPorSensor.forEach((k, v) -> System.out.println(k + ": " + v));
        synchronized (log) {
            if (log.isEmpty()) {
                System.out.println("Nenhum registro no log.");
            } else {
                System.out.println("Primeiro registro: " + log.get(0));
                System.out.println("Ultimo registro: " + log.get(log.size() - 1));
            }
        }
    }
}
