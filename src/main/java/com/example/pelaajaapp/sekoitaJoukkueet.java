package com.example.pelaajaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class sekoitaJoukkueet extends AppCompatActivity {

    private ArrayList<Pelaaja> joukkueA;
    private ArrayList<Pelaaja> joukkueB = new ArrayList<>();




    RecyclerView joukkueAView;
    RecyclerView joukkueBView;
    private MyAdapter joukkueAadapteri;
    private MyAdapter joukkueBadapteri;

public sekoitaJoukkueet(){

}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sekoita_joukkueet);
        setupRecyclerViews();
        Button sekotaTiimitButton = findViewById(R.id.sekoitaJoukkueetButton);


        sekotaTiimitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekoitatiimit();
            }
            });

    }


    public void sekoitatiimit() {
    SharedPreferences sharedPreferences = getSharedPreferences("com.example.PelaajaApp", MODE_PRIVATE);
    Gson gson = new Gson();
    String json = sharedPreferences.getString("illanPelaajat", null);
    Type type = new TypeToken<ArrayList<Pelaaja>>() {
    }.getType();
        joukkueA = gson.fromJson(json, type);
        if(joukkueA == null){
            joukkueA = new ArrayList<>();
        }
        //joukkueA = getIntent().getParcelableArrayListExtra("illanpelaajat");
            }


    private void setupRecyclerViews(){
 joukkueAView = findViewById(R.id.joukkueAlista);
 RecyclerView.LayoutManager layoutManagerA = new LinearLayoutManager(this);
 joukkueAadapteri = new MyAdapter(joukkueA);
 joukkueAView.setAdapter(joukkueAadapteri);
 joukkueAView.setLayoutManager(layoutManagerA);
 joukkueAView.setItemAnimator(new DefaultItemAnimator());

 joukkueBView = findViewById(R.id.joukkueBlista);
 RecyclerView.LayoutManager layoutManagerB = new LinearLayoutManager(this);
 joukkueBadapteri = new MyAdapter(joukkueB);
 joukkueBView.setAdapter(joukkueBadapteri);
 joukkueBView.setLayoutManager(layoutManagerB);
 joukkueBView.setItemAnimator(new DefaultItemAnimator());


    }


}

