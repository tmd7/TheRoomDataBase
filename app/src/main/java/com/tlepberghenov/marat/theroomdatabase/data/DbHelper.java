package com.tlepberghenov.marat.theroomdatabase.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = DbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "temperature.db";

    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL request for creating a table
        String SQL_CREATE_DATA_TABLE = "CREATE TABLE " + TheRoomDataBaseContract.TemperatureHumidity.TABLE_NAME + " ("
                + TheRoomDataBaseContract.TemperatureHumidity._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TheRoomDataBaseContract.TemperatureHumidity.COLUMN_TEMPERATURE + " INTEGER NOT NULL DEFAULT 0, "
                + TheRoomDataBaseContract.TemperatureHumidity.COLUMN_HUMIDITY + " INTEGER NOT NULL DEFAULT 0, "
                + TheRoomDataBaseContract.TemperatureHumidity.COLUM_ROOM + " TEXT NOT NULL DEFAULT 0);";

        //run creating the table
        db.execSQL(SQL_CREATE_DATA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Method for update table

        //Log
        Log.w("SQLite", "Updating from version " + oldVersion + " to version " + newVersion);
    }
}
