package com.example.minkr.jeonju_all.parking.data;

import com.example.minkr.jeonju_all.facility.data.ParkDatas;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "body")
public class ParkingBodyData {

    @Element(name = "data", required = false)
    private ParkingDatas data;

    public ParkingDatas getData() {
        return data;
    }

    public void setData(ParkingDatas data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ParkingBodyData{" +
                "data=" + data +
                '}';
    }
}
