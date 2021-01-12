package it.unicam.c3.Commercio;

public class Prodotto {
    private String descrizione;
    private double prezzo;
    private boolean disponibilita;

    public Prodotto() {this.disponibilita=true; }

    public Prodotto(String descrizione, double prezzo) {
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.disponibilita=true;
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
