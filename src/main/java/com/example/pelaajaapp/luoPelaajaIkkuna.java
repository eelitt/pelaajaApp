package com.example.pelaajaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Type;
import java.util.ArrayList;


import android.content.SharedPreferences;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import static android.view.View.VISIBLE;

public class luoPelaajaIkkuna extends AppCompatActivity {



    ToggleButton mmrToggleButton;
    EditText asetammrLaatikko;
    EditText etsiBoxi;
    RecyclerView listalaatikko;
    RecyclerView vuoroPelaajatLaatikko;

    private ArrayList<Pelaaja> pelaajalista = new ArrayList<>();
    private ArrayList<Pelaaja> illanPelaajat = new ArrayList<>();

    private MyAdapter myadapter;
    private MyAdapter adapteri;
    private ItemTouchHelper objectItemTouchHelper;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_luo_pelaaja_ikkuna);
        load();
        listalaatikko = findViewById(R.id.listaLaatikko);
        vuoroPelaajatLaatikko = findViewById(R.id.iltalistalaatikko);
        etsiBoxi = findViewById(R.id.etsiBox);


        etsiBoxi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        myadapter = new MyAdapter(pelaajalista);
        listalaatikko.setAdapter(myadapter);
        listalaatikko.setLayoutManager(layoutManager);
        listalaatikko.setItemAnimator(new DefaultItemAnimator());
        listalaatikko.setOnDragListener(MyAdapter.getDragInstance());

        adapteri = new MyAdapter(illanPelaajat);
        vuoroPelaajatLaatikko.setAdapter(adapteri);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this);
        vuoroPelaajatLaatikko.setLayoutManager(layoutManager2);
        vuoroPelaajatLaatikko.setItemAnimator(new DefaultItemAnimator());
        vuoroPelaajatLaatikko.setOnDragListener(MyAdapter.getDragInstance());



    }

    public ArrayList<Pelaaja> getPelaajalista() {
        return pelaajalista;
    }


    private void filter(String text) {
        ArrayList<Pelaaja> filteredList = new ArrayList<>();

        for (Pelaaja listaPelaaja : pelaajalista) {
            if (listaPelaaja.getNimi().toLowerCase().contains(text)) {
                filteredList.add(listaPelaaja);
            }
        }
        myadapter.filterList(filteredList);

    }

    public void showTextField(View view) {


        asetammrLaatikko = findViewById(R.id.asetaMmrLaatikko);
        asetammrLaatikko.setVisibility(VISIBLE);
        counter++;
        if (counter == 2) {
            asetammrLaatikko.setVisibility(View.INVISIBLE);
            counter = 0;
        }
    }

    public void save(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.PelaajaApp", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(pelaajalista);
        String json1 = gson.toJson(illanPelaajat);
        editor.putString("pelaajalista", json);
        editor.putString("illanPelaajat", json1);
        editor.apply();
    }

    private void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.PelaajaApp", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("pelaajalista", null);
        String json1 = sharedPreferences.getString("illanPelaajat", null);
        Type type = new TypeToken<ArrayList<Pelaaja>>() {
        }.getType();
        pelaajalista = gson.fromJson(json, type);
        illanPelaajat = gson.fromJson(json1, type);

        if (pelaajalista == null) {
            pelaajalista = new ArrayList<>();
        }
        if (illanPelaajat == null) {
            illanPelaajat = new ArrayList<>();
        }
    }

    public void luoPelaaja(View view) {

        Pelaaja ApuOlio = new Pelaaja();
        int mmr;
        mmrToggleButton = findViewById(R.id.MmrToggleButton);

        EditText nimiLaatikko = findViewById(R.id.pelaajaNimiLaatikko);
        String nimi = nimiLaatikko.getText().toString();

        if (mmrToggleButton.isChecked()) {
            String apu = asetammrLaatikko.getText().toString();
            mmr = Integer.parseInt(apu);
        } else {
            mmr = ApuOlio.getMmr();
        }

        lisaaPelaaja(nimi, mmr);

        nimiLaatikko.getText().clear();
        if (mmrToggleButton.isChecked()) {
            asetammrLaatikko.getText().clear();
            mmrToggleButton.setChecked(false);
            asetammrLaatikko.setVisibility(View.INVISIBLE);
        }
    }

    private void lisaaPelaaja(String name, int ratinki) {

        pelaajalista.add(new Pelaaja(name, ratinki));
        Log.i("maara", String.valueOf(pelaajalista.size()));

    }


}


