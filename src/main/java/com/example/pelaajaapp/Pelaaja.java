package com.example.pelaajaapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Pelaaja implements Parcelable {

    private String nimi;
    private int mmr = 0;


    public Pelaaja(){}
    public Pelaaja(String nimi, int mmr){
       this.nimi = nimi;
       this.mmr = mmr;


    }

    protected Pelaaja(Parcel in) {
        nimi = in.readString();
        mmr = in.readInt();
    }

    public static final Creator<Pelaaja> CREATOR = new Creator<Pelaaja>() {
        @Override
        public Pelaaja createFromParcel(Parcel in) {
            return new Pelaaja(in);
        }

        @Override
        public Pelaaja[] newArray(int size) {
            return new Pelaaja[size];
        }
    };

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setMmr(int mmr) {
        this.mmr = mmr;
    }

    public String getNimi() {
        return nimi;
    }

    public int getMmr() {
        return mmr;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nimi);
        dest.writeInt(mmr);
    }
}