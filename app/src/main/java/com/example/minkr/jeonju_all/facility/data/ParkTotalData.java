package com.example.minkr.jeonju_all.facility.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "rfcOpenApi")
public class ParkTotalData {

    @Element(name = "body", required = false)
    private ParkBodyData body;

    public ParkBodyData getBody() {
        return body;
    }

    public void setBody(ParkBodyData body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ParkTotalData{" +
                "body=" + body +
                '}';
    }
}
