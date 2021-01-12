
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Citta.PuntoRitiro;
import it.unicam.c3.Consegne.GestoreConsegne;
import it.unicam.c3.Controller.ControllerCorriere;
import it.unicam.c3.Ordini.GestoreOrdini;
import it.unicam.c3.View.Console.ConsoleView;
import it.unicam.c3.View.View;

import com.sun.mail.smtp.SMTPTransport;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;


public class C3 {

    public static void main(String[] args) throws MessagingException, IOException {
           // proto1();
        View consoleView = new ConsoleView();
        consoleView.start();
    }



    public static void proto1() throws IOException, MessagingException {
        Commerciante commerciante1 = new Commerciante("Giulio", "Bianchi", "giuliob.1995@gmail.com", "prova");
        commerciante1.addPuntoVendita("Il magazzino del gusto", "Corso Cavour, 15");
        commerciante1.addPuntoVendita("La fabbrica di cioccolato", "via Roma, 22");
        commerciante1.getPuntiVendita().get(0).addProdotto("Salame (€/etto)", 10);
        commerciante1.getPuntiVendita().get(0).addProdotto("Prosciutto San Daniele (€/etto)", 12);
        commerciante1.getPuntiVendita().get(1).addProdotto("Praline cioccolato fondente", 0.2);
        commerciante1.getPuntiVendita().get(1).addProdotto("Praline cioccolato bianco", 0.3);
        commerciante1.getPuntiVendita().get(1).addProdotto("Torta 5 persone", 25);
        commerciante1.getPuntiVendita().get(1).addOfferta("Sconto torta 5 persone","10%");
        commerciante1.getPuntiVendita().get(1).addOfferta("All'acquisto di almeno 20 praline a scelta","5 praline a scelta omaggio");

        Commerciante commerciante2 = new Commerciante("Alessandro", "Precugi", "alepeg@gmail.com", "prova");
        commerciante2.addPuntoVendita("Moonshiners", "Via Spalato, 50");
        commerciante2.getPuntiVendita().get(0).addProdotto("Vino Bianco", 12);
        commerciante2.getPuntiVendita().get(0).addProdotto("Prosecco DOC", 15);
        commerciante2.getPuntiVendita().get(0).addProdotto("Whiskey Lagavulin", 40);
        commerciante2.getPuntiVendita().get(0).addProdotto("Whiskey Jack Daniels", 28);
        commerciante2.getPuntiVendita().get(0).addOfferta("Sconto Whiskey Jack Daniels", "20%");
        commerciante2.getPuntiVendita().get(0).addOfferta("All'acquisto di almeno 2 bottiglie di whiskey","sconto 5€ sul totale");


        CentroCittadino.getInstance().addCommerciante(commerciante1);
        CentroCittadino.getInstance().addCommerciante(commerciante2);

        CentroCittadino.getInstance().addPuntoRitiro("Via Acquivive,35", 30);


        View consoleView = new ConsoleView();
        consoleView.start();

        GestoreConsegne.getInstance().addConsegna(GestoreOrdini.getInstance().getOrdini(commerciante2).get(0), commerciante2, CentroCittadino.getInstance().getPuntiRitiro().get(0));
        GestoreConsegne.getInstance().addConsegna(GestoreOrdini.getInstance().getOrdini(commerciante1).get(0), commerciante1, CentroCittadino.getInstance().getPuntiRitiro().get(0));
        GestoreConsegne.getInstance().addConsegna(GestoreOrdini.getInstance().getOrdini(commerciante1).get(1), commerciante1, CentroCittadino.getInstance().getPuntiRitiro().get(0));

        consoleView.start();
    }

}

