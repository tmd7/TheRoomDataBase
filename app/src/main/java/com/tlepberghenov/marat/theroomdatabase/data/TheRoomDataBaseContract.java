package com.tlepberghenov.marat.theroomdatabase.data;

import android.provider.BaseColumns;

public final class TheRoomDataBaseContract {

    public TheRoomDataBaseContract() {
    }

    public static final class TemperatureHumidity implements BaseColumns {
        public final static String TABLE_NAME = "data";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_TEMPERATURE = "temperature";
        public final static String COLUMN_HUMIDITY = "humidity";
        public final static String COLUM_ROOM = "room";
    }
}
