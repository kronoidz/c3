package it.unicam.c3.Citta;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Commercio.PuntoVendita;

import java.util.LinkedList;
import java.util.List;

public class CentroCittadino {
    private List<Commerciante> commercianti;
    private List<Cliente> clienti;
    private List<Corriere> corrieri;
    private List<PuntoRitiro> puntiRitiro;

    public CentroCittadino(){
        commercianti = new LinkedList<>();
        clienti = new LinkedList<>();
        corrieri = new LinkedList<>();
        puntiRitiro = new LinkedList<>();
    }

    public CentroCittadino(List<Commerciante> commercianti, List<Cliente> clienti, List<Corriere> corrieri, List<PuntoRitiro> puntiRitiro){
        this.commercianti=commercianti;
        this.clienti=clienti;
        this.corrieri=corrieri;
        this.puntiRitiro=puntiRitiro;
    }

    public List<PuntoVendita> getPuntiVendita() {
        List<PuntoVendita> puntiVendita = new LinkedList<>();
        for (Commerciante c:commercianti) {
            c.getPuntiVendita().stream().forEach(puntiVendita::add);
        }
        return puntiVendita;
    }

    public List<Commerciante> getCommercianti(){
        return this.commercianti;
    }

    public void addCommerciante(Commerciante commerciante){
        this.commercianti.add(commerciante);
    }

    public void removeCommerciante(int index){
        this.commercianti.remove(index);
    }

    public void removeCommerciante(Commerciante commerciante){
        this.commercianti.remove(commerciante);
    }

    public List<PuntoRitiro> getPuntiRitiro(){
        return  this.puntiRitiro;
    }

    public void addPuntoRitiro(PuntoRitiro pr){
        this.puntiRitiro.add(pr);
    }

    public void removePuntoRitiro(int index){
        this.puntiRitiro.remove(index);
    }

    public void removePuntoRitiro(PuntoRitiro pr){
        this.puntiRitiro.remove(pr);
    }

    public void addCliente(Cliente cliente){
        this.clienti.add(cliente);
    }

    public List<Cliente> getClienti(){
        return this.clienti;
    }

    public void removeCliente(int index){
        this.clienti.remove(index);
    }

    public void removeCliente(Cliente cliente){
        this.clienti.remove(cliente);
    }

    public List<Corriere> getCorrieri(){
        return this.corrieri;
    }

    public void addCorriere(Corriere corriere){
        this.corrieri.add(corriere);
    }

    public void removeCorriere(int index){
        this.corrieri.remove(index);
    }

    public void removeCorriere(Corriere corriere){
        this.corrieri.remove(corriere);
    }
}
