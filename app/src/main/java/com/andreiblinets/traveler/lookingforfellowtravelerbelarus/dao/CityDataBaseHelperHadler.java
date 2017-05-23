package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.City;

import java.util.ArrayList;
import java.util.List;

public class CityDataBaseHelperHadler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<City> {

    String NAME_TABLE  = "citytable";
    protected final String KEY_ID = "_id";
    private final String KEY_NAME = "name";
    private final String KEY_ID_REGION = "idcountry";
    private final String SELECT_ALL = "SELECT * FROM ";
    String DELETE_TABLE = "DROP TABLE IF EXISTS " + NAME_TABLE;
    String  GET_BY_ID = SELECT_ALL + NAME_TABLE + " WHERE " + KEY_ID + " = ?";
    String GET_ALL = SELECT_ALL + NAME_TABLE;

    public CityDataBaseHelperHadler(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +
                NAME_TABLE +
                " ( " + KEY_ID + " integer primary key autoincrement, " +
                KEY_NAME + " text, " +
                KEY_ID_REGION + " text" + " );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void add(City city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, city.getName());
        values.put(KEY_ID_REGION, city.getIdRegion());
        db.insert(NAME_TABLE, null, values);
        db.close();
    }

    @Override
    public City getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
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
        List<City> cities = new ArrayList<City>();
        String selectQuery = GET_ALL;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int nameIndex = cursor.getColumnIndex(KEY_NAME);
                int idRegionIndex = cursor.getColumnIndex(KEY_ID_REGION);
                city.setId(cursor.getInt(idIndex));
                city.setName(cursor.getString(nameIndex));
                city.setIdRegion(cursor.getInt(idRegionIndex));
                cities.add(city);
            } while (cursor.moveToNext());
        }

        return cities;
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
