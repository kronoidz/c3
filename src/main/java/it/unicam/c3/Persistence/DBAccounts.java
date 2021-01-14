package it.unicam.c3.Persistence;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBAccounts extends DBConnection implements IDBAccounts{
    private String sql;

    public DBAccounts() throws SQLException {
        super();
    }

    public DBAccounts(String connectionString, String username, String password) throws SQLException {
        super(connectionString,username,password);
    }


    @Override
    public List<Commerciante> getCommercianti() throws SQLException {
        List<Commerciante> accountsList= new LinkedList<>();
        String sql = "Select * from Commercianti";
        setData(sql);
        while(getData().next()){
                accountsList.add( new Commerciante(getData().getString("Nome"), getData().getString("Cognome"), getData().getString("Email"), getData().getString("Password")));
        }
        return accountsList;
    }

    @Override
    public List<Cliente> getClienti() throws SQLException {
        List<Cliente> accountsList= new LinkedList<>();
        sql = "Select * from Clienti";
        setData(sql);
        while(getData().next()){
            accountsList.add( new Cliente(getData().getString("Nome"), getData().getString("Cognome"), getData().getString("Email"), getData().getString("Password")));
        }
        return accountsList;
    }

    @Override
    public List<Corriere> getCorrieri() throws SQLException {
        List<Corriere> accountsList= new LinkedList<>();
        String sql = "Select * from Corrieri";
        setData(sql);
        while(getData().next()){
            accountsList.add( new Corriere(getData().getString("Nome"), getData().getString("Cognome"), getData().getString("Email"), getData().getString("Password")));
        }
        return accountsList;
    }

    @Override
    public void registerCommerciante(Commerciante commerciante) throws SQLException {
        sql = "insert into Commercianti(Email, Nome, Cognome, Password) values (?,?,?,?)";
        PreparedStatement prepStat = getConnection().prepareStatement(sql);
        prepStat.setString(1, commerciante.getEmail());
        prepStat.setString(2, commerciante.getNome());
        prepStat.setString(3, commerciante.getCognome());
        prepStat.setString(4, commerciante.getPassword());
        prepStat.executeUpdate();
    }

    @Override
    public void registerCliente(Cliente cliente) throws SQLException {
        sql = "insert into Clienti(Email, Nome, Cognome, Password) values (?,?,?,?)";
        PreparedStatement prepStat = getConnection().prepareStatement(sql);
        prepStat.setString(1, cliente.getEmail());
        prepStat.setString(2, cliente.getNome());
        prepStat.setString(3, cliente.getCognome());
        prepStat.setString(4, cliente.getPassword());
        prepStat.executeUpdate();
    }

    @Override
    public void registerCorriere(Corriere corriere) throws SQLException {
        sql = "insert into Corrieri(Email, Nome, Cognome, Password) values (?,?,?,?)";
        PreparedStatement prepStat = getConnection().prepareStatement(sql);
        prepStat.setString(1, corriere.getEmail());
        prepStat.setString(2, corriere.getNome());
        prepStat.setString(3, corriere.getCognome());
        prepStat.setString(4, corriere.getPassword());
        prepStat.executeUpdate();
    }
}
