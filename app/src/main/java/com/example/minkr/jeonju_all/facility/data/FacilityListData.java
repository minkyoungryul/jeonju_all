package com.example.minkr.jeonju_all.facility.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "list")
public class FacilityListData implements Serializable{

    @Element(name = "activeCnt")
    private String activeCnt;

    @Element(name = "name")
    private String name;

    @Element(name = "patrolAddr")
    private String address;

    @Element(name = "patrolTel")
    private String tel;

    @Element(name = "patrolTitle")
    private String title;

    @Element(name = "posx")
    private String posX;

    @Element(name = "posy")
    private String posY;

    public String getActiveCnt() {
        return activeCnt;
    }

    public void setActiveCnt(String activeCnt) {
        this.activeCnt = activeCnt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return "FacilityListData{" +
                "activeCnt='" + activeCnt + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", title='" + title + '\'' +
                ", posX='" + posX + '\'' +
                ", posY='" + posY + '\'' +
                '}';
    }
}
