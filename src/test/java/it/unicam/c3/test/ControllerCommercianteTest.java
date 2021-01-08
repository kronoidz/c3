package it.unicam.c3.test;

import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Commercio.PuntoVendita;
import it.unicam.c3.Controller.ControllerCommerciante;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControllerCommercianteTest {
    private Commerciante commerciante;
    private ControllerCommerciante controller;

    @BeforeEach
    public void init() {
        commerciante = new Commerciante("Giulio", "Bianchi", "giuliob.1995@gmail.com", "prova");
        commerciante.addPuntoVendita("Il magazzino del gusto", "Corso Cavour, 15");
        commerciante.addPuntoVendita("La fabbrica di cioccolato", "via Roma, 22");
        commerciante.getPuntiVendita().get(0).addProdotto("Salame (€/etto)", 10);
        commerciante.getPuntiVendita().get(0).addProdotto("Prosciutto San Daniele (€/etto)", 12);
        commerciante.getPuntiVendita().get(1).addProdotto("Praline cioccolato fondente", 0.2);
        commerciante.getPuntiVendita().get(1).addProdotto("Praline cioccolato bianco", 0.3);
        commerciante.getPuntiVendita().get(1).addProdotto("Torta 5 persone", 25);

        controller = new ControllerCommerciante(commerciante);
    }

    @Test
    public void testAddProdotto() {
        PuntoVendita pv = commerciante.getPuntiVendita().get(1);
        controller.addProdotto(pv, "TEST", 999);
        Assertions.assertEquals(4, pv.getProdotti().size());
        Assertions.assertEquals("TEST", pv.getProdotti().get(3).getDescrizione());
        Assertions.assertEquals(999, pv.getProdotti().get(3).getPrezzo());
    }
}
