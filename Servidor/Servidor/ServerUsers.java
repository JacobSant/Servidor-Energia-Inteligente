package Servidor;
import Model.Database;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static Servidor.Main.database;

public class ServerUsers extends Thread{
    @Override
    public void run(){
        try {

            ServerSocket serverMeter = new ServerSocket(4000);
            while (true) {
                Socket socketMeter = serverMeter.accept();
                new ClientRequest(socketMeter, database.getClients()).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
