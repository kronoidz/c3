package it.unicam.c3.Ordini;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Commercio.Prodotto;
import it.unicam.c3.Commercio.PuntoVendita;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GestoreOrdini implements IGestoreOrdini{
    private static GestoreOrdini instance;
    private List<Ordine> ordini;

    private GestoreOrdini() {
        ordini = new LinkedList<>();
    }

    private GestoreOrdini(List<Ordine> ordini) {
        this.ordini = ordini;
    }

    public static GestoreOrdini getInstance() {
        if (instance == null) {
            instance = new GestoreOrdini();
        }
        return instance;
    }

    public static GestoreOrdini getInstance(List<Ordine> ordini) {
        if (instance == null) {
            instance = new GestoreOrdini(ordini);
        }
        return instance;
    }

    @Override
    public void addOrdine(Cliente cliente, PuntoVendita pv, List<Prodotto> prodotti) {
        Ordine ordine = new Ordine(cliente, pv, prodotti);
        ordini.add(ordine);
    }

    @Override
    public List<Ordine> getOrdini(Cliente cliente) {
        return ordini.stream()
                .filter(ordine->ordine.getCliente()!=null)
                .filter(ordine -> ordine.getCliente().equals(cliente))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ordine> getOrdini(Cliente cliente, StatoOrdine stato) {
        return ordini.stream()
                .filter(ordine->ordine.getCliente()!=null)
                .filter(ordine -> ordine.getCliente().equals(cliente) &&
                                  ordine.getStato().equals(stato))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ordine> getOrdini(Commerciante commerciante) {
        return ordini.stream()
                .filter(ordine->ordine.getPuntoVendita()!=null)
                .filter(ordine->ordine.getPuntoVendita().getCommerciante()!=null)
                .filter(ordine ->
                        ordine.getPuntoVendita().getCommerciante().equals(commerciante))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ordine> getOrdini(Commerciante commerciante, StatoOrdine stato) {
        return ordini.stream()
                .filter(ordine->ordine.getPuntoVendita()!=null)
                .filter(ordine->ordine.getPuntoVendita().getCommerciante()!=null)
                .filter(ordine ->
                        ordine.getPuntoVendita().getCommerciante().equals(commerciante) &&
                        ordine.getStato().equals(stato))
                .collect(Collectors.toList());
    }

    @Override
    public void setStato(int index, StatoOrdine stato) throws IllegalArgumentException{
        this.setStato(ordini.get(index),stato);
    }

    @Override
    public void setStato(Ordine ordine, StatoOrdine stato) throws IllegalArgumentException{
        if(ordini.contains(ordine)){
                ordine.setStato(stato);
            }else throw new IllegalArgumentException("Error setStato Ordine: Ordine non trovato");
        }
}
