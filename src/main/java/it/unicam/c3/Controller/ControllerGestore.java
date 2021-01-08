package it.unicam.c3.Controller;

import it.unicam.c3.Anagrafica.Amministrazione;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Citta.PuntoRitiro;

import java.util.List;

public class ControllerGestore {
    private boolean autorizzato = false;

    public boolean autorizza(String password) {
        this.autorizzato = Amministrazione.checkPassword(password);
        return this.autorizzato;
    }

    public boolean isAutorizzato() {
        return autorizzato;
    }

    public List<PuntoRitiro> getPuntiRitiro() {
        return CentroCittadino.getInstance().getPuntiRitiro();
    }

    public void addPuntoRitiro(String indirizzo, int capienza) {
        CentroCittadino.getInstance().addPuntoRitiro(indirizzo, capienza);
    }

    public void removePuntoRitiro(PuntoRitiro pr){
        CentroCittadino.getInstance().getPuntiRitiro().remove(pr);
    }

    public void removePuntoRitiro(int indexPr){
        this.removePuntoRitiro(CentroCittadino.getInstance().getPuntiRitiro().get(indexPr));
    }


}
