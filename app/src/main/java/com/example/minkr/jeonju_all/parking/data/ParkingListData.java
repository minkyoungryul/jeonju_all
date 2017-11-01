package com.example.minkr.jeonju_all.parking.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-10-22.
 */

@Root(name="list")
public class ParkingListData {
    @Element(name = "dataSid")
    private int id;

    @Element(name = "dataTitle")
    private String title;

    @Element(name = "parkingAddr")
    private String address;

    @Override
    public String toString() {
        return "ParkingListData{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
