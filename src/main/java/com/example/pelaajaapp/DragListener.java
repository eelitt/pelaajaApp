package com.example.pelaajaapp;

import android.util.Log;
import android.view.DragEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class DragListener implements View.OnDragListener {
    private Boolean isDropped = false;
   private int averageRating;
    private int ratingSum = 0;
ArrayList<Pelaaja> testi = new ArrayList<>();
    public int getAverageRating() {
        return averageRating;
    }

    public ArrayList<Pelaaja> getTesti() {
        return testi;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {

        switch (event.getAction()) {

            case DragEvent.ACTION_DROP:
                isDropped = true;
                 int positionTarget = -1;
                Log.i("testi", String.valueOf(testi));


                View viewSource = (View) event.getLocalState();
                Log.i("viewsource", String.valueOf(viewSource));

                int viewId = v.getId();
                Log.i("viewID listakaikista", String.valueOf(viewId));
                //final int layout = R.layout.activity_player;
                final int listaKaikistaPelaajista = R.id.listaLaatikko;
                final int listaVuoronPelaajista = R.id.iltalistalaatikko;

                switch (viewId) {
                  // case layout:
                    case listaKaikistaPelaajista:
                    case listaVuoronPelaajista:
                        RecyclerView target;


                        switch (viewId) {


                            case listaKaikistaPelaajista:
                               target = v.getRootView().findViewById(listaKaikistaPelaajista);
                               Log.i("listakaikistapleejiast", String.valueOf(viewId));


                                break;
                            case listaVuoronPelaajista:
                               target = v.getRootView().findViewById(listaVuoronPelaajista);
                                Log.i("listvuoronpeejiast", String.valueOf(viewId));
                                Log.i("targetvuyoropelajat", String.valueOf(target));

                                break;
                            default:
                                target = (RecyclerView) v.getParent();

                                positionTarget = (int) v.getTag();

                        }

                        if (viewSource != null) {

                            RecyclerView source = (RecyclerView) viewSource.getParent();

                            MyAdapter adapterSource = (MyAdapter) source.getAdapter();

                            int positionSource = (int) viewSource.getTag();



                            Pelaaja list = adapterSource.getList().get(positionSource);
                            ArrayList<Pelaaja> listSource = adapterSource.getList();

                                    listSource.remove(positionSource);

                                    adapterSource.updateList(listSource);
                                    adapterSource.notifyDataSetChanged();

                              MyAdapter adapterTarget = (MyAdapter) target.getAdapter();
                                    ArrayList<Pelaaja> customListTarget = adapterTarget.getList();
                                    Log.i("customlistrtargetr", String.valueOf(customListTarget));


                                    if (positionTarget >= 0) {
                                        customListTarget.add(positionTarget, list);
                                    } else {
                                        customListTarget.add(list);

                                    }
                                    adapterTarget.updateList(customListTarget);
                            testi = customListTarget;
                                    adapterTarget.notifyDataSetChanged();

                        }
                        break;
                }
                break;
        }



        //if(!isDropped &&event.getLocalState() != null){
        //   ((View)event.getLocalState().setVisibility(View.VISIBLE));
        //}

        return true;
    }


}