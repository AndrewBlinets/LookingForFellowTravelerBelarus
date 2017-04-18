package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants;

public class ConstantsDataBase {

    public static final int DataBaseVersion = 1;
    public static final String DATABASE_NAME = "lookingForFellowTravelerBelarus";

    // Main User

    public static final String MAIN_USER_APP = "create table mainuser (" + "_id integer primary key autoincrement,"
            + "name text," + "surname text," + "foto text," + "token text" + ");";

    // Country and city
    public static final String DATE_LAST_UPDATE = "create table dateupdate (" + "_id integer primary key autoincrement,"
            + "dateupdate text" + ");";
    public static final String COUNTRY_TABLE = "create table country (" + "_id integer primary key autoincrement,"
             + " name text," + " currencycode text" + ");";
    public static final String REGION_TABLE = "create table region (" + "_id integer primary key autoincrement,"
            + "idcountry integer," + "Nameregion text" + ");";
    public static final String CITY_TABLE = "create table city (" + "_id integer primary key autoincrement,"
            + "idcountry integer," +"idregion integer," + "Namecity text" + ");";


}