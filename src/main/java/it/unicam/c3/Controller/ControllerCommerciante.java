package it.unicam.c3.Controller;

import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Citta.PuntoRitiro;
import it.unicam.c3.Commercio.IOfferta;
import it.unicam.c3.Commercio.Prodotto;
import it.unicam.c3.Commercio.PuntoVendita;
import it.unicam.c3.Consegne.Consegna;
import it.unicam.c3.Consegne.GestoreConsegne;
import it.unicam.c3.Consegne.StatoConsegna;
import it.unicam.c3.Ordini.GestoreOrdini;
import it.unicam.c3.Ordini.Ordine;
import it.unicam.c3.Ordini.StatoOrdine;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerCommerciante {
    private Commerciante commerciante;

    public ControllerCommerciante(Commerciante c) {
        this.commerciante = c;
    }

    public void addPuntoVendita(String posizione, String nome) {
        this.commerciante.addPuntoVendita(posizione, nome);
    }

    public void removePuntoVendita(int i) {
        this.commerciante.getPuntiVendita().remove(i);
    }

    public void removePuntoVendita(PuntoVendita pv) {
        this.commerciante.getPuntiVendita().remove(pv);
    }

    public void addProdotto(PuntoVendita pv, String descrizione, double prezzo) {
        if(this.commerciante.getPuntiVendita().contains(pv)){
            pv.addProdotto(descrizione,prezzo);
        } else throw new IllegalArgumentException();
    }

    public void addProdotto(int indexPv, String descrizione, double prezzo) {
        this.commerciante.getPuntiVendita().get(indexPv)
                .addProdotto(descrizione, prezzo);
    }

    public void removeProdotto(PuntoVendita pv, Prodotto prodotto){
        if(this.commerciante.getPuntiVendita().contains(pv)){
            pv.removeProdotto(prodotto);
        } else throw new IllegalArgumentException();
    }

    public void removeProdotto(int indexPv, Prodotto prodotto){
       this.removeProdotto(this.commerciante.getPuntiVendita().get(indexPv), prodotto);
    }

    public void removeProdotto(PuntoVendita pv, int indexProdotto){
        this.removeProdotto(pv , pv.getProdotti().get(indexProdotto));
    }

    public void removeProdotto(int indexPv, int indexProdotto){
        this.removeProdotto(this.commerciante.getPuntiVendita().get(indexPv) , this.commerciante.getPuntiVendita().get(indexPv).getProdotti().get(indexProdotto));
    }


    public List<PuntoVendita> getPuntiVendita() {
        return this.commerciante.getPuntiVendita();
    }

    public List<Ordine> getOrdini() {
        return GestoreOrdini.getInstance().getOrdini(this.commerciante);
    }

    public List<Ordine> getOrdini(StatoOrdine stato) {
        return GestoreOrdini.getInstance().getOrdini(this.commerciante, stato);
    }

    public void accettaOrdine(Ordine ordine, PuntoRitiro pv) {
        GestoreConsegne.getInstance().addConsegna(ordine,this.commerciante,pv);
        GestoreOrdini.getInstance().setStato(ordine, StatoOrdine.ACCETTATO);
    }

    public void rifiutaOrdine(Ordine ordine) {
        GestoreOrdini.getInstance().setStato(ordine, StatoOrdine.RIFIUTATO);
    }

    public List<Consegna> getConsegne() {
        return GestoreConsegne.getInstance().getConsegne(this.commerciante);
    }

    public List<Consegna> getConsegne(StatoConsegna stato) {
        return GestoreConsegne.getInstance().getConsegne(this.commerciante, stato);
    }

    public void addOfferta(PuntoVendita pv, String descrizione, String importo){
        if (this.commerciante.getPuntiVendita().contains(pv)){
            pv.addOfferta(descrizione,importo);
        } else throw new IllegalArgumentException();
    }

    public void addOfferta(PuntoVendita pv, String descrizione, String importo, LocalDate date){
        if (this.commerciante.getPuntiVendita().contains(pv)){
            pv.addOfferta(descrizione,importo, date);
        } else throw new IllegalArgumentException();
    }

    public void removeOfferta(PuntoVendita pv, IOfferta offerta){
        if(this.commerciante.getPuntiVendita().contains(pv) && pv.getOfferte().contains(offerta)){
            pv.getOfferte().remove(offerta);
        } else throw new IllegalArgumentException();
    }

    public void removeOfferta(int indexPv, IOfferta offerta){
        this.removeOfferta(this.commerciante.getPuntiVendita().get(indexPv), offerta);
    }

    public void removeOfferta(PuntoVendita pv, int indexOfferta){
        this.removeOfferta(pv, pv.getOfferte().get(indexOfferta));
    }

    public void removeOfferta(int indexPv, int indexOfferta){
        this.removeOfferta(this.commerciante.getPuntiVendita().get(indexPv), this.commerciante.getPuntiVendita().get(indexPv).getOfferte().get(indexOfferta));
    }

    public List<PuntoRitiro> getPuntiRitiroDisponibili(){
        return CentroCittadino.getInstance().getPuntiRitiro().stream()
                .filter(pr->pr.getSlotDisponibili()>0)
                .collect(Collectors.toList());
    }

}
