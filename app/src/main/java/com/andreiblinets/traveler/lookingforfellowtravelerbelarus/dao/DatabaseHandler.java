package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.MainUser;

import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper implements InterfaseDatabaseHandler<MainUser>  {

    private int dataBaseVerson;
    private Context context;

    private static final String TABLE_MAIN_USER = "mainuser";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_FOTO = "foto";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_ID = "_id";

    public DatabaseHandler(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DataBaseVersion);
        //this.dataBaseVerson = ConstantsDataBase.DataBaseVersion;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ConstantsDataBase.REGION_TABLE);
        db.execSQL(ConstantsDataBase.CITY_TABLE);
        db.execSQL(ConstantsDataBase.DATE_LAST_UPDATE);
        db.execSQL(ConstantsDataBase.MAIN_USER_APP);
        db.execSQL(ConstantsDataBase.COUNTRY_TABLE);
        db.beginTransaction();
        this.dataBaseVerson = ConstantsDataBase.DataBaseVersion;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAIN_USER);

        onCreate(db);
    }

    @Override
    public void create(MainUser mainUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, mainUser.getName());
        values.put(KEY_SURNAME, mainUser.getSurName());
        values.put(KEY_FOTO, mainUser.getFoto());
        values.put(KEY_TOKEN, mainUser.getToken());

        db.insert(TABLE_MAIN_USER, null, values);
        db.close();
    }

    @Override
    public MainUser getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String querystr = "SELECT *  " +
                "FROM " + TABLE_MAIN_USER + " WHERE _id = ?";
        Cursor cursor = db.rawQuery(querystr, new String[]{String.valueOf(id)});
//                query(TABLE_MAIN_USER, new String[] { KEY_NAME, KEY_SURNAME, KEY_FOTO, KEY_TOKEN},
//                KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        MainUser mainUser = new MainUser();
        if (cursor != null){
            if(cursor.moveToFirst())
            {
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int nameIndex = cursor.getColumnIndex(KEY_NAME);
                int surNameIndex = cursor.getColumnIndex(KEY_SURNAME);
                int fotoIndex = cursor.getColumnIndex(KEY_FOTO);
                int tokenIndex = cursor.getColumnIndex(KEY_TOKEN);
                mainUser.setId(cursor.getInt(idIndex));
                mainUser.setName(cursor.getString(nameIndex));
                mainUser.setSurName(cursor.getString(surNameIndex));
                mainUser.setFoto(cursor.getString(fotoIndex));
                mainUser.setToken(cursor.getString(tokenIndex));
            }
        }
        return mainUser;
    }

    @Override
    public List<MainUser> getAll() {
        return null;
    }

    @Override
    public int update(MainUser mainUser) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
