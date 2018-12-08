package com.blve.tugasspksapikurban.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.blve.tugasspksapikurban.model.PotensiSapi;

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

    public ArrayList<PotensiSapi> query(){
        ArrayList<PotensiSapi> arrayList = new ArrayList<PotensiSapi>();
        Cursor cursor = database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null,_ID +" DESC"
                ,null);
        cursor.moveToFirst();
        PotensiSapi potensiSapi;
        if (cursor.getCount()>0) {
            do {

                potensiSapi = new PotensiSapi();
                potensiSapi.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                potensiSapi.setIdentitas(cursor.getString(cursor.getColumnIndexOrThrow(IDENTITAS)));
                potensiSapi.setAktivitas(cursor.getInt(cursor.getColumnIndexOrThrow(AKTIVITAS)));
                potensiSapi.setBulu(cursor.getInt(cursor.getColumnIndexOrThrow(BULU)));
                potensiSapi.setMata(cursor.getInt(cursor.getColumnIndexOrThrow(MATA)));
                potensiSapi.setMulut(cursor.getInt(cursor.getColumnIndexOrThrow(MULUT)));
                potensiSapi.setCelah_kuku(cursor.getInt(cursor.getColumnIndexOrThrow(CELAH_KUKU)));
                potensiSapi.setDubur(cursor.getInt(cursor.getColumnIndexOrThrow(DUBUR)));

                arrayList.add(potensiSapi);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(PotensiSapi potensiSapi){
        ContentValues initialValues =  new ContentValues();
        initialValues.put(IDENTITAS, potensiSapi.getIdentitas());
        initialValues.put(AKTIVITAS, potensiSapi.getAktivitas());
        initialValues.put(BULU, potensiSapi.getBulu());
        initialValues.put(MATA, potensiSapi.getMata());
        initialValues.put(MULUT, potensiSapi.getMulut());
        initialValues.put(CELAH_KUKU, potensiSapi.getCelah_kuku());
        initialValues.put(DUBUR, potensiSapi.getDubur());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public int update(PotensiSapi potensiSapi){
        ContentValues args = new ContentValues();
        args.put(IDENTITAS, potensiSapi.getIdentitas());
        args.put(AKTIVITAS, potensiSapi.getAktivitas());
        args.put(BULU, potensiSapi.getBulu());
        args.put(MATA, potensiSapi.getMata());
        args.put(MULUT, potensiSapi.getMulut());
        args.put(CELAH_KUKU, potensiSapi.getCelah_kuku());
        args.put(DUBUR, potensiSapi.getDubur());
        return database.update(DATABASE_TABLE, args, _ID + "= '" + potensiSapi.getId() + "'", null);
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
