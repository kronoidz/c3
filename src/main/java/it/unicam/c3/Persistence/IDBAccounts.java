package it.unicam.c3.Persistence;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Anagrafica.Utente;
import it.unicam.c3.Controller.ControllerAutenticazione;

import java.sql.SQLException;
import java.util.List;

public interface IDBAccounts {

    List<Commerciante> getCommercianti() throws SQLException;

    List<Cliente> getClienti() throws SQLException;

    List<Corriere> getCorrieri() throws SQLException;

    void registerCommerciante(Commerciante commerciante) throws SQLException;

    void registerCliente(Cliente cliente) throws SQLException;

    void registerCorriere(Corriere corriere) throws SQLException;
}
