package com.example.minkr.jeonju_all.parking.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-10-22.
 */

@Root(name="body")
public class ParkingBodyData {
    @Element(name="totalCount")
    private int totalCount;
    @Element(name="data")
    private ParkingDatas data;

    @Override
    public String toString() {
        return "ParkingBodyData{" +
                "totalCount=" + totalCount +
                ", data=" + data +
                '}';
    }
}
