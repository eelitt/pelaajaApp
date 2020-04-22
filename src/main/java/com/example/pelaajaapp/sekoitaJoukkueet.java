package com.example.pelaajaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class sekoitaJoukkueet extends AppCompatActivity {

    private ArrayList<Pelaaja> joukkueA = new ArrayList<>();
    private ArrayList<Pelaaja> joukkueB = new ArrayList<>();
    luoPelaajaIkkuna apuOlio = new luoPelaajaIkkuna();
    private ArrayList<Pelaaja> lista = apuOlio.getPelaajalista();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sekoita_joukkueet);

    }

    public boolean sekoitaJoukkueet() {

        Collections.sort(lista, new Comparator<Pelaaja>() {
            @Override
            public int compare(Pelaaja o1, Pelaaja o2) {
                int mmr1 = o1.getMmr();
                int mmr2 = o2.getMmr();

                return mmr2-mmr1;
            }
        });



    return true;
    }

}
