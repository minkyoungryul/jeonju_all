package com.example.minkr.jeonju_all.parking.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "list")
public class ParkingListData implements Serializable{

    @Element(name = "dataTitle")
    private String title;

    @Element(name = "parkingFee", required = false)
    private String parkingFee;

    @Element(name = "parkingAddr", required = false)
    private String address;

    @Element(name = "parkingGubun", required = false)
    private String type;

    @Element(name = "parkingTel", required = false)
    private String tel;

    @Element(name = "parkingMemo", required = false)
    private String memo;

    @Element(name = "posx")
    private String posX;

    @Element(name = "posy")
    private String posY;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParkingFee() {
        return parkingFee;
    }

    public void setParkingFee(String parkingFee) {
        this.parkingFee = parkingFee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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
        return "ParkingListData{" +
                "title='" + title + '\'' +
                ", parkingFee='" + parkingFee + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                ", tel='" + tel + '\'' +
                ", memo='" + memo + '\'' +
                ", posX='" + posX + '\'' +
                ", posY='" + posY + '\'' +
                '}';
    }
}
