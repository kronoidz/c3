package it.unicam.c3.View.Spring;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Commercio.Prodotto;
import it.unicam.c3.Ordini.GestoreOrdini;
import it.unicam.c3.View.View;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.LinkedList;

@SpringBootApplication
public class SpringView implements View {
    @SuppressWarnings("RedundantThrows")
    @Override
    public void start() throws IOException {

        MakeTestData(); // todo: DEBUG ONLY

        SpringApplication.run(SpringView.class);
    }

    private void MakeTestData() {
        Commerciante ale = new Commerciante (
                "Alessandro",
                "Pecugi",
                "a",
                "a" );

        ale.addPuntoVendita("La Bottega di Ale", "Corso Vannucci, 22");
        ale.addPuntoVendita("Libreria di Ale", "Via agostino 62");
        ale.addPuntoVendita("Conad", "Strada della Vencaia, 25, 06089 Torgiano");

        CentroCittadino.getInstance().addCommerciante (ale);

        CentroCittadino.getInstance().addCorriere (
                new Corriere(
                        "Corriere",
                        "Corrieri",
                        "c.corrieri@example.com",
                        "ciaone" )
        );

        Cliente cliente = new Cliente(
                "Cliente",
                "Clienti",
                "c3.cliente@example.com",
                "ciaone"
        );

        CentroCittadino.getInstance().addCliente(cliente);

        ale.getPuntiVendita().get(0).addProdotto("Prodotto 1 descrizione blablabla", 15.99);
        ale.getPuntiVendita().get(0).addProdotto("Prodotto 2 descrizione", 1.90);
        ale.getPuntiVendita().get(0).addProdotto("Lorem ipsum dolor sit amet", 0.01);

        GestoreOrdini.getInstance().addOrdine(cliente, ale.getPuntiVendita().get(0),
                ale.getPuntiVendita().get(0).getProdotti());

        Cliente cliente2 = new Cliente(
                "Ricky",
                "Gervais",
                "ricky.gervais@example.com",
                "ciaone"
        );

        CentroCittadino.getInstance().addCliente(cliente);

        ale.getPuntiVendita().get(1).addProdotto("Prodotto 1 descrizione blablabla", 15.99);
        ale.getPuntiVendita().get(1).addProdotto("Prodotto 2 descrizione", 1.90);

        GestoreOrdini.getInstance().addOrdine(cliente2, ale.getPuntiVendita().get(1),
                ale.getPuntiVendita().get(1).getProdotti());

        for (int i = 1; i < 13; i++) {
            CentroCittadino.getInstance().addPuntoRitiro(
                    "Via Rossi, " + i, i * 10
            );
        }
    }
}
