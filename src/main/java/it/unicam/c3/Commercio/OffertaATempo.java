package it.unicam.c3.Commercio;

import java.time.LocalDate;

public class OffertaATempo extends Offerta {
    LocalDate scadenza;

    public OffertaATempo() {
    }

    public OffertaATempo(String descrizione, String importo, LocalDate scadenza) {
        super(descrizione, importo);
        this.scadenza = scadenza;
    }

    public LocalDate getScadenza() {
        return this.scadenza;
    }

    public void setScadenza(LocalDate scadenza) {
        this.scadenza = scadenza;
    }

    public String toString(){
        if(this.scadenza!=null){
            return getDescrizione()+" Importo: ["+getImporto()+"] Scadenza: ["+getScadenza().toString()+"]";
        }else return getDescrizione()+" Importo: ["+getImporto()+"] Scadenza: [NULL]";
    }
}
