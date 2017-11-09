package com.example.minkr.jeonju_all.parking.data;

import com.example.minkr.jeonju_all.facility.data.ParkBodyData;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "rfcOpenApi")
public class ParkingTotalData {

    @Element(name = "body")
    private ParkingBodyData body;

    public ParkingBodyData getBody() {
        return body;
    }

    public void setBody(ParkingBodyData body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ParkingTotalData{" +
                "body=" + body +
                '}';
    }
}
