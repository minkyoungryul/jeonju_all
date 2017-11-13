package com.min.kr.jeonju_all.house.data;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by minkr on 2017-11-06.
 */

@Root(name = "data")
public class HouseDatas {

    @ElementList(inline = true, required = false)
    private List<HouseListData> list;

    public List<HouseListData> getList() {
        return list;
    }

    public void setList(List<HouseListData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "HouseDatas{" +
                "list=" + list +
                '}';
    }
}
