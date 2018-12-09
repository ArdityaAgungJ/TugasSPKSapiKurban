package com.blve.tugasspksapikurban.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.blve.tugasspksapikurban.model.Sapi;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.blve.tugasspksapikurban.db.DBContract.TABLE;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.AKTIVITAS;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.BULU;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.CELAH_KUKU;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.DUBUR;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.IDENTITAS;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.MATA;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.MULUT;

public class PotensiHelper {
    private static String DATABASE_TABLE = TABLE;
    private Context context;
    private DBHelper dataBaseHelper;

    private SQLiteDatabase database;

    public PotensiHelper(Context context){
        this.context = context;
    }

    public PotensiHelper open() throws SQLException {
        dataBaseHelper = new DBHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public ArrayList<Sapi> query(){
        ArrayList<Sapi> arrayList = new ArrayList<Sapi>();
        Cursor cursor = database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null,_ID +" DESC"
                ,null);
        cursor.moveToFirst();
        Sapi sapi;
        if (cursor.getCount()>0) {
            do {

                sapi = new Sapi();
                sapi.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                sapi.setIdentitas(cursor.getString(cursor.getColumnIndexOrThrow(IDENTITAS)));
                sapi.setAktivitas(cursor.getInt(cursor.getColumnIndexOrThrow(AKTIVITAS)));
                sapi.setBulu(cursor.getInt(cursor.getColumnIndexOrThrow(BULU)));
                sapi.setMata(cursor.getInt(cursor.getColumnIndexOrThrow(MATA)));
                sapi.setMulut(cursor.getInt(cursor.getColumnIndexOrThrow(MULUT)));
                sapi.setCelah_kuku(cursor.getInt(cursor.getColumnIndexOrThrow(CELAH_KUKU)));
                sapi.setDubur(cursor.getInt(cursor.getColumnIndexOrThrow(DUBUR)));

                arrayList.add(sapi);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Sapi sapi){
        ContentValues initialValues =  new ContentValues();
        initialValues.put(IDENTITAS, sapi.getIdentitas());
        initialValues.put(AKTIVITAS, sapi.getAktivitas());
        initialValues.put(BULU, sapi.getBulu());
        initialValues.put(MATA, sapi.getMata());
        initialValues.put(MULUT, sapi.getMulut());
        initialValues.put(CELAH_KUKU, sapi.getCelah_kuku());
        initialValues.put(DUBUR, sapi.getDubur());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public int update(Sapi sapi){
        ContentValues args = new ContentValues();
        args.put(IDENTITAS, sapi.getIdentitas());
        args.put(AKTIVITAS, sapi.getAktivitas());
        args.put(BULU, sapi.getBulu());
        args.put(MATA, sapi.getMata());
        args.put(MULUT, sapi.getMulut());
        args.put(CELAH_KUKU, sapi.getCelah_kuku());
        args.put(DUBUR, sapi.getDubur());
        return database.update(DATABASE_TABLE, args, _ID + "= '" + sapi.getId() + "'", null);
    }

    public int delete(int id){
        return database.delete(DATABASE_TABLE, _ID + " = '"+id+"'", null);
    }

    public Cursor queryByIdProvider(String id){
        return database.query(DATABASE_TABLE,null
                ,_ID + " = ?"
                ,new String[]{id}
                ,null
                ,null
                ,null
                ,null);
    }
    public Cursor queryProvider(){
        return database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null
                ,_ID + " DESC");
    }
    public long insertProvider(ContentValues values){
        return database.insert(DATABASE_TABLE,null,values);
    }
    public int updateProvider(String id,ContentValues values){
        return database.update(DATABASE_TABLE,values,_ID +" = ?",new String[]{id} );
    }
    public int deleteProvider(String id){
        return database.delete(DATABASE_TABLE,_ID + " = ?", new String[]{id});
    }
}
