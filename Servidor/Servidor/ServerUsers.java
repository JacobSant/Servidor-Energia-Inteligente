package Servidor;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerUsers extends Thread{
    @Override
    public void run(){
        try {
            ServerSocket serverMeter = new ServerSocket(9900);
            System.out.println("Abriu 9900");
            while (true) {
                Socket socketUser = serverMeter.accept();
                new ClientRequest(socketUser).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
