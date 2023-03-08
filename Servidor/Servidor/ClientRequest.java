package Servidor;

import Model.Client;
import Model.DecodeMessageHTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;

public class ClientRequest extends Thread{
    private Socket socket;
    public ClientRequest(Socket socket, LinkedList<Client> client){
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
            System.out.println(messageHTTP.toString());

            /*
                monitorar consumo historico/total acumulado
                pegar fatura
                alertas
                dados do medidor
             */
            DecodeMessageHTTP messageOut = new DecodeMessageHTTP();
            String messageHTTPOut = messageOut.createMessageHTTP();
            socket.getOutputStream().write(messageHTTPOut.getBytes("UTF-8"));
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}