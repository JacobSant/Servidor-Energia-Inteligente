package Servidor;
import Model.Database;
import java.io.*;
public class Main {
    public static Database database = new Database();
    public static void main(String[] args) throws IOException {
        new ServerMeter().start(); // Servidor responsavel pelos medidores
        new ServerUsers().start(); // Servidor responsavel pelos acessos dos usuários
    }
}
