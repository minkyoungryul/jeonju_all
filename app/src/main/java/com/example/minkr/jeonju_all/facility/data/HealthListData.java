package com.example.minkr.jeonju_all.facility.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "list")
public class HealthListData {

    @Element(name = "addr")
    private String address;

    @Element(name = "dataTitle")
    private String title;

    @Element(name = "fee", required = false)
    private String fee;

    @Element(name = "kyukyuk", required = false)
    private String kyukyuk;

    @Element(name = "kyumo", required = false)
    private String kyumo;

    @Element(name = "tel", required = false)
    private String tel;

    @Element(name = "time3_1",required = false)
    private String time;

    @Element(name = "posx", required = false)
    private String posX;

    @Element(name = "posy", required = false)
    private String posY;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getKyukyuk() {
        return kyukyuk;
    }

    public void setKyukyuk(String kyukyuk) {
        this.kyukyuk = kyukyuk;
    }

    public String getKyumo() {
        return kyumo;
    }

    public void setKyumo(String kyumo) {
        this.kyumo = kyumo;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
        return "HealthListData{" +
                "address='" + address + '\'' +
                ", title='" + title + '\'' +
                ", fee='" + fee + '\'' +
                ", kyukyuk='" + kyukyuk + '\'' +
                ", kyumo='" + kyumo + '\'' +
                ", tel='" + tel + '\'' +
                ", time='" + time + '\'' +
                ", posX='" + posX + '\'' +
                ", posY='" + posY + '\'' +
                '}';
    }
}
