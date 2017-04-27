package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Region;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;


import java.util.List;

public class RegionDataBaseHadler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<Region> {

    private final String NAME_TABLE_REGION = "regiontable";

    private final String KEY_ID = "_id";
    private final String KEY_NAME = "name";
    private final String KEY_ID_COUNTRY = "idcountry";

    private final String CREATE_REGION_TABLE = "create table " +
            NAME_TABLE_REGION +
            " ( " + KEY_ID + " integer primary key autoincrement, " +
            KEY_NAME + " text, " +
            KEY_ID_COUNTRY + " integer " + " );";

    private final String SELECT_ALL = "SELECT * FROM ";
    private final String GET_REGION_BY_ID = SELECT_ALL + NAME_TABLE_REGION + " WHERE " + KEY_ID + " = ?";
    private final String GET_REGION = SELECT_ALL + NAME_TABLE_REGION;
    private final String DELETE_TABLE = "DROP TABLE IF EXISTS " + NAME_TABLE_REGION;

    private static int dataBaseVerson;

    public RegionDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_REGION_TABLE);
        dataBaseVerson = ConstantsDataBase.DataBaseVersion;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void create(Region region) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, region.getName());
        values.put(KEY_ID_COUNTRY, region.getIdCountry());
        db.insert(NAME_TABLE_REGION, null, values);
        db.close();
    }

    @Override
    public Region getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_REGION_BY_ID, new String[]{String.valueOf(id)});
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

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
