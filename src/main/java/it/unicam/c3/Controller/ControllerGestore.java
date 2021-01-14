package it.unicam.c3.Controller;

import it.unicam.c3.Anagrafica.Amministrazione;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Citta.PuntoRitiro;
import it.unicam.c3.Persistence.DBPuntiRitiro;
import it.unicam.c3.Persistence.IDBPuntiRitiro;

import java.sql.SQLException;
import java.util.List;

public class ControllerGestore {
    private boolean autorizzato = false;
    private IDBPuntiRitiro dbPuntiRitiro;

    public ControllerGestore(IDBPuntiRitiro dbPuntiRitiro){
        this.dbPuntiRitiro=dbPuntiRitiro;
    }

    public ControllerGestore() throws SQLException {
        this.dbPuntiRitiro=new DBPuntiRitiro();
    }

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

    public void addPuntoRitiro(String indirizzo, int capienza) throws SQLException {
        if (!autorizzato) throw new RuntimeException("Utente non autorizzato");
        CentroCittadino.getInstance().addPuntoRitiro(indirizzo, capienza);
        dbPuntiRitiro.savePuntoRitiro(CentroCittadino.getInstance().getPuntiRitiro().get(CentroCittadino.getInstance().getPuntiRitiro().size()-1));
    }

    public void removePuntoRitiro(PuntoRitiro pr){
        if (!autorizzato) throw new RuntimeException("Utente non autorizzato");
        CentroCittadino.getInstance().getPuntiRitiro().remove(pr);
        //TODO: RIMUOVI PUNTO RITIRO !!
    }

    public void removePuntoRitiro(int indexPr){
        if (!autorizzato) throw new RuntimeException("Utente non autorizzato");
        this.removePuntoRitiro(CentroCittadino.getInstance().getPuntiRitiro().get(indexPr));
        //TODO: RIMUOVI PUNTO RITIRO !!
    }


}
