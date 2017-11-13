package com.min.kr.jeonju_all.facility.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "body")
public class HealthBodyData {

    @Element(name = "data", required = false)
    private HealthDatas data;

    public HealthDatas getData() {
        return data;
    }

    public void setData(HealthDatas data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HealthBodyData{" +
                "data=" + data +
                '}';
    }
}
