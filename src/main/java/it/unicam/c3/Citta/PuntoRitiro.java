package it.unicam.c3.Citta;

public class PuntoRitiro {
    private String indirizzo;
    private int capienza;
    private int occupati;

    public PuntoRitiro(String indirizzo, int capienza){
        this.indirizzo=indirizzo;
        this.capienza=capienza;
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
