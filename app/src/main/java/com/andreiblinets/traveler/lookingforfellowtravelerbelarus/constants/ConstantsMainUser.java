package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants;

public class ConstantsMainUser extends ConstantsTable {

    public static final String NAME_TABLE_MAIN_USER = "mainuser";
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_FOTO = "foto";
    public static final String KEY_TOKEN = "token";

    public static final String CREATE_MAIN_USER_APP = "create table " + NAME_TABLE_MAIN_USER +
            " ( " + KEY_ID +" integer primary key autoincrement," +
            KEY_NAME + " text," +
            KEY_SURNAME + " text," +
            KEY_FOTO + " text," +
            KEY_TOKEN + " text" + ");";

    public static final String GET_USER_BY_ID = SELECT_ALL + NAME_TABLE_MAIN_USER + " WHERE " + KEY_ID + " = ?";
    public static final String GET_USER = SELECT_ALL + NAME_TABLE_MAIN_USER;
    public static final String DELETE_TABLE = DELETE + NAME_TABLE_MAIN_USER;
}
