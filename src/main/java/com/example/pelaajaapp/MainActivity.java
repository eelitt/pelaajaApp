package com.example.pelaajaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
          public void luoIkkuna(View view)
        {
            Intent pelaajaIkkuna = new Intent(this, luoPelaajaIkkuna.class);
            startActivity(pelaajaIkkuna);
        }
        public void luoSekoitaJoukkueIkkuna(View view) {
            Intent sekoitajoukkueIkkuna = new Intent(this, sekoitaJoukkueet.class);
            startActivity(sekoitajoukkueIkkuna);
        }
        @Override
    public boolean onCreateOptionsMenu(Menu menu){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.startmenu, menu);
            return true;
        }
         public boolean onOptionsItemSelected(MenuItem item){

        return true;
        }


}
