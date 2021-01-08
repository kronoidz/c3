package it.unicam.c3.View.Console;

import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Consegne.Consegna;
import it.unicam.c3.Consegne.StatoConsegna;
import it.unicam.c3.Controller.ControllerCommerciante;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleAccountCommerciante {
    private Commerciante commerciante;
    private ControllerCommerciante controller;
    private BufferedReader br;

    private static final String VISUALIZZA_PUNTI_VENDITA = "1";
    private static final String VISUALIZZA_ORDINI = "2";
    private static final String ABILITA_RITIRO_CONSEGNA = "3";
    private static final String LOGOUT = "4";

    public ConsoleAccountCommerciante(Commerciante commerciante) {
        this.commerciante = commerciante;
        controller = new ControllerCommerciante(commerciante);
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void initChoice() {
        System.out.println("COSA VUOI FARE:");
        System.out.println("(" + VISUALIZZA_PUNTI_VENDITA + ") Visualizza punti vendita");
        System.out.println("(" + VISUALIZZA_ORDINI + ") Visualizza ordini");
        System.out.println("(" + ABILITA_RITIRO_CONSEGNA + ") Abilita ritiro consegna");
        System.out.println("(" + LOGOUT + ") Logout");
    }

    public void commercianteView() throws IOException {
        String line;
        do {
            initChoice();
            line= br.readLine();
            switch (line) {
                case VISUALIZZA_PUNTI_VENDITA:
                    puntiVenditaView();
                    break;
                case VISUALIZZA_ORDINI:
                    ordiniView();
                    break;
                case ABILITA_RITIRO_CONSEGNA:
                    abilitaRitiroView();
                    break;
            }
        }
        while (!line.equals(LOGOUT));
    }

    private void abilitaRitiroView() {
        List<Consegna> consegne = Stream.concat(
                controller.getConsegne(StatoConsegna.EFFETTUATA).stream(),
                controller.getConsegne(StatoConsegna.PRESA_IN_CARICO).stream()
            ).collect(Collectors.toList());

        for (Consegna consegna : consegne) {
            System.out.println(consegna.toString());
        }


    }

    private void ordiniView() {
        
    }

    private void puntiVenditaView() {
    }
}
