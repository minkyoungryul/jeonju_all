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
    public final static String _FOOD_RICE_LIST_SERVER_REQUEST_URL = "jeonjufood/getWhiteRiceList?pageSize=100";
    public final static String _FOOD_BIBIMBAP_LIST_SERVER_REQUEST_URL = "jeonjufood/getMimbapList?pageSize=100";

    public static final String _FOOD_KONGBAP_LIST_SERVER_REQUEST_URL = "jeonjufood/getGongBapList?pageSize=100";
    public static final String _FOOD_WINE_LIST_SERVER_REQUEST_URL = "jeonjufood/getKoreaWineList?pageSize=100";
    public static final String _FOOD_HANOK_LIST_SERVER_REQUEST_URL = "jeonjufood/getHanOkFoodList?pageSize=100";
    public static final String _HOUSE_LIST_SERVER_REQUEST_URL = "hanokhouse/getHanokHouseList?pageSize=34";
    public static final String _CULTURE_LIST_SERVER_REQUEST_URL = "culture/getCultureList?pageSize=72";
    public static final String _POLIICE_LIST_SERVER_REQUEST_URL = "patroldiv/getPatrolDivList?pageSize=46";

    //병원
    public static final String _HOSPITAL_ALL_LIST_SERVER_REQUEST_URL = "medicalnew/getMedicalList?pageSize=980&mediCdb=100"; //병원
    //종합병원, 일반병원, 요양병원, 아동병원, 치과병원, 한방병원

    //의원
    public static final String _HOSPITAL_INTERNAL_LIST_SERVER_REQUEST_URL = "medicalnew/getMedicalList?pageSize=980&mediCdb=200"; //내과
    //내과, 소아청소년과, 이비인후과, 가정의학과, 일반의원, 산부인과, 피부과, 정형외과, 외과, 안과, 재활의학과, 정신건강의학과, 치과, 신경외과

    //한의원
    public static final String _HOSPITAL_KOREA_LIST_SERVER_REQUEST_URL = "medicalnew/getMedicalList?pageSize=980&mediCdb=300"; //한의원

    //산후조리원
    public static final String _HOSPITAL_POSTPARTUM_LIST_SERVER_REQUEST_URL = "medicalnew/getMedicalList?pageSize=980&mediCdb=500"; //산후조리원

    //치과의원
    public static final String _HOSPITAL_DENTIST_LIST_SERVER_REQUEST_URL = "medicalnew/getMedicalList?pageSize=980&mediCdb=600"; //치과의원

    //약국
    public static final String _MEDICINE_LIST_SERVER_REQUEST_URL = "medicalnew/getMedicalList?pageSize=350&mediCdb=400"; //약국

}