package Servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

import static Servidor.Main.database;

public class ServerMeter extends Thread{
    @Override
    public void run(){
        try {
            System.out.println("Abriu 9800");
            DatagramSocket serverSocket = new DatagramSocket(9800);
            while (true) {
                byte[] receiveData = new byte[200];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String message = new String(receivePacket.getData()).trim();
                System.out.println(message);
                new MeterPost(message).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}