/*******************************************************************************
 * MIT License

 * Copyright (c) 2021 Lorenzo Serini and Alessandro Pecugi
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
/**
 *
 */

package it.unicam.c3.View.Spring;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Commercio.PuntoVendita;
import it.unicam.c3.Consegne.Consegna;
import it.unicam.c3.Consegne.GestoreConsegne;
import it.unicam.c3.Consegne.StatoConsegna;
import it.unicam.c3.Ordini.GestoreOrdini;
import it.unicam.c3.Ordini.Ordine;
import it.unicam.c3.Ordini.StatoOrdine;
import it.unicam.c3.View.View;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SpringView implements View {

    @SuppressWarnings("RedundantThrows")
    @Override
    public void start() throws IOException {
        //makeDevData();

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

        Corriere corriere1 = new Corriere("Elton", "John", "c", "c");
        CentroCittadino.getInstance().addCorriere(corriere1);

        GestoreOrdini.getInstance().addOrdine(cliente1, pvs.get(1), pvs.get(1).getProdotti().subList(1, 10));
        Ordine ord = GestoreOrdini.getInstance().getOrdini(pvs.get(1).getCommerciante()).get(0);
        ord.setStato(StatoOrdine.ACCETTATO);

        GestoreConsegne.getInstance().addConsegna(ord, pvs.get(1).getCommerciante(),
                CentroCittadino.getInstance().getPuntiRitiro().get(2));
        Consegna consegna = GestoreConsegne.getInstance().getConsegne().get(0);
        consegna.setStato(StatoConsegna.PRESA_IN_CARICO);
        consegna.setCorriere(corriere1);

        GestoreOrdini.getInstance().addOrdine(cliente1, pvs.get(1), pvs.get(1).getProdotti().subList(4, 5));
        Ordine ord2 = GestoreOrdini.getInstance().getOrdini(pvs.get(1).getCommerciante()).get(1);
        ord2.setStato(StatoOrdine.ACCETTATO);

        GestoreConsegne.getInstance().addConsegna(ord2, pvs.get(1).getCommerciante(),
                CentroCittadino.getInstance().getPuntiRitiro().get(3));
        Consegna consegna2 = GestoreConsegne.getInstance().getConsegne().get(1);
        consegna2.setStato(StatoConsegna.EFFETTUATA);
        consegna.setCorriere(corriere1);
        consegna.setRitirabile(true);
    }

}
