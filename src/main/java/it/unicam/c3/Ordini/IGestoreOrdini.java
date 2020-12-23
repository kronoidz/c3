package it.unicam.c3.Ordini;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Commercio.Prodotto;
import it.unicam.c3.Commercio.PuntoVendita;

import java.util.List;

public interface IGestoreOrdini {
    /**
     * adds a new order
     * @param cliente
     * @param pv
     * @param prodotti
     */
    void addOrdine(Cliente cliente, PuntoVendita pv, List<Prodotto> prodotti);

    /**
     *
     * @param cliente
     * @return the order list
     */
    List<Ordine> getOrdini(Cliente cliente);

    /**
     *
     * @param cliente
     * @param stato
     * @return the list of customer orders with status parameter
     */
    List<Ordine> getOrdini(Cliente cliente, StatoOrdine stato);

    /**
     *
     * @param commerciante
     * @return the customer order list for the merchant parameter
     */
    List<Ordine> getOrdini(Commerciante commerciante);

    /**
     *
     * @param commerciante
     * @param stato
     * @return the customer order list for the merchant parameter with status parameter
     */
    List<Ordine> getOrdini(Commerciante commerciante, StatoOrdine stato);

    /**
     * set order state in index position
     * @param index
     * @param stato
     * @throws IllegalArgumentException
     */
    void setStato(int index, StatoOrdine stato) throws IllegalArgumentException;

    /**
     * set order state
     * @param ordine
     * @param stato
     * @throws IllegalArgumentException
     */
    void setStato(Ordine ordine, StatoOrdine stato) throws IllegalArgumentException;

}
