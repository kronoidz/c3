package it.unicam.c3.Anagrafica;

public class Corriere extends Utente {

    public Corriere(){}

    public Corriere(String nome, String cognome, String email, String password) {
        this.setNome(nome);
        this.setCognome(cognome);
        this.setEmail(email);
        this.setPassword(password);
    }
}
