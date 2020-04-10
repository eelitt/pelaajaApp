package com.example.pelaajaapp;
import android.view.View;


public class Pelaaja {

    private String nimi;
    private int mmr = 0;

    public void setNimi(String name) {
        nimi = name;
    }

    public void setMmr(int rating) {
        mmr = rating;
    }

    public String getNimi() {
        return nimi;
    }

    public int getMmr() {
        return mmr;
    }
}