package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.User;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;

import java.util.List;

public class UserDataBaseHadler extends BaseClassDataBaseHadler<User> {

    private final String KEY_NAME = "name";
    private final String KEY_SURNAME = "surname";
    private final String KEY_FOTO = "foto";
    private final String KEY_INDIFICATION = "indefication";
    private final String KEY_PHONE = "phone";
    private final String KEY_EMAIL = "email";
    private final String KEY_RATING = "rating";
    private final String KEY_DATA_REGISTRATION = "dataRegistration";

    public UserDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.USER);
        CREATE_TABLE = "add table " + NAME_TABLE +
                " ( " + KEY_ID +" integer primary key autoincrement, " +
                KEY_NAME + " text, " +
                KEY_SURNAME + " text, " +
                KEY_FOTO + " text " + " );";
    }

    @Override
    public void add(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_SURNAME, user.getSurName());
        values.put(KEY_FOTO, user.getFoto());
        db.insert(NAME_TABLE, null, values);
        db.close();
    }

    @Override
    public User getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
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
}
