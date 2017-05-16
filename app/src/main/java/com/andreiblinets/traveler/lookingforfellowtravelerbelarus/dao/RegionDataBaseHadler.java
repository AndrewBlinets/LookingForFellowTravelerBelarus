package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Region;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;


import java.util.List;

public class RegionDataBaseHadler extends BaseClassDataBaseHadler<Region> {

    private final String KEY_NAME = "name";
    private final String KEY_ID_COUNTRY = "idcountry";

    public RegionDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.REGION);
        CREATE_TABLE = "add table " +
                NAME_TABLE +
                " ( " + KEY_ID + " integer primary key autoincrement, " +
                KEY_NAME + " text, " +
                KEY_ID_COUNTRY + " integer " + " );";
    }

    @Override
    public void add(Region region) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, region.getName());
        values.put(KEY_ID_COUNTRY, region.getIdCountry());
        db.insert(NAME_TABLE, null, values);
        db.close();
    }

    @Override
    public Region getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
        Region region = new Region();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int nameIndex = cursor.getColumnIndex(KEY_NAME);
            int idCountryIndex = cursor.getColumnIndex(KEY_ID_COUNTRY);
            region.setId(cursor.getInt(idIndex));
            region.setName(cursor.getString(nameIndex));
            region.setIdCountry(cursor.getInt(idCountryIndex));
        }
        cursor.close();
        db.close();
        return region;
    }

    @Override
    public List<Region> getAll() {
        return null;
    }

    @Override
    public int update(Region region) {
        return 0;
    }

}
