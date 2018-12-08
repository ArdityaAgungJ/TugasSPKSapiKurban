package com.blve.tugasspksapikurban.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import static android.provider.BaseColumns._ID;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.AKTIVITAS;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.BULU;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.CELAH_KUKU;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.DUBUR;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.IDENTITAS;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.MATA;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.MULUT;
import static com.blve.tugasspksapikurban.db.DBContract.getColumnInt;
import static com.blve.tugasspksapikurban.db.DBContract.getColumnString;

public class PotensiSapi implements Parcelable {
    private int id;
    private String identitas;
    private int aktivitas;
    private int bulu;
    private int mata;
    private int mulut;
    private int celah_kuku;
    private int dubur;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentitas() {
        return identitas;
    }

    public void setIdentitas(String identitas) {
        this.identitas = identitas;
    }

    public int getAktivitas() {
        return aktivitas;
    }

    public void setAktivitas(int aktivitas) {
        this.aktivitas = aktivitas;
    }

    public int getBulu() {
        return bulu;
    }

    public void setBulu(int bulu) {
        this.bulu = bulu;
    }

    public int getMata() {
        return mata;
    }

    public void setMata(int mata) {
        this.mata = mata;
    }

    public int getMulut() {
        return mulut;
    }

    public void setMulut(int mulut) {
        this.mulut = mulut;
    }

    public int getCelah_kuku() {
        return celah_kuku;
    }

    public void setCelah_kuku(int celah_kuku) {
        this.celah_kuku = celah_kuku;
    }

    public int getDubur() {
        return dubur;
    }

    public void setDubur(int dubur) {
        this.dubur = dubur;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.identitas);
        dest.writeInt(this.aktivitas);
        dest.writeInt(this.bulu);
        dest.writeInt(this.mata);
        dest.writeInt(this.mulut);
        dest.writeInt(this.celah_kuku);
        dest.writeInt(this.dubur);
    }

    public PotensiSapi() {

    }

    public PotensiSapi(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.identitas = getColumnString(cursor, IDENTITAS);
        this.aktivitas = getColumnInt(cursor, AKTIVITAS);
        this.bulu = getColumnInt(cursor, BULU);
        this.mata = getColumnInt(cursor, MATA);
        this.mulut = getColumnInt(cursor, MULUT);
        this.celah_kuku = getColumnInt(cursor, CELAH_KUKU);
        this.dubur = getColumnInt(cursor, DUBUR);
    }

    protected PotensiSapi(Parcel in) {
        this.id = in.readInt();
        this.identitas = in.readString();
        this.aktivitas = in.readInt();
        this.bulu = in.readInt();
        this.mata = in.readInt();
        this.mulut = in.readInt();
        this.celah_kuku = in.readInt();
        this.dubur = in.readInt();
    }

    public static final Parcelable.Creator<PotensiSapi> CREATOR = new Parcelable.Creator<PotensiSapi>() {
        @Override
        public PotensiSapi createFromParcel(Parcel source) {
            return new PotensiSapi(source);
        }

        @Override
        public PotensiSapi[] newArray(int size) {
            return new PotensiSapi[size];
        }
    };

}
