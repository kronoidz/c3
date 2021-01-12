package it.unicam.c3.View.Console;

import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Controller.ControllerCorriere;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleAccountCorriere {
    private Corriere corriere;
    private ControllerCorriere controller;
    private BufferedReader br;
    private final String VISUALIZZA_CONSEGNE_IN_ATTESA = "0";
    private final String VISUALIZZA_CONSEGNE_PRESE_IN_CARICO = "1";
    private final String CONSEGNA_EFFETTUATA = "0";
    private static final String RETURN = "u";
    private final String LOGOUT = "L";

    public ConsoleAccountCorriere(Corriere corriere){
        this.corriere=corriere;
        controller=new ControllerCorriere(corriere);
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    private void initChoice(){
        System.out.println("COSA VUOI FARE:");
        System.out.println(VISUALIZZA_CONSEGNE_IN_ATTESA+") Visualizza Consegne In Attesa Di Consegna");
        System.out.println(VISUALIZZA_CONSEGNE_PRESE_IN_CARICO+") Visualizza Consegne Prese In Carico");
        System.out.println(LOGOUT + ") Logout");
    }

    private void visualizzaConsegneInAttesa(){
        System.out.println("CONSEGNE IN ATTESA:\n");
        for(int i=0; i<controller.getConsegneInAttesa().size();i++){
            System.out.println(i+") DETTAGLI CONSEGNA:");
            System.out.println("---------------");
            System.out.println("Id: "+controller.getConsegneInAttesa().get(i).getId());
            System.out.println("Nome Punto Vendita: "+controller.getConsegneInAttesa().get(i).getOrdine().getPuntoVendita().getNome());
            System.out.println("Posizione Punto Vendita: "+controller.getConsegneInAttesa().get(i).getOrdine().getPuntoVendita().getPosizione());
            System.out.println("Nome Commerciante: "+controller.getConsegneInAttesa().get(i).getCommerciante().getNome()+" "+controller.getConsegneInAttesa().get(i).getCommerciante().getCognome());
            System.out.println("Email Commerciante: "+controller.getConsegneInAttesa().get(i).getCommerciante().getEmail());
            System.out.println("Punto Ritiro: "+controller.getConsegneInAttesa().get(i).getPuntoRitiro().getIndirizzo());
            System.out.println("N. Slot Disponibili Punto Ritiro: "+controller.getConsegneInAttesa().get(i).getPuntoRitiro().getSlotDisponibili());
            System.out.println("Stato: "+controller.getConsegneInAttesa().get(i).getStato());
            System.out.println("Numero Prodotti: "+controller.getConsegneInAttesa().get(i).getOrdine().getProdotti().size());
            //Potrei mettere il visualizza dettaglio consegna in cui vedo i prodotti dell'ordine
        }
    }

    private void visualizzaConsegneInCarico(){
        System.out.println("CONSEGNE PRESE IN CARICO:\n");
        for(int i=0; i<controller.getConsegneInCarico().size();i++){
            System.out.println(i+") DETTAGLI CONSEGNA:");
            System.out.println("---------------");
            System.out.println("Id: "+controller.getConsegneInCarico().get(i).getId());
            System.out.println("Nome Punto Vendita: "+controller.getConsegneInCarico().get(i).getOrdine().getPuntoVendita().getNome());
            System.out.println("Posizione Punto Vendita: "+controller.getConsegneInCarico().get(i).getOrdine().getPuntoVendita().getPosizione());
            System.out.println("Nome Commerciante: "+controller.getConsegneInCarico().get(i).getCommerciante().getNome()+" "+controller.getConsegneInCarico().get(i).getCommerciante().getCognome());
            System.out.println("Email Commerciante: "+controller.getConsegneInCarico().get(i).getCommerciante().getEmail());
            System.out.println("Punto Ritiro: "+controller.getConsegneInCarico().get(i).getPuntoRitiro().getIndirizzo());
            System.out.println("Stato: "+controller.getConsegneInCarico().get(i).getStato());
            System.out.println("Numero Prodotti: "+controller.getConsegneInCarico().get(i).getOrdine().getProdotti().size());
        }
    }

    private void consegneInAttesaView() throws IOException {
        String line;
        visualizzaConsegneInAttesa();
        System.out.println("[number+enter per prendere in carico la consegna]");
        System.out.println("[u+enter to return]");
        line = br.readLine();
        if(!line.equals(RETURN)){
            try {
                controller.prendiInCarico(Integer.parseInt(line));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void consegnePreseInCaricoView() throws IOException {
        String line;
        visualizzaConsegneInCarico();
        System.out.println("[number+enter per segnalare che la consegna è stata effettuata]");
        System.out.println("[u+enter to return]");
        line = br.readLine();
        if(!line.equals(RETURN)) {
            try {
                controller.effettuaConsegna(controller.getConsegneInCarico().get(Integer.parseInt(line)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void corriereView() throws IOException, MessagingException {
        String line;
        do {
            initChoice();
            line= br.readLine();
            if (line.equals(VISUALIZZA_CONSEGNE_IN_ATTESA)) {
                consegneInAttesaView();
            }else if(line.equals(VISUALIZZA_CONSEGNE_PRESE_IN_CARICO)){
                consegnePreseInCaricoView();
            }
        } while (!line.equals(LOGOUT));
    }


}
