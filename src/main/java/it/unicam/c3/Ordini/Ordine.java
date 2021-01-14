package it.unicam.c3.Ordini;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Commercio.Prodotto;
import it.unicam.c3.Commercio.PuntoVendita;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Ordine {
    private String id;
    private StatoOrdine stato = StatoOrdine.IN_ATTESA;
    private Cliente clienteOrdinante;
    private PuntoVendita pv;
    private List<Prodotto> prodotti = new LinkedList<>();

    public Ordine() { }

    public Ordine(Cliente clienteOrdinante, List<Prodotto> prodotti, String id) {
        this.clienteOrdinante = clienteOrdinante;
        this.prodotti.addAll(prodotti);

        if (id == null) this.id = UUID.randomUUID().toString();
        else this.id = id;
    }

    public Ordine(Cliente clienteOrdinante, List<Prodotto> prodotti) {
        this(clienteOrdinante, prodotti, null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getPrice() {
        double price = 0;
        for (Prodotto p : prodotti) {
            price += p.getPrezzo();
        }
        return price;
    }

    public String toString(){
       if(clienteOrdinante!=null && pv!=null) {
           return "Cliente: ["+this.clienteOrdinante.toString()+"] PuntoVendita: ["+this.pv.toString()+"] Stato: ["+getStato().toString()+"] N. Prodotti: ["+getProdotti().size()+"]";
       }
       else return "Cliente: [NULL] PuntoVendita: [NULL] Stato: ["+getStato().toString()+"] N. Prodotti: ["+getProdotti().size()+"]";
    }
}
