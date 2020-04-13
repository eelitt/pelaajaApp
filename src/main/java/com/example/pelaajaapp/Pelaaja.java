package com.example.pelaajaapp;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;


public class Pelaaja extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
    }

    private String nimi;
    private int mmr = 0;
    public Pelaaja(){}
    public Pelaaja(String name, int ratinki){
       nimi = name;
       mmr = ratinki;
    }
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