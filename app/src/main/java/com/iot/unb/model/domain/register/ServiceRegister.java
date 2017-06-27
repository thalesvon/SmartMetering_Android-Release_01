package com.iot.unb.model.domain.register;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aclopesjr on 5/15/17.
 */

public class ServiceRegister extends Register {

    @SerializedName("services")
    private List<Service> services;

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void addService(Service service) {
        if (this.services == null) {
            this.services = new ArrayList<Service>();
        }
        this.services.add(service);
    }
}
