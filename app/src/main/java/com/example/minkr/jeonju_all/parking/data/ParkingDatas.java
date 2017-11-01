package com.example.minkr.jeonju_all.parking.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by minkr on 2017-10-22.
 */

@Root(name="data")
public class ParkingDatas {
    @ElementList(inline = true)
    private List<ParkingListData> list;

    @Override
    public String toString() {
        return "ParkingDatas{" +
                "list=" + list +
                '}';
    }
}
