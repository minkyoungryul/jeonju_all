package com.min.kr.jeonju_all.kindFood.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-10-25.
 */

@Root(name = "rcfOpenApi")
public class KindFoodTotalData {

    @Element(name = "body", required = false)
    private KindFoodBodyData body;

    public KindFoodBodyData getBody() {
        return body;
    }

    public void setBody(KindFoodBodyData body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "KindFoodTotalData{" +
                "body=" + body +
                '}';
    }
}
