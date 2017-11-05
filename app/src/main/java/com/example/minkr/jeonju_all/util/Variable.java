package com.example.minkr.jeonju_all.util;

/**
 * Created by mkr on 2017-08-21.
 */

public class Variable {
//    public final static String _SERVER_HOST = "http://catchadev.com/";
//    public final static String _SERVER_URL = _SERVER_HOST + "json/query.php";

    public final static String _SERVER_HOST = "http://openapi.jeonju.go.kr/rest/";
//    http://openapi.jeonju.go.kr/rest/parking/getParkingList
//    http://openapi.jeonju.go.kr/rest/chakhanpriceservice/getChakHan?ServiceKey=인증키요청변수&pageNo=1&numOfRows=10
    public final static String _PARKING_SERVICE_KEY = "iFwliBu1jhkPtsNQA1gvnrmONFzxyPQzcTSKFnY8rlgryyZ8scW8wzL6m89bOipf7mBo/dptZLTDyvNH1dKCbQ==";
    public final static String _KINDFOOD_SERVICE_KEY = "87NcRP27j9AqfHfoumvpLSAtoostJ8siaBAWmfdnjQEah1RTAis3mcJZh8CT/xCFzYT8zSwydcuevHEBQmVvrQ==";
    public final static String _PARKING_LIST_SERVER_REQUEST_URL = "parking/getParkingList?pageSize=76";
    public final static String _KINDFOOD_LIST_SERVER_REQUEST_URL = "chakhanpriceservice/getChakHan";
    public final static String _FOOD_RICE_LIST_SERVER_REQUEST_URL = "jeonjufood/getWhiteRiceList";
    public final static String _FOOD_BIBIMBAP_LIST_SERVER_REQUEST_URL = "jeonjufood/getMimbapList";

    public static final String _FOOD_KONGBAP_LIST_SERVER_REQUEST_URL = "jeonjufood/getGongBapList";
    public static final String _FOOD_WINE_LIST_SERVER_REQUEST_URL = "jeonjufood/getKoreaWineList";
    public static final String _FOOD_HANOK_LIST_SERVER_REQUEST_URL = "jeonjufood/getHanOkFoodList";
    public static final String _HOUSE_LIST_SERVER_REQUEST_URL = "hanokhouse/getHanokHouseList";
}