package it.unicam.c3.Commercio;

public class Prodotto {
    private String descrizione;
    private double prezzo;

    public Prodotto(){}

    public Prodotto(String descrizione, double prezzo){
        this.descrizione=descrizione;
        this.prezzo=prezzo;
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
}
