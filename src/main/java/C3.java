
import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Citta.PuntoRitiro;
import it.unicam.c3.Commercio.Prodotto;
import it.unicam.c3.Commercio.PuntoVendita;
import it.unicam.c3.Consegne.GestoreConsegne;
import it.unicam.c3.Controller.*;
import it.unicam.c3.Ordini.GestoreOrdini;
import it.unicam.c3.Persistence.DBAccounts;
import it.unicam.c3.Persistence.DBCommerciante;
import it.unicam.c3.View.Console.ConsoleView;
import it.unicam.c3.View.Spring.SpringView;
import it.unicam.c3.View.View;

import com.sun.mail.smtp.SMTPTransport;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.Security;
import java.sql.*;
import java.util.Date;
import java.util.Properties;


public class C3 {

    public static void main(String[] args) throws Exception {

        // proto1();



         View consoleView = new ConsoleView();
         consoleView.start();



       // View springView = new SpringView();
       // springView.start();

    //    ControllerAutenticazione ca = new ControllerAutenticazione();

       //ca.registra("Alessandro","Pecugi", "alessandro@gmail.com","Prova", ControllerAutenticazione.TipoUtente.COMMERCIANTE);
       //Commerciante commercianteAutenticato = ca.autenticaCommerciante("lorenzose.1995@gmail.com","prova");
    //   Commerciante commercianteAutenticato = ca.autenticaCommerciante("alessandro@gmail.com","Prova");

    //    ControllerCommerciante controller = new ControllerCommerciante(commercianteAutenticato);

        //controller.addPuntoVendita("Via Acquevive,35","Punto Ale");
     //   System.out.println(controller.getPuntiVendita().size());
        /*
        System.out.println(controller.getPuntiVendita().get(0).getId());
        System.out.println(controller.getPuntiVendita().get(0).getNome());
        System.out.println(controller.getPuntiVendita().get(0).getPosizione());*/
        //controller.addPuntoVendita("PROVA","PROVAAAAA");

       // View view = new ConsoleView();
       // view.start();
        //DBCommerciante commerciante = new DBCommerciante(commercianteAutenticato);
        //PuntoVendita pv = new PuntoVendita(commercianteAutenticato,"PRVOA PPPPP","PIPIPIPI");
        //commerciante.savePuntoVendita(pv);



       // controller.addProdotto(0,"PROVA1",100);
      //  controller.addProdotto(0,"PROVA2",200);
      //  controller.addProdotto(0,"PROVA3",300);
      //  controller.addPuntoVendita("VIA BOO", "P1");
       // controller.addPuntoVendita("VIA BOO2", "P2");

        //controller.addPuntoVendita("Via Roma,126", "La Fabbrica Del Gusto");
        //controller.addProdotto(0,"Cioccolata",11);
       // controller.addOfferta(controller.getPuntiVendita().get(0),"OFFERTA DI PROVA","20%");
      //  db.removeProdotto(cm.getPuntiVendita().get(0).getProdotti().get(0));
      // System.out.println(commercianteAutenticato.getPuntiVendita().get(0).getProdotti());

       // PuntoVendita pv = new PuntoVendita(cm,"La fabbrica del gusto", "Corso cavour, 15");

        //db.savePuntoVendita(pv);

       // Prodotto p = new Prodotto("PRODOTTO TEST", 100.12);

        //Prodotto p2 = new Prodotto("PRODOTTO TEST", 100.12);
        //p2.setDisponibilita(false);

        //db.saveProdotto(pv,p);
        //db.saveProdotto(pv,p2);


    }



    public static void proto1() throws IOException, MessagingException, SQLException {
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

