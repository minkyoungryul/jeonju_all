package com.example.minkr.jeonju_all.facility.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "rfcOpenApi")
public class HealthTotalData {

    @Element(name = "body", required = false)
    private HealthBodyData body;

    public HealthBodyData getBody() {
        return body;
    }

    public void setBody(HealthBodyData body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "HealthTotalData{" +
                "body=" + body +
                '}';
    }
}
