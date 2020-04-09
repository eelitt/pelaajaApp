package com.example.pelaajaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.view.View.VISIBLE;

public class luoPelaajaIkkuna extends AppCompatActivity {

    Button mmrToggleButton;
    EditText asetammrLaatikko;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luo_pelaaja_ikkuna);
    }

    public void showTextField(View view) {


                mmrToggleButton = findViewById(R.id.MmrToggleButton);
                asetammrLaatikko = findViewById(R.id.asetaMmrLaatikko);
                asetammrLaatikko.setVisibility(VISIBLE);
                counter++;
                if(counter == 2)
                {
                    asetammrLaatikko.setVisibility(View.INVISIBLE);
                    counter = 0;
                }
            }


    public void luoPelaaja()
    {
    Pelaaja player;
    player = new Pelaaja();
    }

}

