package com.example.minkr.jeonju_all.culture.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-07.
 */

@Root(name = "body")
public class CultureBodyData {

    @Element(name = "data")
    private CultureDatas data;

    public CultureDatas getData() {
        return data;
    }

    public void setData(CultureDatas data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CultureBodyData{" +
                "data=" + data +
                '}';
    }
}
