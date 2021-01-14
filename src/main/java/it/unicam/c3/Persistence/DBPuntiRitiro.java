package it.unicam.c3.Persistence;

import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Citta.PuntoRitiro;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBPuntiRitiro extends DBConnection implements IDBPuntiRitiro{
    private String sql;


    public DBPuntiRitiro() throws SQLException {
        super();
    }

    public DBPuntiRitiro(String connectionString, String username, String password) throws SQLException{
        super(connectionString,username,password);
    }

    @Override
    public void savePuntoRitiro(PuntoRitiro pr) throws SQLException {
        sql = "insert into PuntiRitiro(Id, Indirizzo, Capienza) values (?,?,?)";
        PreparedStatement prepStat = getConnection().prepareStatement(sql);
        prepStat.setString(1, pr.getId());
        prepStat.setString(2, pr.getIndirizzo());
        prepStat.setInt(3, pr.getCapienza());
        prepStat.executeUpdate();
    }

    @Override
    public List<PuntoRitiro> getPuntiRitiro() throws SQLException {
        List<PuntoRitiro> puntiRitiro= new LinkedList<>();
        sql = "Select * from PuntiRitiro";
        setData(sql);
        while(getData().next()){
            PuntoRitiro pr = new PuntoRitiro(getData().getString("Indirizzo"), getData().getInt("Capienza"));
            pr.setId(getData().getString("Id"));
            puntiRitiro.add(pr);
        }
        return puntiRitiro;
    }
}
