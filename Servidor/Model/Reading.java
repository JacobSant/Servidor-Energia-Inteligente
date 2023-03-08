package Model;
import java.util.Date;

public class Reading {
    private int consumption;
    private String dateMeasurement;

    public Reading(int consumption, String dateMeasurement) {
        this.consumption = consumption;
        this.dateMeasurement = dateMeasurement;
    }

    public int getConsumption() {
        return consumption;
    }
    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }
    public String getDateMeasurement() {
        return dateMeasurement;
    }
    public void setDateMeasurement(String dateMeasurement) {
        this.dateMeasurement = dateMeasurement;
    }
}
