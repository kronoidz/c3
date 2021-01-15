package it.unicam.c3.Anagrafica;

import it.unicam.c3.Commercio.PuntoVendita;

import java.util.LinkedList;
import java.util.List;

public class Commerciante extends Utente {
    private List<PuntoVendita> puntiVendita;

    public Commerciante() {
        this.puntiVendita = new LinkedList<>();
    }

    public Commerciante(List<PuntoVendita> puntiVendita) {
        this.puntiVendita.addAll(puntiVendita);
    }

    public Commerciante(String nome, String cognome, String email, String password) {
        this.setNome(nome);
        this.setCognome(cognome);
        this.setEmail(email);
        this.setPassword(password);
        this.puntiVendita = new LinkedList<>();
    }

    public List<PuntoVendita> getPuntiVendita() {
        return puntiVendita;
    }

    public void addPuntoVendita(String nome, String posizione) {
        puntiVendita.add(new PuntoVendita(this,nome, posizione));
    }

    public void addPuntoVendita(String id, String nome, String posizione) {
        puntiVendita.add(new PuntoVendita(this,nome, posizione,id));
    }
}
