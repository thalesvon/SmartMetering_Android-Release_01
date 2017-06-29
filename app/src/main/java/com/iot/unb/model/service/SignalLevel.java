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
import android.telephony.gsm.GsmCellLocation;


//Atencao ao construtor da classe. Passar contexto da activity quando criar objeto
public class SignalLevel extends Service {
    private final Context mContext;
    static int level;
    static int currentStrength;
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
                GsmCellLocation cellLocation =  (GsmCellLocation) telephonyManager.getCellLocation();
                int cellid =  cellLocation.getCid();
                int lac = cellLocation.getLac();
                int mcc =0;
                int mnc =0;
                String networkOperator = telephonyManager.getNetworkOperator();
                if(strength.isGsm()) {
                    mcc = Integer.parseInt(networkOperator.substring(0, 3));
                    mnc = Integer.parseInt(networkOperator.substring(3));
                }
                System.out.println("MCC: "+mcc);
                System.out.println("MNC: "+mnc);
                System.out.println("CID: "+cellid);
                System.out.println("LAC: "+lac);


                if (true) {
                    String signalStrength = "";
                    currentStrength = strength.getGsmSignalStrength();
                    level = strength.getLevel();
                    if (currentStrength <= 0) {
                        if (currentStrength == 0) {
                            currentStrength= Integer.valueOf(Integer.parseInt(parts[3]));
                        } else {
                            currentStrength = Integer.valueOf(Integer.parseInt(parts[1]));
                        }
                        signalStrength += " dBm";
                    }
                    else if(currentStrength == 99){
                        currentStrength = Integer.valueOf(Integer.parseInt(parts[8])-140);
                    }
                    else {
                        if (currentStrength != 99) {
                            signalStrength = String.valueOf(((2 * currentStrength) - 113));
                            signalStrength += " dBm";
                            currentStrength = 2*currentStrength-113;
                        }
                    }

                    //System.out.println("LEVEL is: " + level+ " "+carrierName);
                    //System.out.println("Signal strength is : " + signalStrength);

                }

            }
        }, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);


    }

    public static int getSignalStrength(){return level;}
    public static String getCarrierName(){ return carrierName;}
    public static int getSignaldBm(){return currentStrength;}

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}


