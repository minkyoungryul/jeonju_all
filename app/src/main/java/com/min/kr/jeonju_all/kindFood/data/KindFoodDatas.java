package com.min.kr.jeonju_all.kindFood.data;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by minkr on 2017-10-22.
 */

@Root(name = "data")
public class KindFoodDatas {

    @ElementList(inline = true, required = false)
    private List<KindFoodListData> list;

    public List<KindFoodListData> getList() {
        return list;
    }

    public void setList(List<KindFoodListData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "KindFoodDatas{" +
                "list=" + list +
                '}';
    }
}
