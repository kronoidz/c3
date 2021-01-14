package it.unicam.c3.Persistence;

import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Commercio.IOfferta;
import it.unicam.c3.Commercio.Prodotto;
import it.unicam.c3.Commercio.PuntoVendita;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCommerciante extends DBConnection implements IDBCommerciante {
    private Commerciante commerciante;
    private String sql;

    public DBCommerciante(Commerciante commerciante) throws SQLException {
        super();
        this.commerciante = commerciante;
       // if (getConnection() == null) defaultConnection();
    }

    public DBCommerciante(Commerciante commerciante, String connectionString, String username, String password) throws SQLException {
        super(connectionString,username,password);
        this.commerciante = commerciante;
       // if (getConnection() == null) connection(connectionString, username, password);
    }

    @Override
    public void saveProdotto(PuntoVendita pv, Prodotto prodotto) throws SQLException {
        //TODO: MANCA CHIAVE ESTERNA PuntoVendita
        sql = "insert into Prodotti(Descrizione, Prezzo, Disponibilita) values (?,?,?)";
        PreparedStatement prepStat = getConnection().prepareStatement(sql);
        prepStat.setString(1, prodotto.getDescrizione());
        prepStat.setDouble(2, prodotto.getPrezzo());
        prepStat.setBoolean(3, prodotto.getDisponibilita());
        prepStat.executeUpdate();
    }

    @Override
    public void saveOfferta(IOfferta offerta) {

    }

    public String getIdPuntoVendita(PuntoVendita pv) throws SQLException {
        sql = "Select Id from c3.PuntiVendita where nome='" + pv.getNome() + "' and Posizione='" + pv.getPosizione() + "'";
        setData(sql);
        while (getData().next()) {
            return getData().getString("Id");
        }
        return null;
    }

    @Override
    public void savePuntoVendita(PuntoVendita puntoVendita) throws SQLException {
        sql = "insert into PuntiVendita(Nome, Posizione) values (?,?)";
        PreparedStatement prepStat = getConnection().prepareStatement(sql);
        prepStat.setString(1, puntoVendita.getNome());
        prepStat.setString(2, puntoVendita.getPosizione());
        prepStat.executeUpdate();
    }


}
