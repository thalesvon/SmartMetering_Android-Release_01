package com.iot.unb.model.domain.register;

import com.google.gson.annotations.SerializedName;
import com.iot.unb.model.service.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aclopesjr on 5/9/17.
 */
public class AutoRegister {

    @SerializedName("name")
    private String name;

    @SerializedName("chipset")
    private String chipset;

    @SerializedName("mac")
    private String mac;

    @SerializedName("serial")
    private String serial;

    @SerializedName("processor")
    private String processor;

    @SerializedName("channel")
    private String channel;

    @SerializedName("client_time")
    private String client_time;

    @SerializedName("tag")
    private List<String> tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getClient_time() {
        return client_time;
    }

    public void setClient_time(String client_time) {
        this.client_time = client_time;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public AutoRegister() {
        super();
        setName(Device.getDeviceName());
        setChipset(Device.getChipset());
        setMac(Device.getMacAdress());
        setSerial(Device.getUniqueIdentifier());
        setProcessor("processor");
        setChannel("Ethernet");

        Long timeStamp = System.currentTimeMillis()/1000;
        setClient_time(timeStamp.toString());

        List<String> tags = new ArrayList<String>();
        tags.add("Android");
        tags.add("Auto Register");
        setTags(tags);
    }
}
