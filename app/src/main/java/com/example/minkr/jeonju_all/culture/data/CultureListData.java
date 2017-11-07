package com.example.minkr.jeonju_all.culture.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by minkr on 2017-11-07.
 */

@Root(name = "list")
public class CultureListData {

    @Element(name = "addr")
    private String addr;
    @Element(name = "addrDtl", required = false)
    private String addrDtl;
    @Element(name = "dataContent", required = false)
    private String dataContent;
    @Element(name = "dataTitle")
    private String title;
    @Element(name = "posx")
    private String posX;
    @Element(name = "posy")
    private String posY;
    @Element(name = "tel", required = false)
    private String tel;
    @Element(name = "typeCode")
    private String typeCode;
    @Element(name = "userHomepage", required = false)
    private String userHomepage;

    private String img_url;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAddrDtl() {
        return addrDtl;
    }

    public void setAddrDtl(String addrDtl) {
        this.addrDtl = addrDtl;
    }

    public String getDataContent() {
        return dataContent;
    }

    public void setDataContent(String dataContent) {
        this.dataContent = dataContent;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getUserHomepage() {
        return userHomepage;
    }

    public void setUserHomepage(String userHomepage) {
        this.userHomepage = userHomepage;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "CultureListData{" +
                "addr='" + addr + '\'' +
                ", addrDtl='" + addrDtl + '\'' +
                ", dataContent='" + dataContent + '\'' +
                ", title='" + title + '\'' +
                ", posX='" + posX + '\'' +
                ", posY='" + posY + '\'' +
                ", tel='" + tel + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", userHomepage='" + userHomepage + '\'' +
                ", img_url='" + img_url + '\'' +
                '}';
    }
}
