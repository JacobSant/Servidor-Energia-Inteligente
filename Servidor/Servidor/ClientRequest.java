package Servidor;

import Model.Client;
import Model.DecodeMessageHTTP;
import Model.Routes;

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
            BufferedReader messageReader = new BufferedReader(inputStreamReader);
            StringBuilder messageHTTP = new StringBuilder();
            while(messageReader.ready()){
                messageHTTP.append((char) messageReader.read());
            }
            System.out.println(messageHTTP.toString());
            if(!messageHTTP.isEmpty()){
                DecodeMessageHTTP messageDecode = new DecodeMessageHTTP();
                messageDecode.decodeMessage(messageHTTP.toString());
                System.out.println(messageDecode.getPath());
                System.out.println(messageDecode.getParameter());

                DecodeMessageHTTP messageOut = new DecodeMessageHTTP();
                Routes rote = new Routes();

                if(messageDecode.getPath().equals("/historic")) {
                    messageOut.setBody(rote.historicClient(messageDecode.getParameter()));
                }
                else if(messageDecode.getPath().equals("/consumption")) {
                    messageOut.setBody(rote.consumptionClient(messageDecode.getParameter()));
                }
                else if(messageDecode.getPath().equals("/invoice")) {
                    messageOut.setBody(rote.invoiceClient(messageDecode.getParameter()));
                }
                else if(messageDecode.getPath().equals("/alerts")) {
                    messageOut.setBody(rote.alertClient(messageDecode.getParameter()));
                }

                String messageHTTPOut = messageOut.createMessageHTTP();
                socket.getOutputStream().write(messageHTTPOut.getBytes("UTF-8"));
            }

            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}