package com.iot.unb.SmartMetering;

import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.iot.unb.model.service.Device;


public class StateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

    }

    @Override
    public void onResume(){
        super.onResume();
        TextView carrierView = (TextView) findViewById(R.id.carrier);
        TextView latView = (TextView) findViewById(R.id.latitude);
        TextView longView = (TextView) findViewById(R.id.longitude);
        TextView levelView = (TextView) findViewById(R.id.signal_level);
        TextView dbmView = (TextView) findViewById(R.id.signal_dbm);
        TextView batView = (TextView) findViewById(R.id.baterry_per);
        TextView stateView = (TextView) findViewById(R.id.baterry_state);


        String carrier = Device.getCarrierName();
        double latitude = Device.getLatitude();
        double longitude = Device.getLongitude();
        int signal_level = Device.getSignalStrenght();
        int signal_dbm = Device.getSignaldBm();
        int baterry_percentage = Device.getBatteryPercentage();
        String state = Device.getBatteryState();

        carrierView.setText(carrier);
        String df = new DecimalFormat("###.######").format(latitude);
        latView.setText(String.valueOf(df));
        df = new DecimalFormat("###.######").format(longitude);
        longView.setText(String.valueOf(df));
        levelView.setText(String.valueOf(signal_level));
        dbmView.setText(String.valueOf(signal_dbm));
        batView.setText(String.valueOf(baterry_percentage));
        stateView.setText(state);
    }
}
