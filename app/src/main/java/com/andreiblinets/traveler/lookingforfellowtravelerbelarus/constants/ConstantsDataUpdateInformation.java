package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants;

public class ConstantsDataUpdateInformation extends ConstantsTable {

    public static final String NAME_TABLE_DATE_UPDATE_INFORMATION = "dateupdate";
    public static final String KEY_UPDATE_COUNTRY = "countryupdate";
    public static final String KEY_UPDATE_REGION = "regionupdate";
    public static final String KEY_UPDATE_CITY = "cityupdate";

    public static final String CREATE_DATE_LAST_UPDATE = "create table " +
            NAME_TABLE_DATE_UPDATE_INFORMATION +
            " ( " + KEY_ID + " integer primary key autoincrement," +
            KEY_UPDATE_COUNTRY +  " text" +
            KEY_UPDATE_REGION +  " text" +
            KEY_UPDATE_CITY +  " text" + ");";


    public static final String GET_OBJECT_BY_ID = SELECT_ALL + NAME_TABLE_DATE_UPDATE_INFORMATION +
            " WHERE " + KEY_ID + " = ?";
    public static final String GET_OBJECT = SELECT_ALL + NAME_TABLE_DATE_UPDATE_INFORMATION;
    public static final String DELETE_TABLE = DELETE + NAME_TABLE_DATE_UPDATE_INFORMATION;
}
