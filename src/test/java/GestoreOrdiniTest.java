import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Citta.PuntoRitiro;
import it.unicam.c3.Commercio.Prodotto;
import it.unicam.c3.Ordini.GestoreOrdini;
import it.unicam.c3.Ordini.StatoOrdine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestoreOrdiniTest {
    private CentroCittadino cc;
    private LinkedList<Prodotto> carrello;
    private Prodotto mela;
    private  Prodotto pera;
    private  Prodotto banana;
    private Commerciante comm1;
    private Commerciante comm2;

    @BeforeEach
    public void initGestoreOrdini(){
        cc = new CentroCittadino();
        carrello=new LinkedList<>();
        mela = new Prodotto("mela",1);
        pera = new Prodotto("pera",2);
        banana = new Prodotto("banana",3);
        cc.addPuntoRitiro(new PuntoRitiro("Via Panfilo, 16", 10));
        cc.addPuntoRitiro(new PuntoRitiro("Corso Cavour, 18", 10));
        comm1 = new Commerciante("Lorenzo", "Serini", "lorenzoserini@gmail.com", "prova");
        comm1.addPuntoVendita("Gestronomia","Corso cavour, 18");
        comm2=new Commerciante("Alessandro","Pecugi","alepec@gmail.com","prova");
        comm2.addPuntoVendita("Pescheria","Via Bonifazi 12");
        cc.addCommerciante(comm1);
        cc.addCommerciante(comm2);
        cc.addCliente(new Cliente("Paolo", "Rossi", "paolorossi@gmail.com", "prova"));
        cc.addCliente(new Cliente("Luca", "Rossi", "paolorossi@gmail.com", "prova"));

        carrello.add(mela);
        carrello.add(pera);
        carrello.add(banana);
    }

    @Test
    public void numberOrdiniAggiunti(){
        GestoreOrdini.getInstance().addOrdine(cc.getClienti().get(0),cc.getPuntiVendita().get(0), carrello);
        assertEquals(1, GestoreOrdini.getInstance().getOrdini(cc.getClienti().get(0)).size());
        assertEquals(0, GestoreOrdini.getInstance().getOrdini(cc.getClienti().get(1)).size());
    }

    @Test
    public void numberOrdiniCliente(){
        GestoreOrdini.getInstance().addOrdine(cc.getClienti().get(0),cc.getPuntiVendita().get(0), carrello);
        assertEquals(1,GestoreOrdini.getInstance().getOrdini(cc.getClienti().get(0)).size());
    }

    @Test
    public void numberOrdiniStatoCliente(){
        GestoreOrdini.getInstance().addOrdine(cc.getClienti().get(0),cc.getPuntiVendita().get(0), carrello);
        assertEquals(1,GestoreOrdini.getInstance().getOrdini(cc.getClienti().get(0), StatoOrdine.IN_ATTESA).size());
        assertEquals(0,GestoreOrdini.getInstance().getOrdini(cc.getClienti().get(0), StatoOrdine.ACCETTATO).size());
        assertEquals(0,GestoreOrdini.getInstance().getOrdini(cc.getClienti().get(0), StatoOrdine.RIFIUTATO).size());
    }

    @Test
    public void numberOrdiniCommerciante(){
        GestoreOrdini.getInstance().addOrdine(cc.getClienti().get(0),cc.getPuntiVendita().get(0), carrello);
        assertEquals(1,GestoreOrdini.getInstance().getOrdini(comm1).size());
        assertEquals(0,GestoreOrdini.getInstance().getOrdini(comm2).size());
    }

    @Test
    public void numberOrdiniStatoCommerciante(){
        GestoreOrdini.getInstance().addOrdine(cc.getClienti().get(0),cc.getPuntiVendita().get(0), carrello);
        assertEquals(1,GestoreOrdini.getInstance().getOrdini(comm1,StatoOrdine.IN_ATTESA).size());
        assertEquals(0,GestoreOrdini.getInstance().getOrdini(comm2,StatoOrdine.IN_ATTESA).size());
    }

    @Test
    public void numberProdottiInOrdine(){
        GestoreOrdini.getInstance().addOrdine(cc.getClienti().get(0),cc.getPuntiVendita().get(0), carrello);
        assertEquals(3,GestoreOrdini.getInstance().getOrdini(cc.getClienti().get(0)).get(0).getProdotti().size());
    }

    @Test
    public void changeStatoOrdine(){
        GestoreOrdini.getInstance().addOrdine(cc.getClienti().get(0),cc.getPuntiVendita().get(0), carrello);
        GestoreOrdini.getInstance().setStato(GestoreOrdini.getInstance().getOrdini(comm1).get(0),StatoOrdine.ACCETTATO);
        assertEquals(0,GestoreOrdini.getInstance().getOrdini(comm1,StatoOrdine.IN_ATTESA).size());
        assertEquals(1,GestoreOrdini.getInstance().getOrdini(comm1,StatoOrdine.ACCETTATO).size());
        assertEquals(1,GestoreOrdini.getInstance().getOrdini(cc.getClienti().get(0),StatoOrdine.ACCETTATO).size());
        assertEquals(0,GestoreOrdini.getInstance().getOrdini(comm2,StatoOrdine.IN_ATTESA).size());
        GestoreOrdini.getInstance().setStato(GestoreOrdini.getInstance().getOrdini(comm1).get(0),StatoOrdine.RIFIUTATO);
        assertEquals(1,GestoreOrdini.getInstance().getOrdini(comm1,StatoOrdine.RIFIUTATO).size());
        assertEquals(1,GestoreOrdini.getInstance().getOrdini(cc.getClienti().get(0),StatoOrdine.RIFIUTATO).size());
    }

}