package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Country;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;

import java.util.List;

public class CountryDataBaseHadler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<Country> {

    private final String SELECT_ALL = "SELECT * FROM ";
    protected final String KEY_ID = "_id";
    String NAME_TABLE  = "country";
    String DELETE_TABLE = "DROP TABLE IF EXISTS " + NAME_TABLE;
    String  GET_BY_ID = SELECT_ALL + NAME_TABLE + " WHERE " + KEY_ID + " = ?";
    String GET_ALL = SELECT_ALL + NAME_TABLE;

    public CountryDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +
                NAME_TABLE +
                " ( " + KEY_ID + " integer primary key autoincrement, " +
                KEY_NAME + " text, " +
                KEY_KOD_CURRENCY + " text " + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }

    private final String KEY_NAME = "name";
    private final String KEY_KOD_CURRENCY = "kodcurrency";

//    public CountryDataBaseHadler(Context context) {
//        super(context, ConstantsDataBase.COUNTRY);
//        CREATE_TABLE = "create table " +
//                NAME_TABLE +
//                " ( " + KEY_ID + " integer primary key autoincrement, " +
//                KEY_NAME + " text, " +
//                KEY_KOD_CURRENCY + " text " + ");";
//    }

    @Override
    public void add(Country country) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, country.getName());
        values.put(KEY_KOD_CURRENCY, country.getKodCurrency());
        db.insert(NAME_TABLE, null, values);
        db.close();
    }

    @Override
    public Country getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
        Country country = new Country();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int nameIndex = cursor.getColumnIndex(KEY_NAME);
            int kodCurrencyIndex = cursor.getColumnIndex(KEY_KOD_CURRENCY);
            country.setId(cursor.getInt(idIndex));
            country.setName(cursor.getString(nameIndex));
            country.setKodCurrency(cursor.getString(kodCurrencyIndex));
        }
        cursor.close();
        db.close();
        return country;
    }

    @Override
    public List<Country> getAll() {
        return null;
    }

    @Override
    public int update(Country country) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
