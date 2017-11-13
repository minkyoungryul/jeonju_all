package com.min.kr.jeonju_all.kindFood.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by minkr on 2017-10-25.
 */

@Root(name = "list")
public class KindFoodListData implements Serializable{
    @Element(name = "apiSid")
    private int id;
    @Element(name = "apiCeoName")
    private String ceoName;
    @Element(name = "apiLat")
    private String x;
    @Element(name = "apiLng")
    private String y;
    @Element(name = "apiName")
    private String name;
    @Element(name = "apiNewAddress")
    private String address;
    @Element(name = "apiPrice")
    private String price;
    @Element(name = "apiPummokName")
    private String foodName;
    @Element(name = "apiRegDate")
    private String regDate;
    @Element(name = "apiTel")
    private String tel;

    private String storeId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCeoName() {
        return ceoName;
    }

    public void setCeoName(String ceoName) {
        this.ceoName = ceoName;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "KindFoodListData{" +
                "id=" + id +
                ", ceoName='" + ceoName + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", price='" + price + '\'' +
                ", foodName='" + foodName + '\'' +
                ", regDate='" + regDate + '\'' +
                ", tel='" + tel + '\'' +
                ", storeId='" + storeId + '\'' +
                ", img_url='" + img_url + '\'' +
                ", isLike=" + isLike +
                '}';
    }
}
