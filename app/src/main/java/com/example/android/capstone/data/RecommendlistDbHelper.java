package com.example.android.capstone.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.capstone.data.RecommendlistContract.*;

public class RecommendlistDbHelper extends SQLiteOpenHelper {

    // The database name
    private static final String DATABASE_NAME = "recommendlist.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public RecommendlistDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create a table to hold waitlist data
        final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE " + RecommendlistEntry.TABLE_NAME + " (" +
                RecommendlistEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                RecommendlistEntry.COLUMN_GUEST_NAME + " TEXT NOT NULL, " +
                RecommendlistEntry.COLUMN_PARTY_SIZE + " INTEGER NOT NULL, " +
                RecommendlistEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_WAITLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // For now simply drop the table and create a new one. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RecommendlistEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}