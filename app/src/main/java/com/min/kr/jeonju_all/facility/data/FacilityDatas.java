package com.min.kr.jeonju_all.facility.data;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "data")
public class FacilityDatas {

    @ElementList(inline = true, required = false)
    private List<FacilityListData> list;


    public List<FacilityListData> getList() {
        return list;
    }

    public void setList(List<FacilityListData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "FacilityDatas{" +
                "list=" + list +
                '}';
    }
}
