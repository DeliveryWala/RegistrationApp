package com.example.arif.registrationapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Arif on 19/09/16.
 */


public class MyDBHelper {
    private final Context m_context;


    public static final String DATABASE_NAME = "registerdatabase";
    public static final String TABLE_NAME = "register";
    public static final int version = 6;
    public static final String SID = "_id";
    public static final String NAME = "Name";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";
    private static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + " (" + SID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT , " + EMAIL + " TEXT , " + PASSWORD + " TEXT );";
    private static final String TAG = "SignupAdapter";
    DatabaseHelper helper;
    ContentValues cValues;
    SQLiteDatabase db;

    public MyDBHelper(Context ctx) {
        this.m_context = ctx;
        helper = new DatabaseHelper(m_context);
    }

    public long insertEntry(String name, String email, String password) {
        cValues = new ContentValues();
        db = helper.getWritableDatabase();
        cValues.put(NAME, name);
        cValues.put(EMAIL, email);
        cValues.put(PASSWORD, password);
        long id = db.insert(TABLE_NAME, null, cValues);
        return id;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        Context context;

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, version);
            this.context = context;
            Message.message(context, "Constructor called");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Message.message(context, "onCreate called");
                db.execSQL(CREATE_TABLE);
            } catch (SQLException e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "onUpgrade called");
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                onCreate(db);
            } catch (SQLException e) {
                Message.message(context, "" + e);


            }
        }
    }

    //---opens the database---
    public MyDBHelper open() throws SQLException {
        db = helper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close() {
        if (db != null)
            db.close();
        if (helper != null)
            helper.close();

    }

    public String checkPasswordMatch(String email) {

        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + PASSWORD + " FROM " + TABLE_NAME + " where " + EMAIL + " = '" + email + "';", null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("Password"));
        cursor.close();
        return password;
    }

    public String checkEmailExists(String email) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + EMAIL + " FROM " + TABLE_NAME + " WHERE " + EMAIL + " = '" + email + "';", null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String eMail = cursor.getString(cursor.getColumnIndex("Email"));
        cursor.close();
        return eMail;
    }

    public String getName(String email) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + NAME + " FROM " + TABLE_NAME + " WHERE " + EMAIL + " = '" + email + "';", null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String name = cursor.getString(cursor.getColumnIndex("Name"));
        cursor.close();
        return name;
    }

}