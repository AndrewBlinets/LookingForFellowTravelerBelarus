package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.DTO.CityDTO;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsCity;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;

import java.util.List;

public class CityDataBaseHadler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<CityDTO> {

    private static int dataBaseVerson;

    public CityDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ConstantsCity.CREATE_CITY_TABLE);
        db.beginTransaction();
        dataBaseVerson = ConstantsDataBase.DataBaseVersion;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ConstantsCity.DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void create(CityDTO city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ConstantsCity.KEY_NAME, city.getName());
        values.put(ConstantsCity.KEY_ID_REGION, city.getIdRegion());
        db.insert(ConstantsCity.NAME_TABLE_CITY, null, values);
        db.close();
    }

    @Override
    public CityDTO getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(ConstantsCity.GET_CITY_BY_ID, new String[]{String.valueOf(id)});
        CityDTO city = new CityDTO();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(ConstantsCity.KEY_ID);
            int nameIndex = cursor.getColumnIndex(ConstantsCity.KEY_NAME);
            int idRegionIndex = cursor.getColumnIndex(ConstantsCity.KEY_ID_REGION);
            city.setId(cursor.getInt(idIndex));
            city.setName(cursor.getString(nameIndex));
            city.setIdRegion(cursor.getInt(idRegionIndex));
        }
        cursor.close();
        db.close();
        return city;
    }

    @Override
    public List<CityDTO> getAll() {
        return null;
    }

    @Override
    public int update(CityDTO cityDTO) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
