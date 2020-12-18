package it.unicam.c3.Consegne;

import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Citta.PuntoRitiro;
import it.unicam.c3.Ordini.Ordine;

import java.util.List;

public interface IGestoreConsegna {

    void addConsegna(Ordine ordine, Commerciante commerciante, PuntoRitiro puntoRitiro);

    List<Consegna> getConsegnePreseInCaricoDa(Corriere corriere);

    List<Consegna> getConsegneInAttesa();

    void setStato(Consegna consegna, Corriere corriere, StatoConsegna stato) throws IllegalArgumentException;

    void setStato(Consegna consegna, StatoConsegna stato);

    void setStato(int index, Corriere corriere, StatoConsegna stato) throws IllegalArgumentException;

    void setStato(int index, StatoConsegna stato);

}
