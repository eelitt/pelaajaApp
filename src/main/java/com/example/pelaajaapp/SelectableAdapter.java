package com.example.pelaajaapp;

import android.util.Log;
import android.util.SparseBooleanArray;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class SelectableAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private SparseBooleanArray selectedPlayers;

    public SelectableAdapter(){
        selectedPlayers = new SparseBooleanArray();
    }

    public boolean isSelected(int position){


        return getSelectedPlayers().contains(position);
    }

    public void toggleSelection(int position){
        if(selectedPlayers.get(position, false)){
            selectedPlayers.delete(position);
        }else{

            selectedPlayers.put(position, true);
        }
        notifyItemChanged(position);
    }

    public void clearSelection() {
        List<Integer> selection = getSelectedPlayers();
        selectedPlayers.clear();
        for(Integer i : selection){
            notifyItemChanged(i);
        }
    }
    public int getSelectedItemCount() {
        return selectedPlayers.size();
    }

    public List<Integer> getSelectedPlayers(){
      List<Integer> pelaajat = new ArrayList<>(selectedPlayers.size());
        Pelaaja apuPelaaja;
      for(int i = 0; i < selectedPlayers.size(); ++i) {
          pelaajat.add(selectedPlayers.keyAt(i));
      }
      return pelaajat;
    }
}
