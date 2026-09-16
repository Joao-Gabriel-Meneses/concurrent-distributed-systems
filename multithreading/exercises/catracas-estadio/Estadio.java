import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Estadio {
    private final int capacidade;

    private final AtomicInteger publicoAtual = new AtomicInteger(0);

    public final Map<String, Integer> entradasPorCatraca = new ConcurrentHashMap<>();

    public final List<String> log = Collections.synchronizedList(new ArrayList<>());

    Estadio (int capacidade) {
        this.capacidade = capacidade;
    }

    boolean liberarEntrada(String catraca){
        int atual;
        do {
            atual = publicoAtual.get();
            if (atual >= capacidade){
                return false;
            }
        } while (!publicoAtual.compareAndSet(atual, atual + 1));

        int numero = atual +1;
        entradasPorCatraca.merge(catraca, 1, Integer::sum);
        log.add(numero + ";" + catraca);
        System.out.printf("%s liberou a entrada no %d | vagas restantes: %d%n", catraca, numero, capacidade - numero);
        return true;
    }

    void relatorio(){
        System.out.println("\n--Relatorio--");
        System.out.println("Publico total:" + publicoAtual.get());
        System.out.println("Registros no log:" + log.size());
        entradasPorCatraca.forEach((k,v) -> System.out.println(k + ":" + v));
        synchronized(log){
            System.out.println("Primeiro registro: " + log.get(0));
            System.out.println("Ultimo registro: " + log.get(log.size() - 1));
        }
    }
}
