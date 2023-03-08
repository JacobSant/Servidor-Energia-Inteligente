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

            // "0000" 00 DD/MM/AAAA HH:mm:ss = ID CONSSUMO DATA hora
            String[] data = messageHTTP.toString().split(" ");
            LinkedList<Client> list = database.getClients();
            boolean notFind = true;
            if(list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(list.get(i).getMeterID());
                    if (list.get(i).getMeterID().equals(data[0])) {
                        list.get(i).updateconsumption(Integer.parseInt(data[1]), data[2], data[3]);
                        notFind = false;
                        database.setClients(list);
                    }
                }
            }
            if(notFind && data.length > 1){
                System.out.println("Cadastrou");
                Client newClient = new Client(data[0],data[2]);
                database.getClients().add(newClient);
            }

            //System.out.println(list.get(0).getConsumptionTotal());
            //System.out.println(list.get(0).getDatePayment());
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}