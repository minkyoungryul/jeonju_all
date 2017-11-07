package com.example.minkr.jeonju_all.culture.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-07.
 */

@Root(name = "rfcOpenApi")
public class CultureTotalData {

    @Element(name = "body")
    private CultureBodyData body;

    public CultureBodyData getBody() {
        return body;
    }

    public void setBody(CultureBodyData body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "CultureTotalData{" +
                "body=" + body +
                '}';
    }
}
