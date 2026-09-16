public class ControleAcesso{

    public static void main(String[] args) throws InterruptedException {
        Estadio estadio = new Estadio(200);
        Catraca[] catracas = new Catraca[4];
        
        for (int i = 0; i < catracas.length; i++) {
            catracas[i] = new Catraca("Catraca-" + (i+1), estadio);
            catracas[i].start();
        }
        for (Catraca c: catracas){
            c.join(); // faz therad main aguarar as demais
        }
        estadio.relatorio();
    }
}