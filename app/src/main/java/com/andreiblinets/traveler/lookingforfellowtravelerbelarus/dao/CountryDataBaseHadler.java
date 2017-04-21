package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.DTO.CountryDTO;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstansCountry;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;

import java.util.List;

public class CountryDataBaseHadler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<CountryDTO> {

    private static int dataBaseVerson;

    public CountryDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DataBaseVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ConstansCountry.CREATE_COUNTRY_TABLE);
        db.beginTransaction();
        dataBaseVerson = ConstantsDataBase.DataBaseVersion;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ConstansCountry.DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void create(CountryDTO country) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ConstansCountry.KEY_NAME, country.getName());
        values.put(ConstansCountry.KEY_KOD_CURRENCY, country.getKodCurrency());
        db.insert(ConstansCountry.NAME_TABLE_COUNTRY, null, values);
        db.close();
    }

    @Override
    public CountryDTO getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(ConstansCountry.GET_COUNTRY_BY_ID, new String[]{String.valueOf(id)});
        CountryDTO country = new CountryDTO();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(ConstansCountry.KEY_ID);
            int nameIndex = cursor.getColumnIndex(ConstansCountry.KEY_NAME);
            int kodCurrencyIndex = cursor.getColumnIndex(ConstansCountry.KEY_KOD_CURRENCY);
            country.setId(cursor.getInt(idIndex));
            country.setName(cursor.getString(nameIndex));
            country.setKodCurrency(cursor.getString(kodCurrencyIndex));
        }
        cursor.close();
        db.close();
        return country;
    }

    @Override
    public List<CountryDTO> getAll() {
        return null;
    }

    @Override
    public int update(CountryDTO countryDTO) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }


}
