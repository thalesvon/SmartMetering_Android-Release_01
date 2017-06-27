package com.iot.unb.model.domain.register;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aclopesjr on 5/15/17.
 */

public class Service {

    @SerializedName("name")
    private String name;

    @SerializedName("parameters")
    private Map<String, String> serviceParameters;

    @SerializedName("return_type")
    private List<String> returnTypes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getServiceParameters() {
        return serviceParameters;
    }

    public void setServiceParameters(Map<String, String> serviceParameters) {
        this.serviceParameters = serviceParameters;
    }

    public List<String> getReturnTypes() {
        return returnTypes;
    }

    public void setReturnTypes(List<String> returnTypes) {
        this.returnTypes = returnTypes;
    }

    public void addParameter(String parameter, String type) {
        if (null == serviceParameters) {
            serviceParameters = new HashMap<String, String>();
        }
        serviceParameters.put(parameter, type);

        if (null == returnTypes) {
            returnTypes = new ArrayList<String>();
        }
        returnTypes.add(type);
    }
}
