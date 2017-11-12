package com.example.minkr.jeonju_all.facility.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "body")
public class HospitalBodyData {

    @Element(name = "data", required = false)
    private HospitalDatas data;

    public HospitalDatas getData() {
        return data;
    }

    public void setData(HospitalDatas data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HospitalBodyData{" +
                "data=" + data +
                '}';
    }
}
