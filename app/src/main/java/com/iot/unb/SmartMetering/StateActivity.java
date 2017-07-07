package com.iot.unb.SmartMetering;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.iot.unb.model.service.Device;


public class StateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        TextView carrierView = (TextView) findViewById(R.id.carrier);

        String carrier = Device.getCarrierName();
        System.out.println(carrier);
        carrierView.setText(carrier);
    }
}
