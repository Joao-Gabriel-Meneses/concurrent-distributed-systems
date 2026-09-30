import java.io.IOException;
import java.io.ObjectOutputStream; 
import java.net.Socket;   
import java.net.ServerSocket;         
import java.util.Date;            
import javax.swing.JOptionPane;   

public class ServidorTCPBasico {
    public static void main(String[] args){
        try{
            int porta = 12345;
            ServerSocket servidor = new ServerSocket(porta);
            System.out.println("Servidor escutando na porta " + porta + ".");
            while(true){
                Socket cliente = servidor.accept();
                System.out.println("Cliete conectado: " + 
                cliente.getInetAddress().getHostAddress());
                ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
                saida.flush();
                saida.writeObject(new Date());
                saida.close();
                cliente.close();
            }
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
}