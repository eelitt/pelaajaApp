package com.example.pelaajaapp;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnTouchListener , ItemTouchHelperAdapter {

    private static final String TAG = MyAdapter.class.getSimpleName();



    private ArrayList<Pelaaja> apuPelaajalista;

public MyAdapter(ArrayList<Pelaaja> omaPelaajalista){
    super();
    apuPelaajalista = omaPelaajalista;



}



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        final int layout = R.layout.activity_player;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new MyViewHolder(v);
    }



    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Pelaaja currentPelaaja = apuPelaajalista.get(position);
        sortList(apuPelaajalista);
        holder.apuNimilaatikko.setText(currentPelaaja.getNimi());
        holder.apuMmrasetaLaatikko.setText(String.valueOf(currentPelaaja.getMmr()));
        holder.layoutti.setTag(position);
        holder.layoutti.setOnDragListener(new DragListener());
        holder.layoutti.setOnTouchListener(this);


    }

    @Override

    public int getItemCount(){

        return apuPelaajalista.size();
    }

    public void filterList(ArrayList<Pelaaja> filteredList){

       apuPelaajalista = filteredList;
        notifyDataSetChanged();

    }
@Override
public boolean onTouch(View v, MotionEvent event){

    switch(event.getAction()){
        case MotionEvent.ACTION_DOWN:
            ClipData data = ClipData.newPlainText(" ", " ");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                v.startDragAndDrop(data, shadowBuilder, v, 0);
            }else{

            }
            return true;
    }
    return false;
}
    public void sortList(ArrayList<Pelaaja> lista) {
        Collections.sort(lista, new Comparator<Pelaaja>() {
            @Override
            public int compare(Pelaaja o1, Pelaaja o2) {
                int mmr1 = o1.getMmr();
                int mmr2 = o2.getMmr();

                return mmr1-mmr2;
            }
        });
    }

    ArrayList<Pelaaja> getList() {
    return apuPelaajalista;
    }

    public void updateList(ArrayList<Pelaaja> list) {
    apuPelaajalista = list;
    }

static DragListener getDragInstance() {

        return new DragListener();

}

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
       Collections.swap(apuPelaajalista, fromPosition, toPosition);
       notifyItemMoved(fromPosition, toPosition);
       return true;
    }

    @Override
    public void onItemDismiss(int position) {
    apuPelaajalista.remove(position);
    notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{


        public TextView apuNimilaatikko;
        public TextView apuMmrasetaLaatikko;
         RelativeLayout layoutti;


        public MyViewHolder(View v) {
            super(v);

            apuNimilaatikko =  v.findViewById(R.id.nameBox);
            apuMmrasetaLaatikko = v.findViewById(R.id.ratingBox);
            layoutti = v.findViewById(R.id.pelaajaRivi);


        }


        @Override
        public void onItemSelected() {
        apuNimilaatikko.setTextColor(Color.LTGRAY);
        apuMmrasetaLaatikko.setTextColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            apuNimilaatikko.setTextColor(Color.BLACK);
            apuMmrasetaLaatikko.setTextColor(Color.BLACK);
        }




    }











}




