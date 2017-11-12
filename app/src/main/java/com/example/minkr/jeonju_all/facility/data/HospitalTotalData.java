package com.example.minkr.jeonju_all.facility.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "rfcOpenApi")
public class HospitalTotalData {

    @Element(name = "body", required = false)
    private HospitalBodyData body;

    public HospitalBodyData getBody() {
        return body;
    }

    public void setBody(HospitalBodyData body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "HospitalTotalData{" +
                "body=" + body +
                '}';
    }
}
