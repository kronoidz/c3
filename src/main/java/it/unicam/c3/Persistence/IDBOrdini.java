package it.unicam.c3.Persistence;

import it.unicam.c3.Ordini.Ordine;

import java.sql.SQLException;
import java.util.List;

public interface IDBOrdini {

    List<Ordine> getOrdini() throws SQLException;
}
