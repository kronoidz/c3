package it.unicam.c3.View.Spring;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Citta.PuntoRitiro;
import it.unicam.c3.Commercio.PuntoVendita;
import it.unicam.c3.Ordini.GestoreOrdini;
import it.unicam.c3.Ordini.StatoOrdine;
import it.unicam.c3.View.View;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.unicam.c3.Anagrafica.Commerciante;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SpringView implements View {

    @SuppressWarnings("RedundantThrows")
    @Override
    public void start() throws IOException {
        makeDevData();

        SpringApplication.run(SpringView.class);
    }

    private void makeDevData() {
        Commerciante c1 = new Commerciante("Joni", "Mitchell", "a", "a");
        CentroCittadino.getInstance().addCommerciante(c1);

        c1.addPuntoVendita("Punto Vendita 1", "Via dei Punti Vendita, 1");
        c1.addPuntoVendita("Punto Vendita 2", "Via dei Punti Vendita, 2");

        List<PuntoVendita> pvs = c1.getPuntiVendita();

        for (int i = 1; i <= 85; i++) {
            pvs.get(0).addProdotto("Prodotto " + i, i * 0.05);
        }

        for (int i = 1; i <= 11; i++) {
            pvs.get(1).addProdotto("Prodotto " + i, i);
        }

        pvs.get(0).addOfferta("Descrizione offerta", "-10%");
        pvs.get(0).addOfferta("Descrizione offerta", "-10%", LocalDate.now().plusDays(7));

        Cliente cliente1 = new Cliente("Bruno", "Mars", "b", "b");
        CentroCittadino.getInstance().addCliente(cliente1);

        for (int i = 1; i < 8; i++) {
            CentroCittadino.getInstance().addPuntoRitiro("Via dei Punti Ritiro, " + i, i);
        }

        GestoreOrdini.getInstance().addOrdine(cliente1, pvs.get(0), pvs.get(0).getProdotti().subList(2, 9));
        GestoreOrdini.getInstance().addOrdine(cliente1, pvs.get(0), pvs.get(0).getProdotti().subList(1, 2));

        GestoreOrdini.getInstance().getOrdini(cliente1).get(1).setStato(StatoOrdine.TERMINATO);
    }

}
