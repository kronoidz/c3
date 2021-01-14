package it.unicam.c3.Commercio;

import it.unicam.c3.Anagrafica.Commerciante;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class PuntoVendita {
    private String id;
    private String nome;
    private String posizione;
    private Commerciante commerciante;
    private List<Prodotto> prodotti = new LinkedList<>();
    private List<IOfferta> offerte = new LinkedList<>();

    public PuntoVendita() { }

    public PuntoVendita(Commerciante commerciante, String nome, String posizione,
                        String id)
    {
        this.commerciante = commerciante;
        this.nome = nome;
        this.posizione = posizione;
        this.id = id;
    }

    // Genera l'id
    public PuntoVendita(Commerciante commerciante, String nome, String posizione) {
        this(commerciante, nome, posizione, null);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void removeProdotto(Prodotto prodotto){
        this.prodotti.remove(prodotto);
    }

    public void removeProdotto(int indexProdotto){
        this.removeProdotto(this.prodotti.get(indexProdotto));
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
        return "Nome: ["+this.nome+"] Posizione: ["+this.posizione+"]";
    }
}
