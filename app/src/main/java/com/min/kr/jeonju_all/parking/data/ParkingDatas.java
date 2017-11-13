package com.min.kr.jeonju_all.parking.data;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "data")
public class ParkingDatas {

    @ElementList(inline = true, required = false)
    private List<ParkingListData> list;


    public List<ParkingListData> getList() {
        return list;
    }

    public void setList(List<ParkingListData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ParkingDatas{" +
                "list=" + list +
                '}';
    }
}
