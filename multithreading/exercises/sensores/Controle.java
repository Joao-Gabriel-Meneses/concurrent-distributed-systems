public class Controle {
    public static void main(String[] args) throws InterruptedException {

        Painel painel = new Painel(50);

        Thread threadPainel = new Thread(painel, "Painel");
        threadPainel.start();

        Thread[] sensores = new Thread[3];
        for (int i = 0; i < sensores.length; i++) {
            sensores[i] = new Thread(new Sensor("Sensor-" + (i + 1), painel), "Sensor-" + (i + 1));
            sensores[i].start();
        }

        Thread.sleep(200);
        painel.parar();

        // (c) espera os produtores pararem antes de deixar o painel encerrar,
        // e o painel so encerra depois de drenar a fila.
        for (Thread sensor : sensores) {
            sensor.join();
        }
        threadPainel.join();

        painel.relatorio();
    }
}
