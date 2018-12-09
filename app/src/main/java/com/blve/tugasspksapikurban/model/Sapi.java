package com.blve.tugasspksapikurban.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

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

public class Sapi implements Parcelable {
    private int id;
    private String identitas;
    private int aktivitas;
    private int bulu;
    private int mata;
    private int mulut;
    private int celah_kuku;
    private int dubur;
    private boolean isChecked = false;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

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

    public Sapi() {

    }


    public Sapi(String identitas, int aktivitas, int bulu, int mata, int mulut, int celah_kuku, int dubur) {
        this.identitas = identitas;
        this.aktivitas = aktivitas;
        this.bulu = bulu;
        this.mata = mata;
        this.mulut = mulut;
        this.celah_kuku = celah_kuku;
        this.dubur = dubur;
    }

    public Sapi(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.identitas = getColumnString(cursor, IDENTITAS);
        this.aktivitas = getColumnInt(cursor, AKTIVITAS);
        this.bulu = getColumnInt(cursor, BULU);
        this.mata = getColumnInt(cursor, MATA);
        this.mulut = getColumnInt(cursor, MULUT);
        this.celah_kuku = getColumnInt(cursor, CELAH_KUKU);
        this.dubur = getColumnInt(cursor, DUBUR);
    }



    protected Sapi(Parcel in) {
        this.id = in.readInt();
        this.identitas = in.readString();
        this.aktivitas = in.readInt();
        this.bulu = in.readInt();
        this.mata = in.readInt();
        this.mulut = in.readInt();
        this.celah_kuku = in.readInt();
        this.dubur = in.readInt();
    }

    public static final Parcelable.Creator<Sapi> CREATOR = new Parcelable.Creator<Sapi>() {
        @Override
        public Sapi createFromParcel(Parcel source) {
            return new Sapi(source);
        }

        @Override
        public Sapi[] newArray(int size) {
            return new Sapi[size];
        }
    };

}
