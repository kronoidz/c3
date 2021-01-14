package it.unicam.c3.Commercio;

import java.util.UUID;

public abstract class Offerta implements IOfferta {
    private String id;
    private String descrizione;
    private String importo;

    public Offerta() { }

    public Offerta(String descrizione, String importo, String id) {
        this.descrizione = descrizione;
        this.importo = importo;
        this.id = id;
    }

    public Offerta(String descrizione, String importo) {
        this(descrizione, importo, null);
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
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
