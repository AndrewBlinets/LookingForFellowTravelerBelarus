package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsMainUser;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.DTO.MainUser;

import java.util.List;

public class UserDataBaseHadler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<MainUser>  {

    private static int dataBaseVerson;

    public UserDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ConstantsMainUser.CREATE_MAIN_USER_APP);
        db.beginTransaction();
        dataBaseVerson = ConstantsDataBase.DataBaseVersion;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ConstantsMainUser.DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void create(MainUser mainUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ConstantsMainUser.KEY_NAME, mainUser.getName());
        values.put(ConstantsMainUser.KEY_SURNAME, mainUser.getSurName());
        values.put(ConstantsMainUser.KEY_FOTO, mainUser.getFoto());
        values.put(ConstantsMainUser.KEY_TOKEN, mainUser.getToken());
        db.insert(ConstantsMainUser.NAME_TABLE_MAIN_USER, null, values);
        db.close();
    }

    @Override
    public MainUser getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(ConstantsMainUser.GET_USER_BY_ID, new String[]{String.valueOf(id)});
        MainUser mainUser = new MainUser();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(ConstantsMainUser.KEY_ID);
            int nameIndex = cursor.getColumnIndex(ConstantsMainUser.KEY_NAME);
            int surNameIndex = cursor.getColumnIndex(ConstantsMainUser.KEY_SURNAME);
            int fotoIndex = cursor.getColumnIndex(ConstantsMainUser.KEY_FOTO);
            int tokenIndex = cursor.getColumnIndex(ConstantsMainUser.KEY_TOKEN);
            mainUser.setId(cursor.getInt(idIndex));
            mainUser.setName(cursor.getString(nameIndex));
            mainUser.setSurName(cursor.getString(surNameIndex));
            mainUser.setFoto(cursor.getString(fotoIndex));
            mainUser.setToken(cursor.getString(tokenIndex));
        }
        cursor.close();
        db.close();
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
