package it.unicam.c3.Commercio;

public abstract class Offerta implements IOfferta{
    private String descrizione;
    private String importo;

    public Offerta(){}

    public Offerta(String descrizione, String importo){
        setDescrizione(descrizione);
        setImporto(importo);
    }

    public String getImporto() {
        return importo;
    }

    public void setImporto(String importo) {
        this.importo=importo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione=descrizione;
    }
}
