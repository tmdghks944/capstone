package com.example.android.capstone.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.capstone.data.DetaillistContract.*;

public class DetaillistDbHelper extends SQLiteOpenHelper {

    // The database name
    private static final String DATABASE_NAME = "detaillist.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 4;

    // Constructor
    public DetaillistDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create a table to hold waitlist data
        final String SQL_CREATE_DETAILLIST_TABLE = "CREATE TABLE " + DetaillistEntry.TABLE_NAME + " (" +
                DetaillistEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DetaillistEntry.COLUMN_DETAIL_NAME + " TEXT NOT NULL, " +
                DetaillistEntry.COLUMN_DETAIL_SIZE + " INTEGER NOT NULL, " +
                DetaillistEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_DETAILLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // For now simply drop the table and create a new one. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DetaillistEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}