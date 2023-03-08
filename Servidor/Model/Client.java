package Model;
public class Client {
    private long consumptionTotal;
    private int meterID;
    private Reading[] measurementHistory;
    private int dateReading;

    public Client(int meterID, int dataReading){
        this.consumptionTotal = 0;
        this.measurementHistory = new Reading[10];
        this.meterID = meterID;
        this.dateReading = dataReading;

    }


    public int getMeterID() {
        return meterID;
    }

    public void setMeterID(int meterID) {
        this.meterID = meterID;
    }

    public Reading[] getMeasurementHistory() {
        return measurementHistory;
    }

    public void setMeasurementHistory(Reading[] measurementHistory) {
        this.measurementHistory = measurementHistory;
    }

    public int getDateReading() {
        return dateReading;
    }

    public void setDateReading(int dateReading) {
        this.dateReading = dateReading;
    }
    public long getConsumptionTotal() {return consumptionTotal;}

    public void setConsumptionTotal(long consumptionTotal) {
        this.consumptionTotal = consumptionTotal;
    }


    
}
