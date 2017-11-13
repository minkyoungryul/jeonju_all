package com.min.kr.jeonju_all.food.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-03.
 */

@Root(name = "body")
public class FoodBodyData {

    @Element(name = "data", required = false)
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
