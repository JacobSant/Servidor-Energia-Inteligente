package Model;

import java.util.LinkedList;

public class Database {
    private static LinkedList<Client> clients = new LinkedList();


    public synchronized LinkedList<Client> getClients() {
        return clients;
    }

    public synchronized void setClients(LinkedList<Client> clients) {
        this.clients = clients;
    }
}
