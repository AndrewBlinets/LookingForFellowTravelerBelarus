package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.User;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;

import java.util.List;

public class UserDataBaseHadler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<User>  {

    private final String NAME_TABLE_USER = "usertable";
    private final String KEY_ID = "_id";
    private final String KEY_NAME = "name";
    private final String KEY_SURNAME = "surname";
    private final String KEY_FOTO = "foto";

    private final String CREATE_USER = "create table " + NAME_TABLE_USER +
            " ( " + KEY_ID +" integer primary key autoincrement, " +
            KEY_NAME + " text, " +
            KEY_SURNAME + " text, " +
            KEY_FOTO + " text " + " );";

    private static final String SELECT_ALL = "SELECT * FROM ";
    private final String GET_USER_BY_ID = SELECT_ALL + NAME_TABLE_USER + " WHERE " + KEY_ID + " = ?";
    private final String GET_USER = SELECT_ALL + NAME_TABLE_USER;
    private final String DELETE_TABLE = "DROP TABLE IF EXISTS " + NAME_TABLE_USER;

    private static int dataBaseVerson;

    public UserDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        dataBaseVerson = ConstantsDataBase.DataBaseVersion;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void create(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_SURNAME, user.getSurName());
        values.put(KEY_FOTO, user.getFoto());
        db.insert(NAME_TABLE_USER, null, values);
        db.close();
    }

    @Override
    public User getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_USER_BY_ID, new String[]{String.valueOf(id)});
        User user = new User();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int nameIndex = cursor.getColumnIndex(KEY_NAME);
            int surNameIndex = cursor.getColumnIndex(KEY_SURNAME);
            int fotoIndex = cursor.getColumnIndex(KEY_FOTO);
            user.setId(cursor.getInt(idIndex));
            user.setName(cursor.getString(nameIndex));
            user.setSurName(cursor.getString(surNameIndex));
            user.setFoto(cursor.getString(fotoIndex));
        }
        cursor.close();
        db.close();
        return user;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
