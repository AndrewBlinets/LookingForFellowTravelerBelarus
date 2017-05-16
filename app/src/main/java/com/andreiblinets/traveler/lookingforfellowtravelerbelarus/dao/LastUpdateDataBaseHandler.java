package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.LastUpdate;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;

import java.util.List;

public class LastUpdateDataBaseHandler extends BaseClassDataBaseHadler<LastUpdate> {

    private final String KEY_UPDATE_COUNTRY = "countryupdate";
    private final String KEY_UPDATE_REGION = "regionupdate";
    private final String KEY_UPDATE_CITY = "cityupdate";

    public LastUpdateDataBaseHandler(Context context) {
        super(context, ConstantsDataBase.LAST_UPDATE);
        CREATE_TABLE = "add table " +
                NAME_TABLE +
                " ( " + KEY_ID + " integer primary key autoincrement, " +
                KEY_UPDATE_COUNTRY +  " text, " +
                KEY_UPDATE_REGION +  " text, " +
                KEY_UPDATE_CITY +  " text " + " );";
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
        return 0;
    }

}
