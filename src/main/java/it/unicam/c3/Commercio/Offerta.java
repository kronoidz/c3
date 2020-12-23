package it.unicam.c3.Commercio;

public abstract class Offerta implements IOfferta {
    private String descrizione;
    private String importo;

    public Offerta() {
    }

    public Offerta(String descrizione, String importo) {
        setDescrizione(descrizione);
        setImporto(importo);
    }

    @Override
    public String getImporto() {
        return importo;
    }

    @Override
    public void setImporto(String importo) {
        this.importo = importo;
    }

    @Override
    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
