package com.min.kr.jeonju_all.facility.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by minkr on 2017-11-08.
 */

@Root(name = "list")
public class HospitalListData implements Serializable{

    @Element(name = "mediAddr")
    private String address;

    @Element(name = "mediCdb")
    private int mediCdb;

    @Element(name = "mediCdbStr")
    private String mediCdbStr;

    @Element(name = "mediCdm")
    private int mediCdm;

    @Element(name = "mediCdmStr")
    private String mediCdmStr;

    @Element(name = "mediConDate", required = false)
    private String  mediConDate;

    @Element(name = "mediEmergencyEtime", required = false)
    private String mediEmergencyEtime;

    @Element(name = "mediEmergencyStime", required = false)
    private String mediEmergencyStime;

    @Element(name = "mediEtime", required = false)
    private String mediEtime;

    @Element(name = "mediIsEmergency", required = false)
    private int mediIsEmergency;

    @Element(name = "mediName")
    private String mediName;

    @Element(name = "mediStime", required = false)
    private String mediStime;

    @Element(name = "mediTel", required = false)
    private String mediTel;

    @Element(name = "posx")
    private String posX;

    @Element(name = "posy")
    private String posY;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMediCdb() {
        return mediCdb;
    }

    public void setMediCdb(int mediCdb) {
        this.mediCdb = mediCdb;
    }

    public String getMediCdbStr() {
        return mediCdbStr;
    }

    public void setMediCdbStr(String mediCdbStr) {
        this.mediCdbStr = mediCdbStr;
    }

    public int getMediCdm() {
        return mediCdm;
    }

    public void setMediCdm(int mediCdm) {
        this.mediCdm = mediCdm;
    }

    public String getMediCdmStr() {
        return mediCdmStr;
    }

    public void setMediCdmStr(String mediCdmStr) {
        this.mediCdmStr = mediCdmStr;
    }

    public String getMediConDate() {
        return mediConDate;
    }

    public void setMediConDate(String mediConDate) {
        this.mediConDate = mediConDate;
    }

    public String getMediEmergencyEtime() {
        return mediEmergencyEtime;
    }

    public void setMediEmergencyEtime(String mediEmergencyEtime) {
        this.mediEmergencyEtime = mediEmergencyEtime;
    }

    public String getMediEmergencyStime() {
        return mediEmergencyStime;
    }

    public void setMediEmergencyStime(String mediEmergencyStime) {
        this.mediEmergencyStime = mediEmergencyStime;
    }

    public String getMediEtime() {
        return mediEtime;
    }

    public void setMediEtime(String mediEtime) {
        this.mediEtime = mediEtime;
    }

    public int getMediIsEmergency() {
        return mediIsEmergency;
    }

    public void setMediIsEmergency(int mediIsEmergency) {
        this.mediIsEmergency = mediIsEmergency;
    }

    public String getMediName() {
        return mediName;
    }

    public void setMediName(String mediName) {
        this.mediName = mediName;
    }

    public String getMediStime() {
        return mediStime;
    }

    public void setMediStime(String mediStime) {
        this.mediStime = mediStime;
    }

    public String getMediTel() {
        return mediTel;
    }

    public void setMediTel(String mediTel) {
        this.mediTel = mediTel;
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
        return "HospitalListData{" +
                "address='" + address + '\'' +
                ", mediCdb=" + mediCdb +
                ", mediCdbStr='" + mediCdbStr + '\'' +
                ", mediCdm=" + mediCdm +
                ", mediCdmStr='" + mediCdmStr + '\'' +
                ", mediConDate='" + mediConDate + '\'' +
                ", mediEmergencyEtime='" + mediEmergencyEtime + '\'' +
                ", mediEmergencyStime='" + mediEmergencyStime + '\'' +
                ", mediEtime='" + mediEtime + '\'' +
                ", mediIsEmergency=" + mediIsEmergency +
                ", mediName='" + mediName + '\'' +
                ", mediStime='" + mediStime + '\'' +
                ", mediTel='" + mediTel + '\'' +
                ", posX='" + posX + '\'' +
                ", posY='" + posY + '\'' +
                '}';
    }
}
