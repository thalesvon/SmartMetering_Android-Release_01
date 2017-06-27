package com.iot.unb.SmartMetering;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.iot.unb.model.domain.result.AutoRegisterResult;
import com.iot.unb.model.domain.result.ServiceRegisterResult;
import com.iot.unb.model.domain.result.ServiceResult;
import com.iot.unb.model.service.BatteryInfo;
import com.iot.unb.model.service.Raise;
import com.iot.unb.model.service.RaiseUIOT;
import com.iot.unb.SmartMetering.GPSTracker;
import com.iot.unb.model.service.SignalLevel;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private static Context context;

    /**
     * Gets the <{@link MainActivity}/> context.
     * @return <{@link Context}/>.
     */
    public static Context getContext() {
        return context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Settings");
        context = getBaseContext();
        new GPSTracker(SettingsActivity.this);
        new SignalLevel(SettingsActivity.this);
        new BatteryInfo(SettingsActivity.this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnForceAutoRegister) {
            autoRegisterClick();
        } else if (view.getId() == R.id.btnRevalidateAutoRegister) {

        } if (view.getId() == R.id.btnForceDataCollection) {
            dataCollectionClick();
        }
    }

    private void autoRegisterClick() {

        final Raise.ErrorListener errorListener = new Raise.ErrorListener() {
            @Override
            public void onError(VolleyError error) {

            }
        };

        //Starts the process of auto registering
        RaiseUIOT.autoRegister(new Raise.Listener<AutoRegisterResult>() {
            @Override
            public void onSucces(AutoRegisterResult response) {
                //Starts register all services
                RaiseUIOT.registerAllServices(new Raise.Listener<ServiceRegisterResult>() {
                    @Override
                    public void onSucces(ServiceRegisterResult response) {

                        String servicesName = "";
                        for (ServiceResult service : response.getServices()) {
                            servicesName =  servicesName.concat(service.getName());
                            int pos = response.getServices().indexOf(service) + 1;
                            if (pos < response.getServices().size()) {
                                servicesName = servicesName.concat(", ");
                            }
                        }

                        Toast.makeText(SettingsActivity.this, String.format("The device has successfuly been auto registered with services: %s", servicesName), Toast.LENGTH_LONG).show();
                    }
                }, errorListener);
            }
        }, errorListener);
    }

    private void dataCollectionClick() {
        RaiseUIOT.collectDataForAllServices(new Raise.Listener<String>() {
            @Override
            public void onSucces(String response) {
                Toast.makeText(SettingsActivity.this, "Datas have all successfuly been collected!", Toast.LENGTH_LONG).show();
            }
        }, new Raise.ErrorListener() {
            @Override
            public void onError(VolleyError error) {
                Toast.makeText(SettingsActivity.this, "Error Collectiong Datas!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
