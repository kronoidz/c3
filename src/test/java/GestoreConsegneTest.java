import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Citta.PuntoRitiro;
import it.unicam.c3.Consegne.Consegna;
import it.unicam.c3.Consegne.GestoreConsegne;
import it.unicam.c3.Ordini.Ordine;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertNotNull;

public class GestoreConsegneTest {
    private GestoreConsegne gc;
    private CentroCittadino cc;
    private Commerciante commerciante1;
    private Corriere corriere1;
    private Ordine ordine1;
    private Ordine ordine2;
    private Ordine ordine3;


    @BeforeEach
    void initGestoreConsegne(){
        gc = new GestoreConsegne();
        cc = new CentroCittadino();
        cc.addPuntoRitiro(new PuntoRitiro("Via Panfilo, 16", 10));
        cc.addPuntoRitiro(new PuntoRitiro("Corso Cavour, 18", 10));
        commerciante1=new Commerciante("Paolo","Rossi","paolorossi@gmail.com","prova");
        corriere1=new Corriere();
        ordine1=new Ordine();
        ordine2=new Ordine();
        ordine3=new Ordine();

        gc.addConsegna(ordine1, commerciante1, cc.getPuntiRitiro().get(0));
        gc.addConsegna(ordine2, commerciante1, cc.getPuntiRitiro().get(0));
        gc.addConsegna(ordine3, commerciante1, cc.getPuntiRitiro().get(1));


    }

    @Test
    void numberConsegneInAttesa(){
        Assert.assertEquals(0,gc.getConsegneInAttesa().size());
    }

}
