package it.unicam.c3.Anagrafica;

public class Cliente extends Utente {

    public Cliente(){}

    public Cliente(String nome, String cognome, String email, String password) {
        this.setNome(nome);
        this.setCognome(cognome);
        this.setEmail(email);
        this.setPassword(password);
    }

}
