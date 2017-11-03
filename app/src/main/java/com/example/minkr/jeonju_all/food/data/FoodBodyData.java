package com.example.minkr.jeonju_all.food.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-03.
 */

@Root(name = "body", strict = false)
public class FoodBodyData {

    @Element(name = "data")
    private FoodDatas data;

    public FoodDatas getData() {
        return data;
    }

    public void setData(FoodDatas data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FoodBodyData{" +
                "data=" + data +
                '}';
    }
}
