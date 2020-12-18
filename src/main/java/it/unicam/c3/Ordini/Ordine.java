package it.unicam.c3.Ordini;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Commercio.Prodotto;
import it.unicam.c3.Commercio.PuntoVendita;

import java.util.LinkedList;
import java.util.List;

public class Ordine {
    private StatoOrdine stato;
    private Cliente clienteOrdinante;
    private PuntoVendita pv;
    private List<Prodotto> prodotti;

    public Ordine() {
        prodotti = new LinkedList<>();
    }

    public StatoOrdine getStato() {
        return stato;
    }

    public void setStato(StatoOrdine stato) {
        this.stato = stato;
    }

    public Cliente getCliente() {
        return clienteOrdinante;
    }

    public void setCliente(Cliente cliente) {
        clienteOrdinante = cliente;
    }

    public PuntoVendita getPuntoVendita() {
        return pv;
    }

    public void setPuntoVendita(PuntoVendita pv) {
        this.pv = pv;
    }

    public void addProdotto(Prodotto prodotto) {
        prodotti.add(prodotto);
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void removeProdotto(int index) {
        prodotti.remove(index);
    }
}
