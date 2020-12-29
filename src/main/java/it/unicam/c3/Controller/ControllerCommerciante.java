package it.unicam.c3.Controller;

import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Commercio.PuntoVendita;
import it.unicam.c3.Consegne.Consegna;
import it.unicam.c3.Consegne.GestoreConsegne;
import it.unicam.c3.Consegne.StatoConsegna;
import it.unicam.c3.Ordini.GestoreOrdini;
import it.unicam.c3.Ordini.Ordine;
import it.unicam.c3.Ordini.StatoOrdine;

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
        this.commerciante.getPuntiVendita().stream()
                .filter(puntoVendita -> puntoVendita.equals(pv))
                .collect(Collectors.toList())
                .get(0)
                .addProdotto(descrizione, prezzo);
    }

    public void addProdotto(int indexPv, String descrizione, double prezzo) {
        this.commerciante.getPuntiVendita().get(indexPv)
                .addProdotto(descrizione, prezzo);
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

    public void accettaOrdine(Ordine ordine) {
        ordine.setStato(StatoOrdine.ACCETTATO);
    }

    public void rifiutaOrdine(Ordine ordine) {
        ordine.setStato(StatoOrdine.RIFIUTATO);
    }

    public List<Consegna> getConsegne() {
        return GestoreConsegne.getInstance().getConsegne(this.commerciante);
    }

    public List<Consegna> getConsegne(StatoConsegna stato) {
        return GestoreConsegne.getInstance().getConsegne(this.commerciante, stato);
    }
}
