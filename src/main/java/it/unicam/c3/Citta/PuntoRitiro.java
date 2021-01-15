package it.unicam.c3.Citta;

import java.util.UUID;

public class PuntoRitiro {
    private String id;
    private String indirizzo;
    private int capienza;
    private int occupati;

    public PuntoRitiro() { }

    public PuntoRitiro(String indirizzo, int capienza, String id) {
        this.indirizzo = indirizzo;
        this.capienza = capienza;
        if(id!=null) {
            this.id = id;
        } else this.id= UUID.randomUUID().toString();
    }

    public PuntoRitiro(String indirizzo, int capienza){
        this(indirizzo, capienza, null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PuntoRitiro(String indirizzo){
        this.indirizzo=indirizzo;
    }

    public String getIndirizzo(){
        return this.indirizzo;
    }

    public void setIndirizzo(String indirizzo){
        this.indirizzo=indirizzo;
    }

    public int getSlotDisponibili(){
        return this.capienza-this.occupati;
    }

    public int getCapienza(){
        return this.capienza;
    }

    public int getSlotOccupati(){ return this.occupati;}

    public void incrementOccupati(int amount){
        this.occupati=this.occupati+amount;
    }

    public void decrementOccupati(int amount){
        this.occupati=this.occupati-amount;
    }

    public String toString(){
        return this.indirizzo+" Capienza: ["+this.capienza+"] Slot Disponibili:["+getSlotDisponibili()+"]";
    }
}
