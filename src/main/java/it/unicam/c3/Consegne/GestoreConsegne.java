package it.unicam.c3.Consegne;

import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Città.PuntoRitiro;
import it.unicam.c3.Commercio.PuntoVendita;
import it.unicam.c3.Ordini.Ordine;

import java.util.LinkedList;
import java.util.List;

public class GestoreConsegne implements IGestoreConsegna{
   private List<Consegna> consegne;

   public GestoreConsegne(){
       this.consegne= new LinkedList<>();
   }

   public GestoreConsegne(List<Consegna> consegne){
       this.consegne=consegne;
   }

    @Override
    public void addConsegna(Ordine ordine, Commerciante commerciante, PuntoRitiro puntoRitiro) {
        this.consegne.add(new Consegna(commerciante,ordine,puntoRitiro));
    }

    @Override
    public List<Consegna> getConsegnePreseInCaricoDa(Corriere corriere) {
        List<Consegna> consegneCorriere = new LinkedList<>();
        this.consegne.stream()
                .filter(c->c.getCorriere().equals(corriere))
                .filter(c->c.getStato().equals(StatoConsegna.PRESA_IN_CARICO))
                .forEach(consegneCorriere::add);
        return consegneCorriere;
    }

    @Override
    public List<Consegna> getConsegneInAttesa() {
        List<Consegna> consegneInAttesa = new LinkedList<>();
        this.consegne.stream()
                .filter(c->c.getStato().equals(StatoConsegna.IN_ATTESA))
                .forEach(consegneInAttesa::add);
        return consegneInAttesa;
    }

    @Override
    public void setStato(Consegna consegna, Corriere corriere, StatoConsegna stato) throws IllegalArgumentException{
        if(stato == StatoConsegna.EFFETTUATA || stato==StatoConsegna.PRESA_IN_CARICO) {
            consegna.setCorriere(corriere);
            consegna.setStato(stato);
        } else throw new IllegalArgumentException();

    }

    @Override
    public void setStato(Consegna consegna, StatoConsegna stato) {
        consegna.setStato(stato);
    }


    @Override
    public void setStato(int index, Corriere corriere, StatoConsegna stato) throws IllegalArgumentException{
       setStato(consegne.get(index), corriere, stato);
    }

    @Override
    public void setStato(int index, StatoConsegna stato) {
        setStato(consegne.get(index), stato);
    }
}
