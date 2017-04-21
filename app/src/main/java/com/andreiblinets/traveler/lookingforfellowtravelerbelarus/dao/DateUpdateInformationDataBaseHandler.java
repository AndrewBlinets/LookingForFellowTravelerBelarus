package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.DTO.DateUpdateInformation;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataUpdateInformation;

import java.util.List;

public class DateUpdateInformationDataBaseHandler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<DateUpdateInformation> {

    private int dataBaseVerson;

    public DateUpdateInformationDataBaseHandler(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ConstantsDataUpdateInformation.CREATE_DATE_LAST_UPDATE);
        db.beginTransaction();
        dataBaseVerson = ConstantsDataBase.DataBaseVersion;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ConstantsDataUpdateInformation.DELETE_TABLE);
        onCreate(db);
    }


    @Override
    public void create(DateUpdateInformation dateUpdateInformation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ConstantsDataUpdateInformation.KEY_UPDATE_COUNTRY, dateUpdateInformation.getDataTimeUpdateCountry());
        values.put(ConstantsDataUpdateInformation.KEY_UPDATE_REGION, dateUpdateInformation.getDataTimeUpdateRegion());
        values.put(ConstantsDataUpdateInformation.KEY_UPDATE_CITY, dateUpdateInformation.getDataTimeUpdateCity());
        db.insert(ConstantsDataUpdateInformation.NAME_TABLE_DATE_UPDATE_INFORMATION, null, values);
        db.close();
    }

    @Override
    public DateUpdateInformation getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(ConstantsDataUpdateInformation.GET_OBJECT_BY_ID, new String[]{String.valueOf(id)});
        DateUpdateInformation updateInformation = new DateUpdateInformation();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(ConstantsDataUpdateInformation.KEY_ID);
            int dataUpdateCityIndex = cursor.getColumnIndex(ConstantsDataUpdateInformation.KEY_UPDATE_CITY);
            int dataUpdateRegionIndex = cursor.getColumnIndex(ConstantsDataUpdateInformation.KEY_UPDATE_REGION);
            int dataUpdateCounteyIndex = cursor.getColumnIndex(ConstantsDataUpdateInformation.KEY_UPDATE_COUNTRY);
            updateInformation.setId(cursor.getInt(idIndex));
            updateInformation.setDataTimeUpdateCity(cursor.getString(dataUpdateCityIndex));
            updateInformation.setDataTimeUpdateRegion(cursor.getString(dataUpdateRegionIndex));
            updateInformation.setDataTimeUpdateCountry(cursor.getString(dataUpdateCounteyIndex));
        }
        cursor.close();
        db.close();
        return updateInformation;
    }

    @Override
    public List<DateUpdateInformation> getAll() {
        return null;
    }

    @Override
    public int update(DateUpdateInformation dateUpdateInformation) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
