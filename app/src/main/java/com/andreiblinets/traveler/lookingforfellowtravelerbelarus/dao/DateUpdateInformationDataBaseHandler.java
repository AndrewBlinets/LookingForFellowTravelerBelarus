package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.DateUpdateInformation;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;

import java.util.List;

public class DateUpdateInformationDataBaseHandler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<DateUpdateInformation> {

    private final String NAME_TABLE_DATE_UPDATE_INFORMATION = "dateupdatetable";

    private final String KEY_ID = "_id";
    private final String KEY_UPDATE_COUNTRY = "countryupdate";
    private final String KEY_UPDATE_REGION = "regionupdate";
    private final String KEY_UPDATE_CITY = "cityupdate";

    private final String CREATE_DATE_LAST_UPDATE = "create table " +
            NAME_TABLE_DATE_UPDATE_INFORMATION +
            " ( " + KEY_ID + " integer primary key autoincrement, " +
            KEY_UPDATE_COUNTRY +  " text, " +
            KEY_UPDATE_REGION +  " text, " +
            KEY_UPDATE_CITY +  " text " + " );";

    private final String SELECT_ALL = "SELECT * FROM ";
    private final String GET_OBJECT_BY_ID = SELECT_ALL + NAME_TABLE_DATE_UPDATE_INFORMATION +
            " WHERE " + KEY_ID + " = ?";
    private final String GET_OBJECT = SELECT_ALL + NAME_TABLE_DATE_UPDATE_INFORMATION;
    private final String DELETE_TABLE = "DROP TABLE IF EXISTS " + NAME_TABLE_DATE_UPDATE_INFORMATION;

    private int dataBaseVerson;

    public DateUpdateInformationDataBaseHandler(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DataBaseVersion);
        dataBaseVerson = ConstantsDataBase.DataBaseVersion;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATE_LAST_UPDATE);
        dataBaseVerson = ConstantsDataBase.DataBaseVersion;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }


    @Override
    public void create(DateUpdateInformation dateUpdateInformation) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
           // db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put(KEY_UPDATE_COUNTRY, dateUpdateInformation.getDataTimeUpdateCountry());
            values.put(KEY_UPDATE_REGION, dateUpdateInformation.getDataTimeUpdateRegion());
            values.put(KEY_UPDATE_CITY, dateUpdateInformation.getDataTimeUpdateCity());
            db.insert(NAME_TABLE_DATE_UPDATE_INFORMATION, null, values);
            //db.setTransactionSuccessful();
           // db.endTransaction();
            db.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public DateUpdateInformation getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_OBJECT_BY_ID, new String[]{String.valueOf(id)});
        DateUpdateInformation updateInformation = new DateUpdateInformation();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int dataUpdateCityIndex = cursor.getColumnIndex(KEY_UPDATE_CITY);
            int dataUpdateRegionIndex = cursor.getColumnIndex(KEY_UPDATE_REGION);
            int dataUpdateCounteyIndex = cursor.getColumnIndex(KEY_UPDATE_COUNTRY);
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
