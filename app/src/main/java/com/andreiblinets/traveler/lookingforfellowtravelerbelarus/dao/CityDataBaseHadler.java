package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.City;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;

import java.util.List;

public class CityDataBaseHadler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<City> {

    private final String NAME_TABLE_CITY = "citytable";

    private final String KEY_ID = "_id";
    private final String KEY_NAME = "name";
    private final String KEY_ID_REGION = "idregion";

    private final String CREATE_CITY_TABLE = "create table " +
            NAME_TABLE_CITY +
            " ( " + KEY_ID + " integer primary key autoincrement, " +
            KEY_NAME + " text, " +
            KEY_ID_REGION + " integer " + " );";

    private final String SELECT_ALL = "SELECT * FROM ";
    private final String GET_CITY_BY_ID = SELECT_ALL + NAME_TABLE_CITY + " WHERE " + KEY_ID + " = ?";
    private final String GET_CITY = SELECT_ALL + NAME_TABLE_CITY;
    private final String DELETE_TABLE = "DROP TABLE IF EXISTS " + NAME_TABLE_CITY;

    private static int dataBaseVerson;

    public CityDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CITY_TABLE);
        dataBaseVerson = ConstantsDataBase.DataBaseVersion;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void create(City city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, city.getName());
        values.put(KEY_ID_REGION, city.getIdRegion());
        db.insert(NAME_TABLE_CITY, null, values);
        db.close();
    }

    @Override
    public City getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_CITY_BY_ID, new String[]{String.valueOf(id)});
        City city = new City();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int nameIndex = cursor.getColumnIndex(KEY_NAME);
            int idRegionIndex = cursor.getColumnIndex(KEY_ID_REGION);
            city.setId(cursor.getInt(idIndex));
            city.setName(cursor.getString(nameIndex));
            city.setIdRegion(cursor.getInt(idRegionIndex));
        }
        cursor.close();
        db.close();
        return city;
    }

    @Override
    public List<City> getAll() {
        return null;
    }

    @Override
    public int update(City city) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
