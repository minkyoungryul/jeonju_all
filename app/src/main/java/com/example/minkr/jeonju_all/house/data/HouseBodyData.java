package com.example.minkr.jeonju_all.house.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-06.
 */

@Root(name = "body")
public class HouseBodyData {

    @Element(name = "data", required = false)
    private HouseDatas data;

    public HouseDatas getData() {
        return data;
    }

    public void setData(HouseDatas data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HouseBodyData{" +
                "data=" + data +
                '}';
    }
}
