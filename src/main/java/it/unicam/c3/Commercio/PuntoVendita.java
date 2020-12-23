package it.unicam.c3.Commercio;

import it.unicam.c3.Anagrafica.Commerciante;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class PuntoVendita {
    String nome;
    String posizione;
    Commerciante commerciante;
    List<Prodotto> prodotti;
    List<IOfferta> offerte;


    public PuntoVendita(Commerciante commerciante) {
        this.commerciante=commerciante;
        prodotti = new LinkedList<>();
    }

    public PuntoVendita(Commerciante commerciante, String nome, String posizione) {
       this.commerciante=commerciante;
        this.nome = nome;
        this.posizione = posizione;
        this.prodotti = new LinkedList<>();
    }

    public String getPosizione() {
        return posizione;
    }

    public void setPosizione(String posizione) {
        this.posizione = posizione;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Commerciante getCommerciante() {
        return this.commerciante;
    }

    public List<Prodotto> getProdotti() {
        return this.prodotti;
    }

    public void addProdotto(String descrizione, double prezzo) {
        this.prodotti.add(new Prodotto(descrizione, prezzo));
    }

    public List<IOfferta> getOfferte() {
        return this.offerte;
    }

    public void addOfferta(String descrizione, String importo) {
        this.offerte.add(new OffertaSemplice(descrizione, importo));
    }

    public void addOfferta(String descrizione, String importo, LocalDate scadenza) {
        this.offerte.add(new OffertaATempo(descrizione, importo, scadenza));
    }

    public String toString(){
        return this.nome+" Posizione: ["+this.posizione+"]";
    }
}
