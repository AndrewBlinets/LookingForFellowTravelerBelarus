package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.LastUpdate;

import java.util.List;

public class InformationUpdateDataBase extends SQLiteOpenHelper implements InterfaseDataBaseHandler<LastUpdate> {


    private final String KEY_UPDATE_COUNTRY = "countryupdate";
    private final String KEY_UPDATE_REGION = "regionupdate";
    private final String KEY_UPDATE_CITY = "cityupdate";
    protected final String KEY_ID = "_id";
    String NAME_TABLE  = "informationupdate";
    private final String SELECT_ALL = "SELECT * FROM ";
    String DELETE_TABLE = "DROP TABLE IF EXISTS " + NAME_TABLE;
    String  GET_BY_ID = SELECT_ALL + NAME_TABLE + " WHERE " + KEY_ID + " = ?";
    String GET_ALL = SELECT_ALL + NAME_TABLE;

    public InformationUpdateDataBase(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +
                NAME_TABLE +
                " ( " + KEY_ID + " integer primary key autoincrement, " +
                KEY_UPDATE_COUNTRY + " text, " +
                KEY_UPDATE_REGION + " text, " +
                KEY_UPDATE_CITY + " text" + " );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void add(LastUpdate lastUpdate) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_UPDATE_COUNTRY, lastUpdate.getLastUpdateCountry());
            values.put(KEY_UPDATE_REGION, lastUpdate.getLastUpdateRegion());
            values.put(KEY_UPDATE_CITY, lastUpdate.getLastUpdateCity());
            db.insert(NAME_TABLE, null, values);
            db.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public LastUpdate getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
        LastUpdate updateInformation = new LastUpdate();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int dataUpdateCityIndex = cursor.getColumnIndex(KEY_UPDATE_CITY);
            int dataUpdateRegionIndex = cursor.getColumnIndex(KEY_UPDATE_REGION);
            int dataUpdateCounteyIndex = cursor.getColumnIndex(KEY_UPDATE_COUNTRY);
            updateInformation.setId(cursor.getInt(idIndex));
            updateInformation.setLastUpdateCity(cursor.getString(dataUpdateCityIndex));
            updateInformation.setLastUpdateRegion(cursor.getString(dataUpdateRegionIndex));
            updateInformation.setLastUpdateCountry(cursor.getString(dataUpdateCounteyIndex));
        }
        cursor.close();
        db.close();
        return updateInformation;
    }

    @Override
    public List<LastUpdate> getAll() {
        return null;
    }

    @Override
    public int update(LastUpdate lastUpdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_UPDATE_CITY,lastUpdate.getLastUpdateCity());
        values.put(KEY_UPDATE_COUNTRY,lastUpdate.getLastUpdateCountry());
        values.put(KEY_UPDATE_REGION,lastUpdate.getLastUpdateRegion());
        return db.update(NAME_TABLE, values, KEY_ID + " = ?", new String[]{String.valueOf(lastUpdate.getId())} );
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
