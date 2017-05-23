package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Token;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;

import java.util.List;


public class TokenDataBaseHadler  extends SQLiteOpenHelper implements InterfaseDataBaseHandler<Token> {

    private final String SELECT_ALL = "SELECT * FROM ";
    protected final String KEY_ID = "_id";
    String NAME_TABLE  = "token";
    String DELETE_TABLE = "DROP TABLE IF EXISTS " + NAME_TABLE;
    String  GET_BY_ID = SELECT_ALL + NAME_TABLE + " WHERE " + KEY_ID + " = ?";
    String GET_ALL = SELECT_ALL + NAME_TABLE;

    public TokenDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DataBaseVersion);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NAME_TABLE +
                " ( " + KEY_ID +" integer primary key autoincrement, " +
                KEY_TOKEN + " text, " +
                KEY_ID_USER + " integer " + " );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }
    private final String KEY_TOKEN = "token";
    private final String KEY_ID_USER = "iduser";



//    public TokenDataBaseHadler(Context context) {
//        super(context, ConstantsDataBase.TOKEN);
//        CREATE_TABLE = ;
//    }

    @Override
    public void add(Token token) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TOKEN, token.getToken());
        values.put(KEY_ID_USER, token.getIdUser());
        db.insert(NAME_TABLE, null, values);
        db.close();
    }

    @Override
    public Token getById(int id) {
        Token token = new Token();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int tokenIndex = cursor.getColumnIndex(KEY_TOKEN);
                int idUserIndex = cursor.getColumnIndex(KEY_ID_USER);
                token.setId(cursor.getInt(idIndex));
                token.setToken(cursor.getString(tokenIndex));
                token.setIdUser(cursor.getInt(idUserIndex));
            }
            cursor.close();
            db.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return token;
    }

    @Override
    public List<Token> getAll() {
        return null;
    }

    @Override
    public int update(Token Token) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }

}