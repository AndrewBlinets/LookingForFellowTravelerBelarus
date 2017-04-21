package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.DTO.RegionDTO;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsRegion;


import java.util.List;

public class RegionDataBaseHadler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<RegionDTO> {

    private static int dataBaseVerson;

    public RegionDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ConstantsRegion.CREATE_REGION_TABLE);
        db.beginTransaction();
        dataBaseVerson = ConstantsDataBase.DataBaseVersion;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ConstantsRegion.DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void create(RegionDTO region) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ConstantsRegion.KEY_NAME, region.getName());
        values.put(ConstantsRegion.KEY_ID_COUNTRY, region.getIdCountry());
        db.insert(ConstantsRegion.NAME_TABLE_REGION, null, values);
        db.close();
    }

    @Override
    public RegionDTO getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(ConstantsRegion.GET_REGION_BY_ID, new String[]{String.valueOf(id)});
        RegionDTO region = new RegionDTO();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(ConstantsRegion.KEY_ID);
            int nameIndex = cursor.getColumnIndex(ConstantsRegion.KEY_NAME);
            int idCountryIndex = cursor.getColumnIndex(ConstantsRegion.KEY_ID_COUNTRY);
            region.setId(cursor.getInt(idIndex));
            region.setName(cursor.getString(nameIndex));
            region.setIdCountry(cursor.getInt(idCountryIndex));
        }
        cursor.close();
        db.close();
        return region;
    }

    @Override
    public List<RegionDTO> getAll() {
        return null;
    }

    @Override
    public int update(RegionDTO regionDTO) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
