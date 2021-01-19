package it.unicam.c3.Persistence;

import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Commercio.Prodotto;
import it.unicam.c3.Ordini.Ordine;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBCliente extends DBConnection implements IDBCliente{
    private String sql;

    public DBCliente() throws SQLException {
        super();
    }

    public DBCliente(String connectionString, String username, String password) throws SQLException {
        super(connectionString,username,password);
    }

    public void saveOrdine(Ordine ordine) throws SQLException {
        sql = "insert into Ordini(Id,Cliente, PuntoVendita, Stato) values (?,?,?,?)";
        PreparedStatement prepStat = getConnection().prepareStatement(sql);
        prepStat.setString(1, ordine.getId());
        prepStat.setString(2, ordine.getCliente().getEmail());
        prepStat.setString(3, ordine.getPuntoVendita().getId());
        prepStat.setString(4, ordine.getStato().toString());
        prepStat.executeUpdate();
        this.saveProdottiOrdine(ordine);
    }

    private void saveProdottiOrdine(Ordine ordine) throws SQLException {
        sql="insert into ListaProdottiOrdine(IdOrdine,IdProdotto) values (?,?)";
        PreparedStatement prepStat = getConnection().prepareStatement(sql);
        for(Prodotto p:ordine.getProdotti()) {
            prepStat.setString(1, ordine.getId());
            prepStat.setString(2, p.getId());
            prepStat.executeUpdate();
        }
    }


}
