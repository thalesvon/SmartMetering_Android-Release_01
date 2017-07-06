package com.iot.unb.SmartMetering;

/**
 * Created by Thaleco on 29-Jun-17.
 */

public class LastData {
    public String  state;
    public String latitude;
    public String longitude;
    public String percentage;
    public String carrier;
    public String signal;
    public String signaldbm;
    public String timestamp;

    public LastData() {
    }


    public LastData(String timestamp, String latitude, String longitude, String state, String percentage, String carrier, String signal, String signaldbm) {
        this.timestamp = timestamp;
        this.longitude = latitude;
        this.latitude = longitude;
        this.state = state;
        this.percentage = percentage;
        this.carrier = carrier;
        this.signal = signal;
        this.signaldbm = signaldbm;
    }
    /*public LastData(String timestamp) {
        this.timestamp = timestamp;
    }*/
}