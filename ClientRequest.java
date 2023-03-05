import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLOutput;

public class ClientRequest extends Thread{
    private Socket socket;

    public ClientRequest(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            InputStreamReader inputReader = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(inputReader);
            String message;
            while((message = reader.readLine()) != null){
                System.out.print(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}