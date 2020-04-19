package com.example.pelaajaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static android.view.View.VISIBLE;

public class luoPelaajaIkkuna extends AppCompatActivity implements MyAdapter.MyViewHolder.ClickListener {

    ArrayList<Pelaaja> pelaajalista = new ArrayList<Pelaaja>();
    ToggleButton mmrToggleButton;
    EditText asetammrLaatikko;
    EditText etsiBoxi;
    RecyclerView listalaatikko;
    private RecyclerView.LayoutManager layoutManager;
    private MyAdapter myadapter;
    private ActionModeCallback actionModeCallback = new ActionModeCallback();
    private ActionMode actionMode;


    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luo_pelaaja_ikkuna);
        load();
        listalaatikko = findViewById(R.id.listaLaatikko);
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

        layoutManager = new LinearLayoutManager(this);
        myadapter = new MyAdapter(pelaajalista, this);
        listalaatikko.setAdapter(myadapter);
        listalaatikko.setLayoutManager(layoutManager);
        listalaatikko.setItemAnimator(new DefaultItemAnimator());

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


    @Override
    public void onItemClicked(int position) {

        if (actionMode != null) {

            toggleSelection(position);
        } else {
            // myadapter.removePlayer(position);
        }
    }

    @Override
    public boolean onItemLongClicked(int position) {

        if (actionMode == null) {

            actionMode = startActionMode(actionModeCallback);

        }
        toggleSelection(position);
        return true;
    }

    private void toggleSelection(int position) {
        myadapter.toggleSelection(position);
        int count = myadapter.getSelectedItemCount();
        if (count == 0) {
            actionMode.finish();
        } else {

            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }

    private class ActionModeCallback implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.selected_menu, menu);

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_remove:
                    myadapter.removePlayers(myadapter.getSelectedPlayers());
                    mode.finish();
                    return true;

                default:
                    return false;
            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            myadapter.clearSelection();
            actionMode = null;
        }
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
        editor.putString("pelaajalista", json);
        editor.apply();
    }

    private void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.PelaajaApp", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("pelaajalista", null);
        Type type = new TypeToken<ArrayList<Pelaaja>>() {
        }.getType();
        pelaajalista = gson.fromJson(json, type);

        if (pelaajalista == null) {
            pelaajalista = new ArrayList<>();
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

        lisaaPelaaja(nimi, mmr, false);

        nimiLaatikko.getText().clear();
        if (mmrToggleButton.isChecked()) {
            asetammrLaatikko.getText().clear();
            mmrToggleButton.setChecked(false);
            asetammrLaatikko.setVisibility(View.INVISIBLE);
        }
    }

    private void lisaaPelaaja(String name, int ratinki, boolean active) {

        pelaajalista.add(new Pelaaja(name, ratinki, active));
        Log.i("maara", String.valueOf(pelaajalista.size()));

    }


}


