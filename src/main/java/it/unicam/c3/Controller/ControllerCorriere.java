package it.unicam.c3.Controller;

import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Consegne.Consegna;
import it.unicam.c3.Consegne.GestoreConsegne;
import it.unicam.c3.Consegne.StatoConsegna;

import java.util.List;

public class ControllerCorriere {
    private Corriere corriere;

    public ControllerCorriere(Corriere corriere){
        this.corriere=corriere;
    }

    /**
     *
     * @return lista delle consegne pronte per essere consegnate
     */
    public List<Consegna> getConsegneInAttesa(){
        return GestoreConsegne.getInstance().getConsegne(StatoConsegna.IN_ATTESA);
    }

    /**
     *
     * @return lista delle consegne prese in carico
     */
    public List<Consegna> getConsegneInCarico(){
        return GestoreConsegne.getInstance().getConsegnePreseInCaricoDa(this.corriere);
    }

    /**
     * Utilizzato per prendere in carico una consegna
     * @param consegna
     */
    public void prendiInCarico(Consegna consegna){
        GestoreConsegne.getInstance().prendiInCaricoConsegna(consegna,this.corriere);
    }

    /**
     * Utilizzato per prendere in carico una consegna
     * @param indexConsegna
     */
    public void prendiInCarico(int indexConsegna){
        GestoreConsegne.getInstance().prendiInCaricoConsegna(indexConsegna,this.corriere);
    }

    /**
     * Utilizzato quando la consegna è stata portata a termine
     * @param consegna
     */
    public void effettuaConsegna(Consegna consegna){
        GestoreConsegne.getInstance().consegnaEffettuata(consegna,this.corriere);
    }

    /**
     * Utilizzato quando la consegna è stata portata a termine
     * @param indexConsegna
     */
    public void effettuaConsegna(int indexConsegna){
        GestoreConsegne.getInstance().consegnaEffettuata(indexConsegna,this.corriere);
    }
}
