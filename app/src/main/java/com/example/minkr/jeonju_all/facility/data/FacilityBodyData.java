package com.example.minkr.jeonju_all.facility.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "body")
public class FacilityBodyData {

    @Element(name = "data")
    private FacilityDatas data;

    public FacilityDatas getData() {
        return data;
    }

    public void setData(FacilityDatas data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FacilityBodyData{" +
                "data=" + data +
                '}';
    }
}
