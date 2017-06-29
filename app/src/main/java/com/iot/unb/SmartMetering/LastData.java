package com.iot.unb.SmartMetering;

/**
 * Created by Thaleco on 29-Jun-17.
 */

public class LastData {
    public String latitude;
    public String longitude;

    public LastData() {
    }

    public LastData(String latitude, String longitude) {
        this.longitude = latitude;
        this.latitude = longitude;
    }
}