package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants;

public class ConstantsRegion extends ConstantsTable {

    public static final String NAME_TABLE_REGION = "region";
    public static final String KEY_NAME = "name";
    public static final String KEY_ID_COUNTRY = "idcountry";

    public static final String CREATE_REGION_TABLE = "create table" +
            NAME_TABLE_REGION +
            " ( " + KEY_ID + " integer primary key autoincrement, " +
            KEY_NAME + " text," +
            KEY_ID_COUNTRY + " integer" + ");";

    public static final String GET_REGION_BY_ID = SELECT_ALL + NAME_TABLE_REGION + " WHERE " + KEY_ID + " = ?";
    public static final String GET_REGION = SELECT_ALL + NAME_TABLE_REGION;
    public static final String DELETE_TABLE = DELETE + NAME_TABLE_REGION;

}
