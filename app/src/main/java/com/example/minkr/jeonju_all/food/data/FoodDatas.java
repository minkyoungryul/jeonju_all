package com.example.minkr.jeonju_all.food.data;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by minkr on 2017-11-03.
 */

@Root(name = "data")
public class FoodDatas {

    @ElementList(inline = true)
    private List<FoodListData> list;

    public List<FoodListData> getList() {
        return list;
    }

    public void setList(List<FoodListData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "FoodDatas{" +
                "list=" + list +
                '}';
    }
}
