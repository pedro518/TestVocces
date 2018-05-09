package com.example.pedro.testvocces.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.pedro.testvocces.db.UsersContract.UserEntry;


public class NucleoBD extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TestVocces.db";

    public NucleoBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + UserEntry.TABLE_NAME + " ("
                + UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserEntry.GENDER + " TEXT NOT NULL,"
                + UserEntry.TITLE + " TEXT NOT NULL,"
                + UserEntry.FIRST + " TEXT NOT NULL,"
                + UserEntry.LAST + " TEXT NOT NULL,"
                + UserEntry.DOB + " INTEGER NOT NULL,"
                + UserEntry.PHONE + " TEXT NOT NULL,"
                + UserEntry.LARGE + " TEXT NOT NULL,"
                + UserEntry.MEDIUM + " TEXT NOT NULL,"
                + UserEntry.THUMBNAIL + " TEXT NOT NULL,"
                + UserEntry.VISITS + " INTEGER NOT NULL DEFAULT 0"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
