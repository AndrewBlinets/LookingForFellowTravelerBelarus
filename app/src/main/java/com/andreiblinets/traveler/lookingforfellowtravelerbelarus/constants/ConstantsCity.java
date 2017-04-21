package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants;

public class ConstantsCity extends ConstantsTable {

    public static final String NAME_TABLE_CITY = "city";
    public static final String KEY_NAME = "name";
    public static final String KEY_ID_REGION = "idregion";

    public static final String CREATE_CITY_TABLE = "create table" +
            NAME_TABLE_CITY +
            " ( " + KEY_ID + " integer primary key autoincrement, " +
            KEY_NAME + " text," +
            KEY_ID_REGION + " integer" + ");";

    public static final String GET_CITY_BY_ID = SELECT_ALL + NAME_TABLE_CITY + " WHERE " + KEY_ID + " = ?";
    public static final String GET_CITY = SELECT_ALL + NAME_TABLE_CITY;
    public static final String DELETE_TABLE = DELETE + NAME_TABLE_CITY;
}
