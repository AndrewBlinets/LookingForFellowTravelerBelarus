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

    private final String NAME_TABLE_TOKEN = "tokentable";

    private final String KEY_ID = "_id";
    private final String KEY_TOKEN = "token";
    private final String KEY_ID_USER = "iduser";

    private final String CREATE_TOKEN = "create table " + NAME_TABLE_TOKEN +
            " ( " + KEY_ID +" integer primary key autoincrement, " +
            KEY_TOKEN + " text, " +
            KEY_ID_USER + " integer " + " );";

    private final String DELETE_TABLE = "DROP TABLE IF EXISTS " + NAME_TABLE_TOKEN;
    private final String SELECT_ALL = "SELECT * FROM ";
    private final String GET_TOKEN_BY_ID = SELECT_ALL + NAME_TABLE_TOKEN + " WHERE " + KEY_ID + " = ?";
    private final String GET_TOKEN = SELECT_ALL + NAME_TABLE_TOKEN;
    // private final String UPDATE_USER_BY_ID = ;
    // private final String DELETE_USER_BY_ID = ;

    private static int dataBaseVerson;

    public TokenDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TOKEN);
        dataBaseVerson = ConstantsDataBase.DataBaseVersion;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void create(Token token) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TOKEN, token.getToken());
        values.put(KEY_ID_USER, token.getIdUser());
        db.insert(NAME_TABLE_TOKEN, null, values);
        db.close();
    }

    @Override
    public Token getById(int id) {
        Token token = new Token();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(GET_TOKEN_BY_ID, new String[]{String.valueOf(id)});
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