package com.example.minkr.jeonju_all.main.data;

import java.io.Serializable;

/**
 * Created by minkr on 2017-11-10.
 */

public class BookmarkList implements Serializable{
    private int id;
    private String type;
    private String title;
    private String address;
    private String tel;
    private String img_url;
    private String homepage_url;
    private String posX;
    private String posY;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getHomepage_url() {
        return homepage_url;
    }

    public void setHomepage_url(String homepage_url) {
        this.homepage_url = homepage_url;
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
        return "BookmarkList{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", img_url='" + img_url + '\'' +
                ", homepage_url='" + homepage_url + '\'' +
                ", posX='" + posX + '\'' +
                ", posY='" + posY + '\'' +
                '}';
    }
}
