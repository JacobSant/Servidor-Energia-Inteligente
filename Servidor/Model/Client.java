package Model;

public class Client {
    private long consumptionTotal;
    private String meterID;
    private Reading[] measurementHistory;
    private String datePayment;
    private Reading overconsumption;
    private boolean overconsumptionAlert = false;
    private boolean variationAlert = false;
    private Float[] invoiceHistory = new Float[10];

    public void averageConsumption(Float newInvoice){
        float average = 0;
        int cont = 0;
        for(int i = 0; i < invoiceHistory.length; i++){
            if(invoiceHistory[i] != null){
                cont++;
                average += invoiceHistory[i];
            }
        }
        variationAlert = ((cont>=3)?((newInvoice+newInvoice*0.3 > average/cont)? true:false):false);
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

    public void updadteInvoiceHistory(Float cost){
        if(invoiceHistory[9] != null){
            for(int j = 0; j<invoiceHistory.length-1; j++){
                measurementHistory[j] = measurementHistory[j+1];
            }
        }
        else{
            for(int i = 0; i < invoiceHistory.length; i++){
                if(invoiceHistory[i] == null){
                    invoiceHistory[i] = cost;
                }
            }
            invoiceHistory[9] = cost;
        }
        averageConsumption(cost);
    }

    public void updateconsumption(int meter, String date, String hour){
        Reading reading = new Reading(meter, date, hour);
        if(meter > 100){
            overconsumptionAlert = true;
            overconsumption = reading;
        }
        updadteHistory(reading);
        consumptionTotal+=meter;
    }

    public String isOverconsumptionAlert() {
        if(overconsumptionAlert){
            overconsumptionAlert = false;
            return "\nALERTA!!!\nHouve um registro de um caso de consumo excessivo\nNo dia "+overconsumption.getDateMeasurement()+" às "+overconsumption.getHours()+"\nO consumo foi"+ overconsumption.getConsumption() +"kW/h";
        }
        return "\nVocê não tem novos alertas de consumo excessivo";
    }

    public String isVariationAlert() {
        if(variationAlert){
            variationAlert = false;
            return "\nALERTA!!!\nHouve registro de um caso de grande variação no seu consumo";
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
