package com.example.studynook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UserOpenHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "userdatabase.db";
    static final int DATABASE_VERSION = 1;

    static final String USERNAME = "username";
    static final String PASSWORD = "password";
    static final String USER_TABLE = "tableUser";
    static final String ID = "_id";


    public UserOpenHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + USER_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERNAME + " TEXT, " + PASSWORD + " TEXT)";
        Log.d(MainActivity.TAG, "onCreate: " + sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertUser(User user)
    {
        ContentValues content = new ContentValues();
        content.put(USERNAME, user.getUsername());
        content.put(PASSWORD, user.getPassword());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(USER_TABLE, null, content);
        db.close();
    }

    public User getSelectUserById(int idParam)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(USER_TABLE, new String[]{ID, USERNAME, PASSWORD},
                ID + "=?", new String[]{"" + idParam}, null, null, null);

        User user = null;

        if(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            user = new User(username, password);
            user.setId(id);
        }

        return user;
    }

    public Cursor getSelectAllCursor(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(USER_TABLE, new String[]{ID, USERNAME, PASSWORD},
                null, null, null, null, null);
        return cursor;
    }

    public List<User> getSelectAllUser(){
        List<User> users = new ArrayList<>();
        Cursor cursor = getSelectAllCursor();

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            User user = new User(username, password);
            user.setId(id);
            users.add(user);
        }

        return users;
    }

    public void deleteAllContacts(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(USER_TABLE, null,null);
        db.close();
    }
}
