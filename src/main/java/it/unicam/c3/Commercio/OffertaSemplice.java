package it.unicam.c3.Commercio;

public class OffertaSemplice extends Offerta {

    public OffertaSemplice() {
        super();
    }

    public OffertaSemplice(String descrizione, String importo) {
        super(descrizione, importo);
    }

    public String toString(){
        return getDescrizione()+" Importo: ["+getImporto()+"]";
    }

}
