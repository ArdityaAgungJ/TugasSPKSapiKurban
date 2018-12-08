package com.blve.tugasspksapikurban.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.blve.tugasspksapikurban.db.DBContract.TABLE;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.AKTIVITAS;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.BULU;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.CELAH_KUKU;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.DUBUR;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.IDENTITAS;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.MATA;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.MULUT;

public class DBHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbnoteapp";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_NOTE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s INTEGER NOT NULL," +
                    " %s INTEGER NOT NULL," +
                    " %s INTEGER NOT NULL," +
                    " %s INTEGER NOT NULL," +
                    " %s INTEGER NOT NULL," +
                    " %s INTEGER NOT NULL)",
                    TABLE,
                    DBContract.TableColumns._ID,
                    IDENTITAS,
                    AKTIVITAS,
                    BULU,
                    MATA,
                    MULUT,
                    CELAH_KUKU,
                    DUBUR
    );

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_NOTE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
