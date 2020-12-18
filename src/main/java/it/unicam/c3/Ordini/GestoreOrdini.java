package it.unicam.c3.Ordini;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Città.CentroCittadino;
import it.unicam.c3.Commercio.Prodotto;
import it.unicam.c3.Commercio.PuntoVendita;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GestoreOrdini {
    private static GestoreOrdini instance;
    private List<Ordine> ordini;

    private GestoreOrdini() {
        ordini = new LinkedList<>();
    }

    public static GestoreOrdini getInstance() {
        if (instance == null) {
            instance = new GestoreOrdini();
        }

        return instance;
    }

    public void addOrdine(Cliente cliente, PuntoVendita pv, List<Prodotto> prodotti) {
        Ordine ordine = new Ordine();

        ordine.setCliente(cliente);
        ordine.setStato(StatoOrdine.IN_ATTESA);
        ordine.setPuntoVendita(pv);
        prodotti.forEach(ordine::addProdotto);

        ordini.add(ordine);
    }

    public List<Ordine> getOrdini(Cliente cliente) {
        return ordini.stream()
                .filter(ordine -> ordine.getCliente() == cliente)
                .collect(Collectors.toList());
    }

    public List<Ordine> getOrdini(Cliente cliente, StatoOrdine stato) {
        return ordini.stream()
                .filter(ordine -> ordine.getCliente() == cliente &&
                                  ordine.getStato() == stato)
                .collect(Collectors.toList());
    }

    public List<Ordine> getOrdini(Commerciante commerciante) {
        return ordini.stream()
                .filter(ordine ->
                        ordine.getPuntoVendita().getCommerciante() == commerciante)
                .collect(Collectors.toList());
    }

    public List<Ordine> getOrdini(Commerciante commerciante, StatoOrdine stato) {
        return ordini.stream()
                .filter(ordine ->
                        ordine.getPuntoVendita().getCommerciante() == commerciante &&
                        ordine.getStato() == stato)
                .collect(Collectors.toList());
    }

    public void setStato(int index, StatoOrdine stato) {
        ordini.get(index).setStato(stato);
    }

    public void setStato(Ordine ordine, StatoOrdine stato) {
        for (Ordine o : ordini) {
            if (o == ordine) {
                ordine.setStato(stato);
            }
        }

        throw new RuntimeException("setStato(Ordine, StatoOrdine): Ordine non trovato");
    }
}
