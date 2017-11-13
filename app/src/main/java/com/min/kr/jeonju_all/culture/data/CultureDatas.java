package com.min.kr.jeonju_all.culture.data;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by minkr on 2017-11-07.
 */

@Root(name = "data")
public class CultureDatas {

    @ElementList(inline = true, required = false)
    private List<CultureListData> list;

    public List<CultureListData> getList() {
        return list;
    }

    public void setList(List<CultureListData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "CultureDatas{" +
                "list=" + list +
                '}';
    }
}
