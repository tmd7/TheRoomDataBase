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
        public final static String COLUMN_ROOM = "room";
    }

    public static final class Rooms implements BaseColumns {
        public final static String TABLE_NAME = "rooms";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_SIZE = "size";
        public final static String COLUMN_DESCRIPTION ="description";
    }
}
