package com.iot.unb.model.service;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import com.iot.unb.SmartMetering.GPSTracker;
import com.iot.unb.SmartMetering.MainActivity;
import com.iot.unb.SmartMetering.SettingsActivity;


/**
 * Created by aclopesjr on 5/14/17.
 */

public class Device {
    //private Context context = new Context(SettingsActivity.class);
    //
    //Context context = Context().getApplicationContext(SettingsActivity.class);
    //GPSTracker gps = new GPSTracker(SettingsActivity.getContext());


    public static String getUniqueIdentifier() {
        return Settings.Secure.getString(MainActivity.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getDeviceName() {
        return Build.DEVICE;
    }

    public static String getOperationSystem() {
        return Build.VERSION.CODENAME + " " + Build.VERSION.BASE_OS;
    }

    public static String getBrand() {
        return Build.MANUFACTURER;
    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static String getMacAdress() {
        return "";
    }

    public static String getChipset() {
        return Build.HARDWARE;
    }

    public static String getProcessor() {
        return Build.HARDWARE;
    }

    public static float getLatitude() {
        return (float) GPSTracker.getLatitude();
    }

    public static float getLongitude() {
        return (float) GPSTracker.getLongitude();
    }

    public static int getSignalStrenght(){
        return SignalLevel.getSignalStrength();
    }

    public static String getCarrierName(){
        return  SignalLevel.getCarrierName();
    }

    public static String getBatteryState(){
        return BatteryInfo.getBatteryState();
    }

    public static int getBatteryPercentage(){
        return  BatteryInfo.getBatteryPercentage();
    }
}
