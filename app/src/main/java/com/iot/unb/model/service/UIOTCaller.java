package com.iot.unb.model.service;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.iot.unb.SmartMetering.SettingsActivity;
import com.iot.unb.model.domain.result.AutoRegisterResult;
import com.iot.unb.model.domain.result.ServiceRegisterResult;
import com.iot.unb.model.domain.result.ServiceResult;

/**
 * Created by Thaleco on 05-Jul-17.
 */

public class UIOTCaller {

    private static Context mcontext;

    public UIOTCaller(){

    }

    public void passContext(Context context){
        this.mcontext=context;
    }


    public void autoRegister() {

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
                    if(mcontext!=null) {
                        Toast.makeText(mcontext, String.format("The device has successfuly been auto registered with services: %s", servicesName), Toast.LENGTH_LONG).show();
                    }
                    }
                }, errorListener);
            }
        }, errorListener);
    }

    public void dataCollection() {
        RaiseUIOT.collectDataForAllServices(new Raise.Listener<String>() {
            @Override
            public void onSucces(String response) {
                if(mcontext!=null) {
                    Toast.makeText(mcontext, "Data have all successfuly been collected!", Toast.LENGTH_LONG).show();
                    mcontext =null;
                }
                else{
                    System.out.println("Data have all successfuly been collected!");
                }
            }
        }, new Raise.ErrorListener() {
            @Override
            public void onError(VolleyError error) {
                if(mcontext!=null) {
                    Toast.makeText(mcontext, "Error Collectiong Data!", Toast.LENGTH_LONG).show();
                    mcontext =null;
                }
                else{
                    System.out.println("Error Collecting Data");
                }
            }
        });
    }

}
