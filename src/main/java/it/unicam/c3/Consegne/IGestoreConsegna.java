package it.unicam.c3.Consegne;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Citta.PuntoRitiro;
import it.unicam.c3.Ordini.Ordine;

import java.util.List;

public interface IGestoreConsegna {
    /**
     * adds a new delivery
     * @param ordine
     * @param commerciante
     * @param puntoRitiro
     */
    void addConsegna(Ordine ordine, Commerciante commerciante, PuntoRitiro puntoRitiro);

    /**
     *
     * @param corriere
     * @return delivery by courier
     */
    List<Consegna> getConsegnePreseInCaricoDa(Corriere corriere);

    /**
     *
     * @return all delivery
     */
    List<Consegna> getConsegne();

    /**
     *
     * @param stato
     * @return delivery with status parameter
     */
    List<Consegna> getConsegne(StatoConsegna stato);

    /**
     *
     * @param commerciante
     * @return
     */
    List<Consegna> getConsegne(Commerciante commerciante);

    /**
     *
     * @param stato
     * @return
     */
    List<Consegna> getConsegne(Commerciante commerciante, StatoConsegna stato);

    /**
     * used when a courier takes over a delivery
     * @param consegna
     * @param corriere
     * @throws IllegalArgumentException
     */
    void prendiInCaricoConsegna(Consegna consegna, Corriere corriere) throws Exception;

    /**
     * used when a courier takes over a delivery
     * @param index
     * @param corriere
     * @throws IllegalArgumentException
     */
    void prendiInCaricoConsegna(int index, Corriere corriere) throws Exception;

    /**
     * used when a courier makes a delivery
     * @param consegna
     * @param corriere
     * @throws IllegalArgumentException
     */
    void consegnaEffettuata(Consegna consegna, Corriere corriere) throws Exception;

    /**
     * used when a courier makes a delivery
     * @param index
     * @param corriere
     * @throws IllegalArgumentException
     */
    void consegnaEffettuata(int index, Corriere corriere) throws Exception;

    /**
     * used when a customer picks up a delivery
     * @param consegna
     * @param cliente
     * @throws IllegalArgumentException
     */
    void consegnaRitirata(Consegna consegna, Cliente cliente) throws IllegalArgumentException;

    /**
     * used when a customer picks up a delivery
     * @param index
     * @param cliente
     * @throws IllegalArgumentException
     */
    void consegnaRitirata(int index, Cliente cliente) throws IllegalArgumentException;


    /**
     * set delivery state
     * @param consegna
     * @param stato
     * @throws IllegalArgumentException
     */
    void setStato(Consegna consegna, StatoConsegna stato) throws IllegalArgumentException ;

    /**
     * set delivery state
     * @param index
     * @param stato
     * @throws IllegalArgumentException
     */
    void setStato(int index, StatoConsegna stato) throws IllegalArgumentException;

}
