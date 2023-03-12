package Model;

public class Client {
    private long consumptionTotal;
    private String meterID;
    private Reading[] measurementHistory;
    private String datePayment;
    private Reading[] dataAlert = new Reading[2];
    private boolean overconsumptionAlert = false;
    private boolean variationAlert = false;

    private float averageConsumption(){
        int average = 0;
        int cont = 0;
        for(int i = 0; i < measurementHistory.length; i++){
            if(measurementHistory[i] != null){
                average += measurementHistory[i].getConsumption();
                cont++;
            }
        }
        return ((cont != 0)? average/cont: 0);
    }

    public Client(String meterID, String dataReading){
        this.consumptionTotal = 0;
        this.measurementHistory = new Reading[10];
        this.meterID = meterID;
        this.datePayment = dataReading;
    }

    private void updadteHistory(Reading reading){
        for(int i = 0; i < measurementHistory.length; i++){
            if(measurementHistory[i] == null){
                measurementHistory[i] = reading;
                return;
            }
        }
        for(int j = 0; j<measurementHistory.length-1; j++){
            measurementHistory[j] = measurementHistory[j+1];
        }
        measurementHistory[9] = reading;
    }

    public void updateconsumption(int meter, String date, String hour){
        Reading reading = new Reading(meter, date, hour);
        if(meter+(meter*0.3) > averageConsumption()){
            variationAlert = true;
            dataAlert[1] = reading;
        }
        if(meter > 100){
            overconsumptionAlert = true;
            dataAlert[0] = reading;
        }
        updadteHistory(reading);
        consumptionTotal+=meter;
    }

    public String isOverconsumptionAlert() {
        if(overconsumptionAlert){
            overconsumptionAlert = false;
            return "\nALERTA!!!\nHouve um registro de um caso de consumo excessivo\nNo dia "+dataAlert[0].getDateMeasurement()+" às "+dataAlert[0].getHours()+"\nO consumo foi"+ dataAlert[0].getConsumption() +"kW/h";
        }
        return "\nVocê não tem novos alertas de consumo excessivo";
    }

    public String isVariationAlert() {
        if(variationAlert){
            variationAlert = false;
            return "\nALERTA!!!\nHouve um registro de um caso de grande variação no seu consumo\nNo dia "+dataAlert[1].getDateMeasurement()+" às "+dataAlert[1].getHours()+"\nO consumo foi"+ dataAlert[1].getConsumption() +"kW/h";
        }
        return "\nVocê não tem novos alertas de grande variação no seu consumo";
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
