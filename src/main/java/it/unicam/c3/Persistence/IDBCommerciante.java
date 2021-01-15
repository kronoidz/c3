package it.unicam.c3.Persistence;

import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Commercio.IOfferta;
import it.unicam.c3.Commercio.Prodotto;
import it.unicam.c3.Commercio.PuntoVendita;
import it.unicam.c3.Consegne.Consegna;
import it.unicam.c3.Ordini.Ordine;

import java.sql.SQLException;

public interface IDBCommerciante{

    void savePuntoVendita(PuntoVendita pv) throws Exception;

    void saveProdotto(PuntoVendita pv, Prodotto prodotto) throws Exception;

    void saveOfferta(PuntoVendita pv, IOfferta offerta) throws Exception;

    void removePuntoVendita(PuntoVendita pv) throws Exception;

    void removeProdotto(Prodotto prodotto) throws  Exception;

    void removeOfferta(IOfferta offerta) throws Exception;

}
