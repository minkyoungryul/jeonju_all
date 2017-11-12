package com.example.minkr.jeonju_all.kindFood.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-10-25.
 */

@Root(name = "body")
public class KindFoodBodyData {

    @Element(name = "data", required = false)
    private KindFoodDatas data;

    public KindFoodDatas getData() {
        return data;
    }

    public void setData(KindFoodDatas data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "KindFoodBodyData{" +
                "data=" + data +
                '}';
    }
}
