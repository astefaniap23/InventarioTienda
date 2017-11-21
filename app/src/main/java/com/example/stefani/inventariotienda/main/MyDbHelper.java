package com.example.stefani.inventariotienda.main;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefani on 21/11/2017.
 */

public class MyDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pym.db";

    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " (" +
                UserContract.UserEntry.COLUMN_ID + " INTEGER PRIMARY KEY," +
                UserContract.UserEntry.COLUMN_LOGIN + " TEXT," +
                UserContract.UserEntry.COLUMN_PASSWORD + " TEXT," +
                UserContract.UserEntry.COLUMN_USERTYPE + " TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME);
        onCreate(db);
    }
    public List<User> getAllUsersFromDataBase() {
        List<User> userList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + UserContract.UserEntry.TABLE_NAME ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getLong(0));
                user.setLogin(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setUserType(cursor.getString(3));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        return userList;
    }


}