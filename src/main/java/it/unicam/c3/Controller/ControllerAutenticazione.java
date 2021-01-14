package it.unicam.c3.Controller;

import it.unicam.c3.Anagrafica.Amministrazione;
import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Citta.CentroCittadino;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerAutenticazione {
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

    public ControllerGestore autenticaGestore(String password) {
        if (!Amministrazione.checkPassword(password))
            return null;

        return new ControllerGestore();
    }

    public enum TipoUtente {
        COMMERCIANTE, CLIENTE, CORRIERE
    }

    public void registra(String nome, String cognome, String email, String password,
                         TipoUtente tipo)
    {
        switch (tipo) {
            case CLIENTE:
                CentroCittadino.getInstance().addCliente(
                        new Cliente(nome, cognome, email, password)
                );
                break;
            case COMMERCIANTE:
                CentroCittadino.getInstance().addCommerciante(
                        new Commerciante(nome, cognome, email, password)
                );
                break;
            case CORRIERE:
                CentroCittadino.getInstance().addCorriere(
                        new Corriere(nome, cognome, email, password)
                );
                break;
        }
    }
}
