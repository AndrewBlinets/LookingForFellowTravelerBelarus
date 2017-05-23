package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.PopularInquiries;

import java.util.List;


public class PopularInquiriesDataBaseHadler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<PopularInquiries> {

    private final String SELECT_ALL = "SELECT * FROM ";
    private final String KEY_NAME = "name";
    private final String KEY_ID_REGION = "idregion";
    protected final String KEY_ID = "_id";
    String NAME_TABLE  = "popularinquiries";
    String DELETE_TABLE = "DROP TABLE IF EXISTS " + NAME_TABLE;
    String  GET_BY_ID = SELECT_ALL + NAME_TABLE + " WHERE " + KEY_ID + " = ?";
    String GET_ALL = SELECT_ALL + NAME_TABLE;

    public PopularInquiriesDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DataBaseVersion);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +
                NAME_TABLE +
                " ( " + KEY_ID + " integer primary key autoincrement, " +
                KEY_ID_CITY_OF_DEPARTURE + " integer, " +
                KEY_ID_CITY_OF_ARRIVED + " integer " +
                KEY_COUNT + " integer " + " );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }

    private final String KEY_COUNT = "count";
    private final String KEY_ID_CITY_OF_DEPARTURE = "idcityofdeparture";
    private final String KEY_ID_CITY_OF_ARRIVED = "idсityofarrived";

//    public PopularInquiriesDataBaseHadler(Context context) {
//        super(context, ConstantsDataBase.POPULAR_INQUIRIES);
//        CREATE_TABLE = "";
//    }

    @Override
    public void add(PopularInquiries popularInquiries) {

    }

    @Override
    public PopularInquiries getById(int id) {
        return null;
    }

    @Override
    public List<PopularInquiries> getAll() {
        return null;
    }

    @Override
    public int update(PopularInquiries popularInquiries) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
