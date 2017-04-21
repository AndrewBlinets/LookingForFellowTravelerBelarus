package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants;

public class ConstansCountry extends ConstantsTable {

    public static final String NAME_TABLE_COUNTRY = "country";
    public static final String KEY_NAME = "name";
    public static final String KEY_KOD_CURRENCY = "kodcurrency";

    public static final String CREATE_COUNTRY_TABLE = "create table " +
            NAME_TABLE_COUNTRY +
            " ( " + KEY_ID + " integer primary key autoincrement," +
            KEY_NAME + " text," +
            KEY_KOD_CURRENCY + " text" + ");";

    public static final String GET_COUNTRY_BY_ID = SELECT_ALL + NAME_TABLE_COUNTRY + " WHERE " + KEY_ID + " = ?";
    public static final String GET_COUNTRY = SELECT_ALL + NAME_TABLE_COUNTRY;
    public static final String DELETE_TABLE = DELETE + NAME_TABLE_COUNTRY;

}
