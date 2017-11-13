package com.min.kr.jeonju_all.facility.data;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "data")
public class ParkDatas {

    @ElementList(inline = true, required = false)
    private List<ParkListData> list;


    public List<ParkListData> getList() {
        return list;
    }

    public void setList(List<ParkListData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ParkDatas{" +
                "list=" + list +
                '}';
    }
}
