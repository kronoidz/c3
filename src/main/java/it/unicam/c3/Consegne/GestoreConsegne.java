package it.unicam.c3.Consegne;

import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Citta.PuntoRitiro;
import it.unicam.c3.Ordini.Ordine;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GestoreConsegne implements IGestoreConsegna{
   private static GestoreConsegne instance;
   private List<Consegna> consegne;

    private GestoreConsegne(){
       this.consegne= new LinkedList<>();
   }

   private GestoreConsegne(List<Consegna> consegne){
       this.consegne=consegne;
   }

    public static GestoreConsegne getInstance() {
        if (instance == null) {
            instance = new GestoreConsegne();
        }
        return instance;
    }

    public static GestoreConsegne getInstance(List<Consegna> consegne) {
        if (instance == null) {
            instance = new GestoreConsegne(consegne);
        }
        return instance;
    }

    @Override
    public void addConsegna(Ordine ordine, Commerciante commerciante, PuntoRitiro puntoRitiro) {
        this.consegne.add(new Consegna(commerciante,ordine,puntoRitiro));
    }

    @Override
    public List<Consegna> getConsegnePreseInCaricoDa(Corriere corriere) {
        return this.consegne.stream()
                .filter(c->c.getCorriere()!=null)
                .filter(c->c.getCorriere().equals(corriere))
                .filter(c->c.getStato().equals(StatoConsegna.PRESA_IN_CARICO))
               .collect(Collectors.toList());
    }

    @Override
    public List<Consegna> getConsegne(){
       return this.consegne;
    }

    @Override
    public List<Consegna> getConsegne(StatoConsegna stato) {
        return  this.consegne.stream()
                .filter(c->c.getStato()!=null)
                .filter(c->c.getStato().equals(stato))
                .collect(Collectors.toList());
    }

    @Override
    public List<Consegna> getConsegne(Commerciante commerciante) {
        return this.consegne.stream()
                .filter(consegna -> consegna.getCommerciante().equals(commerciante))
                .collect(Collectors.toList());
    }

    @Override
    public List<Consegna> getConsegne(Commerciante commerciante, StatoConsegna stato) {
        return this.consegne.stream()
                .filter(consegna -> consegna.getCommerciante().equals(commerciante))
                .filter(consegna -> consegna.getStato() == stato)
                .collect(Collectors.toList());
    }

    private void setConsegna(Consegna consegna, Corriere corriere, StatoConsegna stato){
        if(stato.equals(StatoConsegna.EFFETTUATA) || stato.equals(StatoConsegna.PRESA_IN_CARICO)) {
            if(this.consegne.contains(consegna)) {
                consegna.setCorriere(corriere);
                consegna.setStato(stato);
            }else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();
    }

    @Override
    public void prendiInCaricoConsegna(Consegna consegna, Corriere corriere) throws IllegalArgumentException{
           if(corriere!=null){
               setConsegna(consegna,corriere,StatoConsegna.PRESA_IN_CARICO);
           }else throw new IllegalArgumentException();
    }

    @Override
    public void prendiInCaricoConsegna(int index, Corriere corriere) throws IllegalArgumentException{
        prendiInCaricoConsegna(this.consegne.get(index), corriere);
    }

    @Override
    public void consegnaEffettuata(Consegna consegna, Corriere corriere) throws IllegalArgumentException{
        if(consegna.getCorriere().equals(corriere)&&consegna.getStato().equals(StatoConsegna.PRESA_IN_CARICO)){
            setConsegna(consegna,corriere,StatoConsegna.EFFETTUATA);
        }else throw new IllegalArgumentException();
    }

    @Override
    public void consegnaEffettuata(int index, Corriere corriere) throws IllegalArgumentException{
       consegnaEffettuata(this.consegne.get(index), corriere);
    }

    @Override
    public void setStato(Consegna consegna, StatoConsegna stato) throws IllegalArgumentException{
        if(consegne.contains(consegna)) {
            consegna.setStato(stato);
        }else throw new IllegalArgumentException("Error setStato consegna: consegna non trovata!");
    }

    @Override
    public void setStato(int index, StatoConsegna stato) throws IllegalArgumentException{
        setStato(consegne.get(index), stato);
    }
}
