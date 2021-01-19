package it.unicam.c3.Persistence;

import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Commercio.IOfferta;
import it.unicam.c3.Commercio.Offerta;
import it.unicam.c3.Commercio.Prodotto;
import it.unicam.c3.Commercio.PuntoVendita;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBCommerciante extends DBConnection implements IDBCommerciante {
    private Commerciante commerciante;
    private String sql;

    public DBCommerciante(Commerciante commerciante) throws SQLException {
        super();
        this.commerciante = commerciante;
    }

    public DBCommerciante(Commerciante commerciante, String connectionString, String username, String password) throws SQLException {
        super(connectionString,username,password);
        this.commerciante = commerciante;
    }

    @Override
    public void saveProdotto(PuntoVendita pv, Prodotto prodotto) throws SQLException {
        sql = "insert into Prodotti(Id,Descrizione, Prezzo, Disponibilita, PuntoVendita) values (?,?,?,?,?)";
        PreparedStatement prepStat = getConnection().prepareStatement(sql);
        prepStat.setString(1, prodotto.getId());
        prepStat.setString(2, prodotto.getDescrizione());
        prepStat.setDouble(3, prodotto.getPrezzo());
        prepStat.setBoolean(4, prodotto.getDisponibilita());
        prepStat.setString(5, pv.getId());
        prepStat.executeUpdate();
    }

    @Override
    public void saveOfferta(PuntoVendita pv, IOfferta offerta) throws SQLException {
        sql = "insert into Offerte(Id,Descrizione, Importo, PuntoVendita) values (?,?,?,?)";
        PreparedStatement prepStat = getConnection().prepareStatement(sql);
        prepStat.setString(1, offerta.getId());
        prepStat.setString(2, offerta.getDescrizione());
        prepStat.setString(3, offerta.getImporto());
        prepStat.setString(4, pv.getId());
        prepStat.executeUpdate();
    }

    @Override
    public void savePuntoVendita(PuntoVendita puntoVendita) throws SQLException {
        sql = "insert into PuntiVendita(Id, Nome, Posizione, Commerciante) values (?,?,?,?)";
        PreparedStatement prepStat = getConnection().prepareStatement(sql);
        prepStat.setString(1, puntoVendita.getId());
        prepStat.setString(2, puntoVendita.getNome());
        prepStat.setString(3, puntoVendita.getPosizione());
        prepStat.setString(4, this.commerciante.getEmail());
        prepStat.executeUpdate();
    }

    public void removePuntoVendita(PuntoVendita pv) throws SQLException {
        for(Prodotto p:pv.getProdotti()) removeProdotto(p);
        for(IOfferta o:pv.getOfferte()) removeOfferta(o);
        String sql = "delete from PuntiVendita where Id=?";
        PreparedStatement prepStat = getConnection().prepareStatement(sql);
        prepStat.setString(1, pv.getId());
        prepStat.executeUpdate();
    }

    public void removeProdotto(Prodotto prodotto) throws SQLException {
        String sql = "delete from Prodotti where Id=?";
        PreparedStatement prepStat = getConnection().prepareStatement(sql);
        prepStat.setString(1, prodotto.getId());
        prepStat.executeUpdate();
    }

    public void removeOfferta(IOfferta offerta) throws SQLException {
        String sql = "delete from Offerte where Id=?";
        PreparedStatement prepStat = getConnection().prepareStatement(sql);
        prepStat.setString(1, offerta.getId());
        prepStat.executeUpdate();
    }

}
