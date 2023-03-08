package Servidor;

import Model.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;

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

            // "0000" 00 DD/MM/AAAA = ID CONSSUMO DATA
            String[] data = messageHTTP.toString().split(" ");
            LinkedList<Client> list = database.getClients();

            boolean notFind = true;
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getMeterID() == data[0]){
                    list.get(i).updateconsumption(Integer.parseInt(data[1]),data[2]);
                    notFind = false;
                }
            }
            if(notFind){
                Client newClient = new Client(data[0],data[2]);
                database.getClients().add(newClient);
            }
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}