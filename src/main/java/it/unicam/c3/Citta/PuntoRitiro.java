package it.unicam.c3.Citta;

import java.util.UUID;

public class PuntoRitiro {
    private String id;
    private String indirizzo;
    private String id = UUID.randomUUID().toString();
    private int capienza;
    private int occupati;

    public PuntoRitiro() { }

    public PuntoRitiro(String indirizzo, int capienza, String id) {
        this.indirizzo = indirizzo;
        this.capienza = capienza;
        this.id = id;
    }

    public PuntoRitiro(String indirizzo, int capienza){
        this(indirizzo, capienza, null);
        this.id = UUID.randomUUID().toString();
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

    public void setId(String id){
        this.id=id;
    }

    public String getId() {
        return id.toString();
    }

    public String toString(){
        return this.indirizzo+" Capienza: ["+this.capienza+"] Slot Disponibili:["+getSlotDisponibili()+"]";
    }
}
