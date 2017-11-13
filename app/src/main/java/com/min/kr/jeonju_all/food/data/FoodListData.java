package com.min.kr.jeonju_all.food.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by minkr on 2017-11-03.
 */

@Root(name = "list")
public class FoodListData implements Serializable {

    @Element(name = "closeTime")
    private String closeTime;
    @Element(name = "holiday", required = false)
    private String holiday;
    @Element(name = "isPaking")
    private int isParking;
    @Element(name = "isReserve")
    private int isReserve;
    @Element(name = "mainImgUrl")
    private String mainImg;
    @Element(name = "mainMenu", required = false)
    private String mainMenu;
    @Element(name = "newAddr", required = false)
    private String newAddr;
    @Element(name = "openTime")
    private String openTime;
    @Element(name = "parkingDetail", required = false)
    private String parkingDetail;
    @Element(name = "posx")
    private String posX;
    @Element(name = "posy")
    private String posY;
    @Element(name = "seatCnt")
    private int seatCnt;
    @Element(name = "storeNm")
    private String StoreName;
    @Element(name = "tableCnt")
    private int tableCnt;
    @Element(name = "tel")
    private String tel;

    private String type;
    private String storeId;
    private boolean isLike = false;

    @Override
    public String toString() {
        return "FoodListData{" +
                "closeTime='" + closeTime + '\'' +
                ", holiday='" + holiday + '\'' +
                ", isParking=" + isParking +
                ", isReserve=" + isReserve +
                ", mainImg='" + mainImg + '\'' +
                ", mainMenu='" + mainMenu + '\'' +
                ", newAddr='" + newAddr + '\'' +
                ", openTime='" + openTime + '\'' +
                ", parkingDetail='" + parkingDetail + '\'' +
                ", posX='" + posX + '\'' +
                ", posY='" + posY + '\'' +
                ", seatCnt=" + seatCnt +
                ", StoreName='" + StoreName + '\'' +
                ", tableCnt=" + tableCnt +
                ", tel='" + tel + '\'' +
                ", type='" + type + '\'' +
                ", storeId='" + storeId + '\'' +
                ", isLike=" + isLike +
                '}';
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIsParking() {
        return isParking;
    }

    public void setIsParking(int isParking) {
        this.isParking = isParking;
    }

    public int getIsReserve() {
        return isReserve;
    }

    public void setIsReserve(int isReserve) {
        this.isReserve = isReserve;
    }

    public String getParkingDetail() {
        return parkingDetail;
    }

    public void setParkingDetail(String parkingDetail) {
        this.parkingDetail = parkingDetail;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public int isParking() {
        return isParking;
    }

    public void setParking(int parking) {
        isParking = parking;
    }

    public int isReserve() {
        return isReserve;
    }

    public void setReserve(int reserve) {
        isReserve = reserve;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public String getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(String mainMenu) {
        this.mainMenu = mainMenu;
    }

    public String getNewAddr() {
        return newAddr;
    }

    public void setNewAddr(String newAddr) {
        this.newAddr = newAddr;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getParkingInfo() {
        return parkingDetail;
    }

    public void setParkingInfo(String parkingInfo) {
        this.parkingDetail = parkingInfo;
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

    public int getSeatCnt() {
        return seatCnt;
    }

    public void setSeatCnt(int seatCnt) {
        this.seatCnt = seatCnt;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public int getTableCnt() {
        return tableCnt;
    }

    public void setTableCnt(int tableCnt) {
        this.tableCnt = tableCnt;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
