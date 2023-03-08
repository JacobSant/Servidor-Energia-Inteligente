package Model;

public class Reading {
    private int consumption;
    private String dateMeasurement;
    private String hours;

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Reading(int consumption, String dateMeasurement, String hours) {
        this.consumption = consumption;
        this.dateMeasurement = dateMeasurement;
        this.hours = hours;
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
