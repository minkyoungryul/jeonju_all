package com.example.minkr.jeonju_all.food.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-03.
 */

@Root(name = "rfcOpenApi")
public class FoodTotalData {

    @Element(name = "body", required = false)
    private FoodBodyData body;

    public FoodBodyData getBody() {
        return body;
    }

    public void setBody(FoodBodyData body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "FoodTotalData{" +
                "body=" + body +
                '}';
    }
}
