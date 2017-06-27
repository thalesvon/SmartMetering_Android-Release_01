package com.iot.unb.model.domain.register;

import com.google.gson.annotations.SerializedName;
import com.iot.unb.model.domain.result.AutoRegisterResult;
import com.iot.unb.model.service.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aclopesjr on 5/15/17.
 */

public class Register {

    @SerializedName("tokenId")
    private String tokenId;

    @SerializedName("client_time")
    private String clientTime;

    @SerializedName("tag")
    private List<String> tags;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getClientTime() {
        return clientTime;
    }

    public void setClientTime(String clientTime) {
        this.clientTime = clientTime;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Register() {
        super();

        AutoRegisterResult autoRegisterResult = Storage.load(AutoRegisterResult.fileName, AutoRegisterResult.class);
        if (autoRegisterResult != null && !autoRegisterResult.getTokenId().isEmpty()) {
            setTokenId(autoRegisterResult.getTokenId());
        }
        Long timeStamp = System.currentTimeMillis()/1000;
        setClientTime(timeStamp.toString());

        List<String> tags = new ArrayList<String>();
        tags.add("Android");
        tags.add("Smart Metering Homol");
        setTags(tags);
    }
}
