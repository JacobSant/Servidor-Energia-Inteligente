package Model;
import java.sql.Date;

public class Invoice {
    private float COST = (float) 0.06; // Preço do kW/h
    private int dateRead;
    private int idClient;
    private float value;
    private long totalConsumption;
    
    public Invoice(int date, int idClient, long totalConsumption){
        this.dateRead = date;
        this.idClient = idClient;
        this.value = totalConsumption*COST;
    }

    public int getDateRead() {
        return dateRead;
    }

    public void setDateRead(int dateRead) {
        this.dateRead = dateRead;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public long getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(long totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    

    
}
