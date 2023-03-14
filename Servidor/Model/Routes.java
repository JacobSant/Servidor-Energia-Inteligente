package Model;

import java.util.Date;
import java.util.LinkedList;
import static Servidor.Main.database;

public class Routes {


    private Client getClient(String id){
        String[] idClient = id.split("=");
        LinkedList<Client> list = database.getClients();
        if(list.size() != 0){
            System.out.println(list.get(0).getMeterID() + "aquii");

            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getMeterID().equals(idClient[1])){
                    return list.get(i);
                }
            }
            System.out.println(list.get(0).getMeterID() + "aquii");
            return null;
        }
        else{
            return null;
        }
    }

    private void updateDatePayment(Client client, String date){
        LinkedList<Client> list = database.getClients();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getMeterID() == client.getMeterID()){
                list.get(i).setDatePayment(date);
                database.setClients(list);
            }
        }
    }

    private String buildExpirationDate(String date){
        String[] editDate = date.split("/");
        int month = Integer.parseInt(editDate[1]);
        int year = Integer.parseInt(editDate[2]);

        if(month < 12){
            month++;
        }else{
            month=1;
            year+= 1;
        }
        String newDate = editDate[0]+"/"+month+"/"+year;
        return newDate;
    }

    public String consumptionClient(String id){
        Client client = getClient(id);
        if(client != null){
            String consumption = "Consumo total = "+ client.getConsumptionTotal()+"kWh";
            return consumption;

        }else{
            return "Cliente não cadastrado";
        }
    }

    public String historicClient(String id){
        Client client = getClient(id);
        if(client != null) {
            String historic = "Histórico das ultimas 10 medições realizadas\nConsumo/ Dia da leitura/ Horario da leitura\n";
            Reading[] list = client.getMeasurementHistory();
            for (int i = 0; i < list.length; i++) {
                if (list[i] != null) {
                    historic += list[i].getConsumption() + "/" + list[i].getDateMeasurement() + "/" + list[i].getHours() + "\n";
                }
            }
            return historic;
        }else{
            return "Cliente não cadastrado";
        }
    }

    public String invoiceClient(String id){
        Client client = getClient(id);
        if(client != null) {
            float costKW = (float) 0.06;
            float costInvoice = client.getConsumptionTotal() * costKW;
            String dueDate = buildExpirationDate(client.getDatePayment());
            String invoice = "Fatura\nMatrícula do cliente: " + client.getMeterID() + "\nValor da fatura: " + costInvoice + "\nData de vencimento: " + dueDate + "\n Total Consumido: " + client.getConsumptionTotal() + "kWh";
            client.setConsumptionTotal(0);
            updateDatePayment(client, dueDate);
            return invoice;
        }else{
            return "Cliente não cadastrado";
        }
    }

    public String alertClient(String id){
        Client client = getClient(id);
        if(client != null) {
            String alerts = client.isOverconsumptionAlert() + client.isVariationAlert();
            LinkedList<Client> list = database.getClients();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getMeterID() == client.getMeterID()) {
                    database.getClients().set(i, client);
                }
            }
            return alerts;
        }else{
            return "Cliente não cadastrado";
        }
    }
}
