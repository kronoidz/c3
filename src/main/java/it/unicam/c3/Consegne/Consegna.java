package it.unicam.c3.Consegne;

import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Citta.PuntoRitiro;
import it.unicam.c3.Ordini.Ordine;

import java.util.UUID;

public class Consegna {
    public String id;
    private StatoConsegna stato = StatoConsegna.IN_ATTESA;
    private Ordine ordine;
    private Corriere corriere;
    private Commerciante commerciante;
    private PuntoRitiro puntoRitiro;
    private boolean ritirabile = false;

    public Consegna() { }

    // Se id == null, genera l'id
    public Consegna(Commerciante commerciante, Ordine ordine, PuntoRitiro puntoRitiro,
                    String id)
    {
        this.commerciante = commerciante;
        this.ordine = ordine;
        this.puntoRitiro = puntoRitiro;

        this.id = id;
    }

    public Consegna(Commerciante commerciante, Ordine ordine, PuntoRitiro puntoRitiro) {
        this(commerciante, ordine, puntoRitiro, null);
        this.id = UUID.randomUUID().toString();
    }

    public String getId(){
        return this.id.toString();
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isRitirabile(){
        return ritirabile;
    }

    public boolean setRitirabile(boolean bool){
        return ritirabile=bool;
    }

    public String toString(){
        if(corriere != null) {
            return "Commerciante: [" + commerciante.toString() + "] Ordine: [" + ordine.toString() + "] Stato: [" + stato.toString()+"] Corriere: ["+this.corriere.toString()+"] Punto Ritiro: [" + puntoRitiro.getIndirizzo() + "] ";
        } else return "Commerciante: [" + commerciante.toString() + "] Ordine: [" + ordine.toString() + "] Stato: [" + stato.toString() +"] Corriere: [NULL] Punto Ritiro: [" + puntoRitiro.getIndirizzo() + "] ";
    }
}
