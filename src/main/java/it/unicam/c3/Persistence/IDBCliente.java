package it.unicam.c3.Persistence;

import it.unicam.c3.Ordini.Ordine;

import java.sql.SQLException;

public interface IDBCliente{

    public void saveOrdine(Ordine ordine) throws Exception;
    
}
