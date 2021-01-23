package it.unicam.c3.test;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Citta.PuntoRitiro;
import it.unicam.c3.Controller.ControllerCliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerClienteTest {
    private Commerciante commerciante1;
    private Commerciante commerciante2;
    private Cliente cliente = new Cliente("Lorenzo","Serini","lose@gmail.com","prova");
    private ControllerCliente controller;

    {
        try {
            controller = new ControllerCliente(cliente);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @BeforeEach
    public void initControllerCorriereTest(){

        commerciante1 = new Commerciante("Giulio", "Bianchi", "giuliob.1995@gmail.com", "prova");
        commerciante1.addPuntoVendita("Il magazzino del gusto", "Corso Cavour, 15");
        commerciante1.addPuntoVendita("La fabbrica di cioccolato", "via Roma, 22");
        commerciante1.getPuntiVendita().get(0).addProdotto("Salame (€/etto)", 10);
        commerciante1.getPuntiVendita().get(0).addProdotto("Prosciutto San Daniele (€/etto)", 12);
        commerciante1.getPuntiVendita().get(1).addProdotto("Praline cioccolato fondente", 0.2);
        commerciante1.getPuntiVendita().get(1).addProdotto("Praline cioccolato bianco", 0.3);
        commerciante1.getPuntiVendita().get(1).addProdotto("Torta 5 persone", 25);


        commerciante2 = new Commerciante("Alessandro", "Pecugi", "alepeg@gmail.com", "prova");
        commerciante2.addPuntoVendita("Moonshiners", "Via Spalato, 50");
        commerciante2.getPuntiVendita().get(0).addProdotto("Vino Bianco", 12);
        commerciante2.getPuntiVendita().get(0).addProdotto("Prosecco DOC", 15);
        commerciante2.getPuntiVendita().get(0).addProdotto("Whiskey Lagavulin", 40);
        commerciante2.getPuntiVendita().get(0).addProdotto("Whiskey Jack Daniels", 28);
        commerciante2.getPuntiVendita().get(0).addOfferta("Sconto Whiskey Jack Daniels", "20%");


        CentroCittadino.getInstance().addCommerciante(commerciante1);
        CentroCittadino.getInstance().addCommerciante(commerciante2);

        CentroCittadino.getInstance().addPuntoRitiro("Via Acquivive,35", 30);
    }

    @Test
    public void numberPuntiVendita(){
        assertEquals(2, commerciante1.getPuntiVendita().size());
        assertEquals(1, commerciante2.getPuntiVendita().size());
    }

    @Test
    public void sizeCarrello(){
        controller.addInCarrello(controller.getPuntiVendita().get(0), controller.getPuntiVendita().get(0).getProdotti().get(0));
        controller.addInCarrello(controller.getPuntiVendita().get(0), controller.getPuntiVendita().get(0).getProdotti().get(1));
        controller.addInCarrello(controller.getPuntiVendita().get(1), controller.getPuntiVendita().get(0).getProdotti().get(1));
        assertEquals(2,controller.getCarrello().size());
        assertEquals(2,controller.getCarrello().get(controller.getPuntiVendita().get(0)).size());
        assertEquals(1,controller.getCarrello().get(controller.getPuntiVendita().get(1)).size());
    }


    @Test
    public void numberOrdini(){
        controller.addInCarrello(controller.getPuntiVendita().get(0), controller.getPuntiVendita().get(0).getProdotti().get(0));
        controller.addInCarrello(controller.getPuntiVendita().get(0), controller.getPuntiVendita().get(0).getProdotti().get(1));
        controller.addInCarrello(controller.getPuntiVendita().get(1), controller.getPuntiVendita().get(0).getProdotti().get(1));
        try {
            controller.ordinaProdotti();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(2,controller.getOrdini().size());
    }


}
