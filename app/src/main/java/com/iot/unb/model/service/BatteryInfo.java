package com.iot.unb.model.service;

import android.content.Context;
import android.os.BatteryManager;
import static android.content.Context.BATTERY_SERVICE;


/**
 * Created by Thaleco on 20-May-17.
 */

public class BatteryInfo {
    private final Context mContext;
    static int batLevel;
    static boolean charging;
    public BatteryInfo(Context context){
        this.mContext = context;
        baterryInformation();
    }
    public void baterryInformation() {
        //Nivel de bateria. API >=21
        BatteryManager bm = (BatteryManager) mContext.getSystemService(BATTERY_SERVICE);
        batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        charging = bm.isCharging();
    }

    public static String getBatteryState(){
        String result;
        if(charging){
            result = "Charging";
        }
        else{
            result = "On Battery";
        }
        return result;
    }

    public static int getBatteryPercentage(){
        return batLevel;
    }
}
