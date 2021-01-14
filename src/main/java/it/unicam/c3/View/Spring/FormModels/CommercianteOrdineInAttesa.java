package it.unicam.c3.View.Spring.FormModels;

import it.unicam.c3.Ordini.Ordine;

public class CommercianteOrdineInAttesa {
    private String id;
    private int numeroProdotti;
    private String nomeCliente;
    private String emailCliente;

    public CommercianteOrdineInAttesa() { }

    public CommercianteOrdineInAttesa(Ordine ordine) {
        this.id = ordine.getId();
        this.numeroProdotti = ordine.getProdotti().size();
        this.nomeCliente = ordine.getCliente().getNome() + " " + ordine.getCliente().getCognome();
        this.emailCliente = ordine.getCliente().getEmail();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public int getNumeroProdotti() {
        return numeroProdotti;
    }

    public void setNumeroProdotti(int numeroProdotti) {
        this.numeroProdotti = numeroProdotti;
    }
}
