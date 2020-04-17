package com.example.pelaajaapp;

public class Pelaaja {

    private String nimi;
    private int mmr = 0;
    private boolean active;

    public Pelaaja(){}
    public Pelaaja(String nimi, int mmr, boolean active){
       this.nimi = nimi;
       this.mmr = mmr;
       this.active = active;

    }

    public void setNimi(String name) {
        nimi = name;
    }

    public void setMmr(int rating) {
        mmr = rating;
    }

    public String getNimi() {
        return nimi;
    }

    public int getMmr() {
        return mmr;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}