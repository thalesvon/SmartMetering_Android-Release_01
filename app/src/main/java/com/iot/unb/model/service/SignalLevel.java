package com.iot.unb.model.service;

/**
 * Created by Thales on 5/19/2017.
 */


import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;



//Atencao ao construtor da classe. Passar contexto da activity quando criar objeto
public class SignalLevel extends Service {
    private final Context mContext;
    static int level;
    static String carrierName;
    public SignalLevel(Context mContext){
        this.mContext = mContext;
        getNetworkStats();
    }
    public void getNetworkStats() {
        final TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(TELEPHONY_SERVICE);
        telephonyManager.listen(new PhoneStateListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onSignalStrengthsChanged(SignalStrength strength) {
                super.onSignalStrengthsChanged(strength);
                String[] parts = strength.toString().split(" ");
                carrierName = telephonyManager.getNetworkOperatorName();

                if (true) {
                    String signalStrength = "";
                    int currentStrength = strength.getGsmSignalStrength();
                    level = strength.getLevel();
                    /*if (currentStrength <= 0) {
                        if (currentStrength == 0) {
                            signalStrength = String.valueOf(Integer.parseInt(parts[3]));
                        } else {
                            signalStrength = String.valueOf(Integer.parseInt(parts[1]));
                        }
                        signalStrength += " dBm";
                    } else {
                        if (currentStrength != 99) {
                            signalStrength = String.valueOf(((2 * currentStrength) - 113));
                            signalStrength += " dBm";
                        }
                    }

                    System.out.println("LEVEL is: " + level+ " "+carrierName);
                    System.out.println("Signal strength is : " + signalStrength);*/

                }

            }
        }, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);


    }

    public static int getSignalStrength(){return level;}
    public static String getCarrierName(){ return carrierName;}

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}


