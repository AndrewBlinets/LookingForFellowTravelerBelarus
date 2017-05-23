package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants;

public class NetWork {
    public static final String HOST = "http://192.168.43.246:8080";
    //public static final String HOST = "http://172.22.0.1:8080";
    public static final String AUTIFICATION = "/autification/login&hashpassword";
    public static final String GET_DATE_LAST_UPDATE = "/lastdataupdate";

    public static final String GET_LIST_COUNTRY = "/countries";
    public static final String GET_LIST_REGION = "/regions";
    public static final String GET_LIST_CITY = "/cities";

    public static final String CHECKING_EMAIL = "/checking/{email}";
    public static final String CHECKING_PHONE = "/checking/{phone}";

    public static final String CHECKING_TOKEN = "/user/token";
    public static final String REGISTRATION ="/registration";
    public static final String SEARCH_ROAD = "/search/idCityOfDeparture&idCityOfArrived&data";
    public static final String CREATE_ROAD = "/read";
    public static final String MY_ROAD = "/driveTrip/token";
}
