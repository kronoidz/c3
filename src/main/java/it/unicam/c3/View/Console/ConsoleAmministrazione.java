package it.unicam.c3.View.Console;

import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Controller.ControllerCorriere;
import it.unicam.c3.Controller.ControllerGestore;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleAmministrazione {
    private ControllerGestore controller;
    private BufferedReader br;
    private final String VISUALIZZA_PUNTI_RITIRO = "0";
    private final String AGGIUNGI_PUNTO_RITIRO = "1";
    private final String RETURN = "u";
    private final String LOGOUT = "L";


    public ConsoleAmministrazione() {
        controller = new ControllerGestore();
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    private void initChoice() {
        System.out.println("COSA VUOI FARE: ");
        System.out.println(VISUALIZZA_PUNTI_RITIRO + ") VISUALIZZA PUNTI RITIRO");
        System.out.println(AGGIUNGI_PUNTO_RITIRO + ") AGGIUNGI PUNTO RITIRO");
        System.out.println();
        System.out.println(LOGOUT + ") LOGOUT");

    }

    private void visualizzaPuntiRitiro() {
        System.out.println("PUNTI RITIRO:");
        for (int i = 0; i < CentroCittadino.getInstance().getPuntiRitiro().size(); i++) {
            System.out.println("-------------- PUNTO RITIRO " + i + "--------------");
            System.out.println("Indirizzo: " + CentroCittadino.getInstance().getPuntiRitiro().get(i).getIndirizzo());
            System.out.println("Capienza: " + CentroCittadino.getInstance().getPuntiRitiro().get(i).getCapienza());
            System.out.println("Slot Occupati: " + CentroCittadino.getInstance().getPuntiRitiro().get(i).getSlotOccupati());
            System.out.println("Slot Disponibili: " + CentroCittadino.getInstance().getPuntiRitiro().get(i).getSlotDisponibili());

        }
    }

    private void visualizzaPuntiRitiroView() throws IOException {
        String line;
        visualizzaPuntiRitiro();
        System.out.println("\n[number+enter eliminare un punto di ritiro]");
        System.out.println("[" + RETURN + "+enter to return]");
        line = br.readLine();
        if (!line.equals(RETURN)) {
            int number = Integer.parseInt(line);
            System.out.println("Vuoi davvero eliminare il punto di ritiro numero " + number + "?");
            System.out.println("[y+enter per eliminare il punto di ritiro]");
            System.out.println("n+enter per annullare l'operazione");
            line = br.readLine();
            if (line.equals("y")) controller.removePuntoRitiro(number);
        }
    }

    private void aggiungiPuntoRitiroView() throws IOException {
        String line;
        System.out.println("Immetti l'indirizzo del punto di ritiro: ");
        line = br.readLine();
        String indirizzo = line;
        System.out.println("Immetti la capienza del punto di ritiro (Slot a disposizione): ");
        line = br.readLine();
        int capienza = Integer.parseInt(line);
        System.out.println("Vuoi davvero aggiungere il punto di ritiro?");
        System.out.println("[y+enter per aggiungere il punto ritiro]");
        System.out.println("[n+enter per annullare l'operazione]");
        line = br.readLine();
        if (line.equals("y")) controller.addPuntoRitiro(indirizzo, capienza);
    }

    private boolean autenticazioneAmministrazione() throws IOException {
        String line;
        System.out.println("Immetti la password amministrazione:");
        line = br.readLine();
        return controller.autorizza(line);
    }


    public void amministrazioneView() throws IOException {
        String line;
        if(autenticazioneAmministrazione()==true) {
            do {
                initChoice();
                line = br.readLine();
                if (line.equals(VISUALIZZA_PUNTI_RITIRO)) {
                    visualizzaPuntiRitiroView();
                } else if (line.equals(AGGIUNGI_PUNTO_RITIRO)) {
                    aggiungiPuntoRitiroView();
                }
            } while (!line.equals(LOGOUT));
        } else System.out.println("\nACCESSO NEGATO: PASSWORD ERRATA!\n");
    }
}

