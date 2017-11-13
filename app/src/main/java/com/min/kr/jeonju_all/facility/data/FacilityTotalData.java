package com.min.kr.jeonju_all.facility.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "rfcOpenApi")
public class FacilityTotalData {

    @Element(name = "body", required = false)
    private FacilityBodyData body;

    public FacilityBodyData getBody() {
        return body;
    }

    public void setBody(FacilityBodyData body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "FacilityTotalData{" +
                "body=" + body +
                '}';
    }
}
