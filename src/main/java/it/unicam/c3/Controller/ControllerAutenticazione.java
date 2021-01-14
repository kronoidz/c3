package it.unicam.c3.Controller;

import it.unicam.c3.Anagrafica.Amministrazione;
import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Persistence.DBAccounts;
import it.unicam.c3.Persistence.DBPuntiRitiro;
import it.unicam.c3.Persistence.IDBAccounts;
import it.unicam.c3.Persistence.IDBPuntiRitiro;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerAutenticazione {
    private IDBAccounts dbAccounts;

    //public static boolean autenticazioneAbilitata = false;

    public ControllerAutenticazione(IDBAccounts dbAccounts, IDBPuntiRitiro dbPuntiRitiro ) throws SQLException {
        this.dbAccounts=dbAccounts;
        CentroCittadino.getInstance(this.dbAccounts.getCommercianti(), this.dbAccounts.getClienti(), this.dbAccounts.getCorrieri(), dbPuntiRitiro.getPuntiRitiro());
    }

    public ControllerAutenticazione() throws SQLException {
        this(new DBAccounts(), new DBPuntiRitiro());
    }

    public Commerciante autenticaCommerciante(String email, String password) {
        List<Commerciante> commercianti = CentroCittadino.getInstance()
                .getCommercianti()
                .stream()
                .filter(commerciante ->
                        commerciante.getEmail().equals(email) &&
                        commerciante.getPassword().equals(password))
                .collect(Collectors.toList());
        if (commercianti.size() < 1) {
            return null;
        }
        return commercianti.get(0);
    }

    public Cliente autenticaCliente(String email, String password) {
        List<Cliente> clienti = CentroCittadino.getInstance()
                .getClienti()
                .stream()
                .filter(cliente ->
                        cliente.getEmail().equals(email) &&
                                cliente.getPassword().equals(password))
                .collect(Collectors.toList());
        if (clienti.size() < 1) {
            return null;
        }
        return clienti.get(0);
    }

    public Corriere autenticaCorriere(String email, String password) {
        List<Corriere> corrieri = CentroCittadino.getInstance()
                .getCorrieri()
                .stream()
                .filter(corriere ->
                        corriere.getEmail().equals(email) &&
                        corriere.getPassword().equals(password))
                .collect(Collectors.toList());
        if (corrieri.size() < 1) {
            return null;
        }
        return corrieri.get(0);
    }

  /*TODO:  public ControllerGestore autenticaGestore(String password) {
        if (!Amministrazione.checkPassword(password))
            return null;
        return new ControllerGestore(); LO METTIAMO QUI O NO?
        IN QUESTO CASO NON SERVONO I COMANDI DI CONTROLLO
        AUTORIZZAZIONE IN CONTROLLERGESTORE
    }*/

    public enum TipoUtente {
        COMMERCIANTE, CLIENTE, CORRIERE
    }

    public void registra(String nome, String cognome, String email, String password,
                         TipoUtente tipo) throws SQLException {
        switch (tipo) {
            case CLIENTE:
                Cliente cliente =  new Cliente(nome, cognome, email, password);
                CentroCittadino.getInstance().addCliente(cliente);
                this.dbAccounts.registerCliente(cliente);
                break;
            case COMMERCIANTE:
                Commerciante commerciante =  new Commerciante(nome, cognome, email, password);
                CentroCittadino.getInstance().addCommerciante(commerciante);
                this.dbAccounts.registerCommerciante(commerciante);
                break;
            case CORRIERE:
                Corriere corriere = new Corriere(nome, cognome, email, password);
                CentroCittadino.getInstance().addCorriere(corriere);
                this.dbAccounts.registerCorriere(corriere);
                break;
        }
    }

    public void logout() {
        // ?
    }
}
