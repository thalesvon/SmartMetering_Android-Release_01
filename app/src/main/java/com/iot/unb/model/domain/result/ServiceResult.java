package com.iot.unb.model.domain.result;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aclopesjr on 5/17/17.
 */

public class ServiceResult {

    @SerializedName("service_id")
    private float serviceId;

    @SerializedName("service_name")
    private String name;

    public float getServiceId() {
        return serviceId;
    }

    public void setServiceId(float serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
