package com.example.minkr.jeonju_all.facility.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "list")
public class ParkListData implements Serializable{

    @Element(name = "dataSid")
    private int id;

    @Element(name = "dataTitle")
    private String title;

    @Element(name = "parkGubun")
    private String type;

    @Element(name = "parkLoadAddr")
    private String address;

    @Element(name = "parkArea", required = false)
    private double area;

    @Element(name = "parkTel", required = false)
    private String tel;

    @Element(name = "posx")
    private String posX;

    @Element(name = "posy")
    private String posY;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPosX() {
        return posX;
    }

    public void setPosX(String posX) {
        this.posX = posX;
    }

    public String getPosY() {
        return posY;
    }

    public void setPosY(String posY) {
        this.posY = posY;
    }

    @Override
    public String toString() {
        return "ParkListData{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", tel='" + tel + '\'' +
                ", posX='" + posX + '\'' +
                ", posY='" + posY + '\'' +
                '}';
    }
}
