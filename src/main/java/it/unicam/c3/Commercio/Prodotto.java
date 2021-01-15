package it.unicam.c3.Commercio;

import java.util.UUID;

public class Prodotto {
    private String id;
    private String descrizione;
    private double prezzo;
    private boolean disponibilita;

    public Prodotto() { }

    public Prodotto(String descrizione, double prezzo, String id) {
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.disponibilita = true;
        if(id!=null){
            this.id = id;
        }else this.id= UUID.randomUUID().toString();
    }

    public Prodotto(String descrizione, double prezzo) {
        this(descrizione, prezzo, null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public void setDisponibilita(boolean disponibilita) {this.disponibilita=disponibilita; }

    public boolean getDisponibilita() {
        return this.disponibilita;
    }

    public void setPrezzo(boolean disponibilita) {
        this.disponibilita = disponibilita;
    }

    public String toString(){
        return "Prodotto: ["+this.descrizione+"] Prezzo: ["+this.prezzo+"]";
    }
}
