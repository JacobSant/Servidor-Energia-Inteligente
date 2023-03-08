package Model;

public class Client {
    private long consumptionTotal;
    private String meterID;
    private Reading[] measurementHistory;
    private String datePayment;

    public Client(String meterID, String dataReading){
        this.consumptionTotal = 0;
        this.measurementHistory = new Reading[10];
        this.meterID = meterID;
        this.datePayment = dataReading;
    }

    private void updadteHistory(Reading reading){
        for(int i = 0; i < 10; i++){
            if(measurementHistory[i] == null){
                measurementHistory[i] = reading;
                return;
            }
        }
        for(int j = 0; j<9; j++){
            measurementHistory[j] = measurementHistory[j+1];
        }
        measurementHistory[9] = reading;
    }

    public void updateconsumption(int meter, String date){
        Reading reading = new Reading(meter, date);
        updadteHistory(reading);
        consumptionTotal+=meter;
    }

    public String getMeterID() {
        return meterID;
    }

    public void setMeterID(String meterID) {
        this.meterID = meterID;
    }

    public Reading[] getMeasurementHistory() {
        return measurementHistory;
    }

    public void setMeasurementHistory(Reading[] measurementHistory) {
        this.measurementHistory = measurementHistory;
    }

    public String getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(String datePayment) {
        this.datePayment = datePayment;
    }
    public long getConsumptionTotal() {return consumptionTotal;}

    public void setConsumptionTotal(long consumptionTotal) {
        this.consumptionTotal = consumptionTotal;
    }


    
}
