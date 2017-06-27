package com.iot.unb.model.domain.register;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aclopesjr on 5/17/17.
 */

public class DataRegister extends Register {

    @SerializedName("data")
    private List<Data> datas;

    @SerializedName("token")
    private String token;

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void addData(Data data) {
        if (null == this.datas) {
            this.datas = new ArrayList<Data>();
        }
        this.datas.add(data);
    }

    public DataRegister() {
        super();
        setToken(getTokenId());
    }
}
