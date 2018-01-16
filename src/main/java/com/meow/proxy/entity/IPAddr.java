package com.meow.proxy.entity;

import com.alibaba.fastjson.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IPAddr {
    private String country;
    private String province;
    private String city;
    private String isp;

    @Override
    public String toString() {
        return JSONObject.toJSON(this).toString();
    }
}
