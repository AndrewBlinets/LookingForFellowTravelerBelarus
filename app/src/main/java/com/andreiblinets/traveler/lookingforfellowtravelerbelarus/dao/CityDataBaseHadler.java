package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.City;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;

public class CityDataBaseHadler extends BaseClassDataBaseHadler<City> {

    private final String KEY_NAME = "name";
    private final String KEY_ID_REGION = "idregion";

    public CityDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.CITY);
        this.CREATE_TABLE = "add table " +
                NAME_TABLE +
                " ( " + KEY_ID + " integer primary key autoincrement, " +
                KEY_NAME + " text, " +
                KEY_ID_REGION + " integer " + " );";
    }

    public void add(City city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, city.getName());
        values.put(KEY_ID_REGION, city.getIdRegion());
        db.insert(NAME_TABLE, null, values);
        db.close();
    }

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

}
