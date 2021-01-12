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
        if (!autorizzato) throw new RuntimeException("Utente non autorizzato");
        return CentroCittadino.getInstance().getPuntiRitiro();
    }

    public void addPuntoRitiro(String indirizzo, int capienza) {
        if (!autorizzato) throw new RuntimeException("Utente non autorizzato");
        CentroCittadino.getInstance().addPuntoRitiro(indirizzo, capienza);
    }

    public void removePuntoRitiro(PuntoRitiro pr){
        if (!autorizzato) throw new RuntimeException("Utente non autorizzato");
        CentroCittadino.getInstance().getPuntiRitiro().remove(pr);
    }

    public void removePuntoRitiro(int indexPr){
        if (!autorizzato) throw new RuntimeException("Utente non autorizzato");
        this.removePuntoRitiro(CentroCittadino.getInstance().getPuntiRitiro().get(indexPr));
    }


}
