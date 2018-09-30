package com.springboot.lxl.proj.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 由ip地址对应的信息的对象模型
 * */
public class IpLocationResult {
    private Integer code;
    private List<LocationResult> data = new ArrayList<LocationResult>();

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<LocationResult> getData() {
        return data;
    }

    public void setData(List<LocationResult> data) {
        this.data = data;
    }
}
