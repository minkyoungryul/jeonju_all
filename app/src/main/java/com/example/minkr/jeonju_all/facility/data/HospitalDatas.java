package com.example.minkr.jeonju_all.facility.data;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "data")
public class HospitalDatas {

    @ElementList(inline = true)
    private List<HospitalListData> list;


    public List<HospitalListData> getList() {
        return list;
    }

    public void setList(List<HospitalListData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "HospitalDatas{" +
                "list=" + list +
                '}';
    }
}
