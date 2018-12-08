package com.blve.tugasspksapikurban.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DBContract {
    public static String TABLE = "potensi_sapi_kurban";
    public static final String AUTHORITY = "com.blve.tugasspksapikurban";


    public static final class TableColumns implements BaseColumns {
        public static String IDENTITAS = "identitas";
        public static String AKTIVITAS= "aktivitas";
        public static String BULU = "bulu";
        public static String MATA = "mata";
        public static String MULUT = "mulut";
        public static String CELAH_KUKU = "celah_kuku";
        public static String DUBUR = "dubur";
    }

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }
}
