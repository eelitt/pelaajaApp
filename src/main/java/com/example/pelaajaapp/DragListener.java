package com.example.pelaajaapp;

import android.util.Log;
import android.view.DragEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DragListener implements View.OnDragListener {
    private Boolean isDropped = false;


    @Override
    public boolean onDrag(View v, DragEvent event) {

        switch (event.getAction()) {
            case DragEvent.ACTION_DROP:
                isDropped = true;
                int positionTarget = -1;

                View viewSource = (View) event.getLocalState();
                int viewId = v.getId();
                final int layout = R.layout.activity_player;
                final int listaKaikistaPelaajista = R.id.listaLaatikko;
                final int listaVuoronPelaajista = R.id.iltalistalaatikko;

                switch (viewId) {
                    case layout:
                    case listaKaikistaPelaajista:
                    case listaVuoronPelaajista:
                        RecyclerView target;
                        switch (viewId) {
                            case listaKaikistaPelaajista:
                                target = v.getRootView().findViewById(listaKaikistaPelaajista);
                                break;
                            case listaVuoronPelaajista:
                                target = v.getRootView().findViewById(listaVuoronPelaajista);
                                break;
                            default:
                                target = (RecyclerView) v.getParent();
                                positionTarget = (int) v.getTag();
                        }
                        if (viewSource != null) {
                            RecyclerView source = (RecyclerView) viewSource.getParent();

                            MyAdapter adapterSource = (MyAdapter) source.getAdapter();
                            int positionSource = (int) viewSource.getTag();
                            int sourceId = source.getId();


                            Pelaaja list = adapterSource.getList().get(positionSource);

                            ArrayList<Pelaaja> listSource = adapterSource.getList();

                            for(int i = 0; i < listSource.size(); i++)
                            {
                                Log.i("testaus", String.valueOf(listSource.get(i)));
                            }

                            listSource.remove(positionSource);

                            adapterSource.updateList(listSource);

                            adapterSource.notifyDataSetChanged();


                            MyAdapter adapterTarget = (MyAdapter) target.getAdapter();
                            ArrayList<Pelaaja> customListTarget = adapterTarget.getList();
                            if (positionTarget >= 0) {
                                customListTarget.add(positionTarget, list);
                            } else {
                                customListTarget.add(list);
                            }
                            adapterTarget.updateList(customListTarget);
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