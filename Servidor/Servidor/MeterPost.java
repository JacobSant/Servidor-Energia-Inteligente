package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import static Servidor.Main.database;


public class MeterPost extends Thread{
    private Socket socket;
    public MeterPost(Socket socket){
        this.socket = socket;

    }
    @Override
    public void run(){
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader message = new BufferedReader(inputStreamReader);
            StringBuilder messageHTTP = new StringBuilder();
            while(message.ready()){
                messageHTTP.append((char) message.read());
            }

            // 0000 00 000000 = ID CONSSUMO DATA
            String[] data = messageHTTP.toString().split(" ");
            database.getClients();




        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}