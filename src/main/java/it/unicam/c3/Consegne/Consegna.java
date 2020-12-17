package it.unicam.c3.Consegne;

import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Città.PuntoRitiro;
import it.unicam.c3.Ordini.Ordine;

public class Consegna {
    private StatoConsegna stato;
    private Ordine ordine;
    private Corriere corriere;
    private Commerciante commerciante;
    private PuntoRitiro puntoRitiro;

    public Consegna(Commerciante commerciante, Ordine ordine, PuntoRitiro puntoRitiro){
        this.commerciante=commerciante;
        this.ordine=ordine;
        this.puntoRitiro=puntoRitiro;
        this.stato=StatoConsegna.IN_ATTESA;
    }

    public void setStato(StatoConsegna stato){
        this.stato=stato;
    }

    public StatoConsegna getStato(){
        return this.stato;
    }

    public void setOrdine(Ordine ordine){
        this.ordine=ordine;
    }

    public Ordine getOrdine(){
        return this.ordine;
    }

    public void setCorriere(Corriere corriere){
        this.corriere=corriere;
    }

    public Corriere getCorriere(){
        return this.corriere;
    }

    public Commerciante getCommerciante(){
        return this.commerciante;
    }

    public void setPuntoRitiro(PuntoRitiro puntoRitiro){
        this.puntoRitiro=puntoRitiro;
    }

    public PuntoRitiro getPuntoRitiro(){
        return this.puntoRitiro;
    }

    public String toString(){
        return "Commerciante: ["+commerciante.toString()+"] Ordine: ["+ordine.toString()+"] Stato: ["+stato.toString()+"] Corriere: ["+corriere.toString()+"] Punto Ritiro: ["+ puntoRitiro.getIndirizzo()+"] ";
    }
}
