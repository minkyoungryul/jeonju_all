package com.example.minkr.jeonju_all.facility.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "body")
public class ParkBodyData {

    @Element(name = "data", required = false)
    private ParkDatas data;

    public ParkDatas getData() {
        return data;
    }

    public void setData(ParkDatas data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ParkBodyData{" +
                "data=" + data +
                '}';
    }
}
