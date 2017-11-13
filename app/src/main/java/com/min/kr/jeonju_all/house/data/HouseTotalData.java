package com.min.kr.jeonju_all.house.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-06.
 */
@Root(name = "rfcOpenApi")
public class HouseTotalData {

    @Element(name = "body", required = false)
    private HouseBodyData body;

    public HouseBodyData getBody() {
        return body;
    }

    public void setBody(HouseBodyData body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "HouseTotalData{" +
                "body=" + body +
                '}';
    }
}
