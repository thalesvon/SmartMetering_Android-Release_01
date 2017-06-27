package com.iot.unb.model.domain.result;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aclopesjr on 5/17/17.
 */

public class ServiceRegisterResult {

    public static final String fileName = "service_register.json";

    @SerializedName("services")
    private List<ServiceResult> services;

    @SerializedName("tokenId")
    private String tokenId;

    public List<ServiceResult> getServices() {
        return services;
    }

    public void setServices(List<ServiceResult> services) {
        this.services = services;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
