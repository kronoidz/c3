package it.unicam.c3.Persistence;

import it.unicam.c3.Citta.PuntoRitiro;

import java.sql.SQLException;
import java.util.List;

public interface IDBPuntiRitiro {

    void savePuntoRitiro(PuntoRitiro pr) throws SQLException;

    List<PuntoRitiro> getPuntiRitiro() throws SQLException;
}
