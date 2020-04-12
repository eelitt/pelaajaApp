package com.example.pelaajaapp;
import android.view.View;

import java.io.Serializable;


public class Pelaaja implements Serializable {

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