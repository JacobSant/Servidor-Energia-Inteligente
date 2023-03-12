package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static Servidor.Main.database;

public class ServerMeter extends Thread{
    @Override
    public void run(){
        try {
            System.out.println("Abriu 5000");
            ServerSocket serverMeter = new ServerSocket(5000);
            while (true) {
                Socket socketMeter = serverMeter.accept();
                new MeterPost(socketMeter).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}