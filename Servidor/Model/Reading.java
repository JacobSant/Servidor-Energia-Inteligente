package Model;
import java.util.Date;

public class Reading {
    private int consumption;
    private Date dateMeasurement;
    private int idMeter;

    public int getIdMeter() {
        return idMeter;
    }

    public void setIdMeter(int idMeter) {
        this.idMeter = idMeter;
    }

    public int getConsumption() {
        return consumption;
    }
    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }
    public Date getDateMeasurement() {
        return dateMeasurement;
    }
    public void setDateMeasurement(Date dateMeasurement) {
        this.dateMeasurement = dateMeasurement;
    }
}
