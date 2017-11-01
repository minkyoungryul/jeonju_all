package com.example.minkr.jeonju_all.parking.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-10-22.
 */

@Root(name = "rfcOpenApi")
public class ParkingTotalData {
    @Element(name = "body")
    ParkingBodyData body;

    @Override
    public String toString() {
        return "ParkingTotalData{" +
                "body=" + body +
                '}';
    }
}
