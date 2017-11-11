package com.example.minkr.jeonju_all.house.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by minkr on 2017-11-06.
 */

@Root(name = "list")
public class HouseListData implements Serializable{

    @Element(name = "addr")
    private String address;
    @Element(name = "dataContent")
    private String content;
    @Element(name = "dataTitle")
    private String storeName;
    @Element(name = "homepage", required = false)
    private String homepage;
    @Element(name = "introContent")
    private String introContent;
    @Element(name = "posx")
    private String posX;
    @Element(name = "posy")
    private String posY;

    private String img_url;



    private boolean isLike = false;

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getIntroContent() {
        return introContent;
    }

    public void setIntroContent(String introContent) {
        this.introContent = introContent;
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
        return "HouseListData{" +
                "address='" + address + '\'' +
                ", content='" + content + '\'' +
                ", storeName='" + storeName + '\'' +
                ", homepage='" + homepage + '\'' +
                ", introContent='" + introContent + '\'' +
                ", posX='" + posX + '\'' +
                ", posY='" + posY + '\'' +
                ", img_url='" + img_url + '\'' +
                ", isLike=" + isLike +
                '}';
    }
}
