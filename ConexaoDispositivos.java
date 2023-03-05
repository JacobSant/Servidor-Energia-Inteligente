import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
//import Model.FormatResponseHTTP;s

public class ConexaoDispositivos {
    public static void main(String[] args) throws IOException{
        while(true){
        ServerSocket server = new ServerSocket(4000); // Servidor
        System.out.println("Servidor on");
        Socket socket = server.accept();                   // Accept do cliente
        System.out.println("Cliente conectou");

        InputStreamReader inputReader = new InputStreamReader(socket.getInputStream());
        System.out.println("x");
        PrintStream output = new PrintStream(socket.getOutputStream());
        System.out.println("x");
        BufferedReader reader = new BufferedReader(inputReader);
        System.out.println("x");

        //ClientRequest clientRequest = new ClientRequest(socket);
        //String msg = "HTTP/1.1 200 ok\r\nlinha\r\n\r\n";
        String x;

        while((x = reader.readLine()) != null){
            System.out.println("s");
            
            System.out.println(x);
            output.println("veio");
        }
    }}
}