package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Region;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 22.05.2017.
 */

public class RegionDataBaseHelperHandler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<Region> {

    String NAME_TABLE  = "regiontable";
    protected final String KEY_ID = "_id";
    private final String KEY_NAME = "name";
    private final String KEY_ID_COUNTRY = "idcountry";
    private final String SELECT_ALL = "SELECT * FROM ";
    String DELETE_TABLE = "DROP TABLE IF EXISTS " + NAME_TABLE;
    String  GET_BY_ID = SELECT_ALL + NAME_TABLE + " WHERE " + KEY_ID + " = ?";
    String GET_ALL = SELECT_ALL + NAME_TABLE;

    public RegionDataBaseHelperHandler(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +
                NAME_TABLE +
                " ( " + KEY_ID + " integer primary key autoincrement, " +
                KEY_NAME + " text, " +
                KEY_ID_COUNTRY + " text" + " );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
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
        Region region = new Region();
       // try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
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
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            String str = e.getMessage();
//        }
        //return region;
    }

    @Override
    public List<Region> getAll() {
        List<Region> regions = new ArrayList<>();
        String selectQuery = GET_ALL;

        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    Region region = new Region();
                    int idIndex = cursor.getColumnIndex(KEY_ID);
                    int nameIndex = cursor.getColumnIndex(KEY_NAME);
                    region.setId(cursor.getInt(idIndex));
                    region.setName(cursor.getString(nameIndex));
                    region.setIdCountry(1);
                    regions.add(region);
                } while (cursor.moveToNext());
            }
        }
        catch (Exception e)
        {

        }
        return regions;
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
