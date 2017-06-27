package com.iot.unb.model.domain.register;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.ObjectConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aclopesjr on 5/17/17.
 */

public class Data {

    @SerializedName("service_id")
    private int serviceId;

    @SerializedName("data_values")
    private Map<String, Object> dataValues;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Map<String, Object> getDataValues() {
        return dataValues;
    }

    public void setDataValues(Map<String, Object> dataValues) {
        this.dataValues = dataValues;
    }

    public void addData(String data, Object value) {
        if (null == this.dataValues) {
            this.dataValues = new HashMap<String, Object>();
        }
        this.dataValues.put(data, value);
    }
}
