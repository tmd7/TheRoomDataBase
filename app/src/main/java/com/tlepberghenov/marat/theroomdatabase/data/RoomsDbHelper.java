package com.tlepberghenov.marat.theroomdatabase.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RoomsDbHelper extends SQLiteOpenHelper {

    private final static String LOG_TAG = RoomsDbHelper.class.getSimpleName();

    private final static String DATABASE_NAME = "rooms.db";

    private final static int DATABASE_VERSION = 1;

    public RoomsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_DATA_TABLE = "CREATE TABLE " + TheRoomDataBaseContract.Rooms.TABLE_NAME + " ("
                + TheRoomDataBaseContract.Rooms._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TheRoomDataBaseContract.Rooms.COLUMN_NAME + " TEXT NOT NULL DEFAULT 0, "
                + TheRoomDataBaseContract.Rooms.COLUMN_SIZE + " INTEGER NOT NULL DEFAULT 0, "
                + TheRoomDataBaseContract.Rooms.COLUMN_DESCRIPTION + " TEXT NOT NULL DEFAULT 0);";
        db.execSQL(SQL_CREATE_DATA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Method for update table

        //Log
        Log.w(LOG_TAG, "Updating from version " + oldVersion + " to version " + newVersion);
    }
}
