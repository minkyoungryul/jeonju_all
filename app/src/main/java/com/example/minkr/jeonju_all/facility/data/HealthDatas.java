package com.example.minkr.jeonju_all.facility.data;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "data")
public class HealthDatas {

    @ElementList(inline = true)
    private List<HealthListData> list;


    public List<HealthListData> getList() {
        return list;
    }

    public void setList(List<HealthListData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "HealthDatas{" +
                "list=" + list +
                '}';
    }
}
