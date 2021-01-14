package it.unicam.c3.View.Spring.FormModels;

import it.unicam.c3.Commercio.PuntoVendita;

public class CommerciantePuntoVendita {
    private String id;
    private String nome;
    private String posizione;

    public CommerciantePuntoVendita() { }

    public CommerciantePuntoVendita(PuntoVendita pv) {
        this.id = pv.getId();
        this.nome = pv.getNome();
        this.posizione = pv.getPosizione();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosizione() {
        return posizione;
    }

    public void setPosizione(String posizione) {
        this.posizione = posizione;
    }
}
