package com.example.pelaajaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

private ArrayList<Pelaaja> apuPelaajalista;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
       public TextView apuNimilaatikko;
        public TextView apuMmrasetaLaatikko;

        public MyViewHolder(View v) {
            super(v);

            apuNimilaatikko =  v.findViewById(R.id.nameBox);
            apuMmrasetaLaatikko = v.findViewById(R.id.ratingBox);
        }
    }
    public MyAdapter(ArrayList<Pelaaja> omaPelaajalista){
        apuPelaajalista = omaPelaajalista;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View va =  LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_player, parent, false);
        MyViewHolder mvh = new MyViewHolder(va);
        return mvh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        Pelaaja currentPelaaja = apuPelaajalista.get(position);
        holder.apuNimilaatikko.setText(currentPelaaja.getNimi());
        holder.apuMmrasetaLaatikko.setText(String.valueOf(currentPelaaja.getMmr()));
    }
    @Override

    public int getItemCount(){
        return apuPelaajalista.size();
    }

}
