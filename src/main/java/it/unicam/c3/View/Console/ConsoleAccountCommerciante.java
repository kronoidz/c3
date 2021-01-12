package it.unicam.c3.View.Console;


import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Citta.PuntoRitiro;
import it.unicam.c3.Commercio.Prodotto;
import it.unicam.c3.Commercio.PuntoVendita;
import it.unicam.c3.Consegne.Consegna;
import it.unicam.c3.Consegne.StatoConsegna;
import it.unicam.c3.Controller.ControllerCommerciante;
import it.unicam.c3.Ordini.Ordine;
import it.unicam.c3.Ordini.StatoOrdine;

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
    private static final String AGGIUNGI_PUNTO_VENDITA = "2";
    private static final String VISUALIZZA_PRODOTTI = "1";
    private static final String AGGIUNGI_PRODOTTO = "2";
    private static final String VISUALIZZA_ORDINI_IN_ATTESA = "3";
    private static final String ABILITA_RITIRO_CONSEGNA = "4";
    private static final String RETURN = "u";
    private static final String LOGOUT = "L";

    public ConsoleAccountCommerciante(Commerciante commerciante) {
        this.commerciante = commerciante;
        controller = new ControllerCommerciante(commerciante);
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void initChoice() {
        System.out.println("COSA VUOI FARE:");
        System.out.println("(" + VISUALIZZA_PUNTI_VENDITA + ") Visualizza punti vendita");
        System.out.println("(" + AGGIUNGI_PUNTO_VENDITA + ") Aggiungi punto vendita");
        System.out.println("(" + VISUALIZZA_ORDINI_IN_ATTESA + ") Visualizza ordini in attesa");
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
                    System.out.println();
                    break;
                case AGGIUNGI_PUNTO_VENDITA:
                    aggiungiPuntoVenditaView();
                    System.out.println();
                    break;
                case VISUALIZZA_ORDINI_IN_ATTESA:
                    ordiniInAttesaView();
                    System.out.println();
                    break;
                case ABILITA_RITIRO_CONSEGNA:
                    abilitaRitiroView();
                    System.out.println();
                    break;
            }
        }
        while (!line.equals(LOGOUT));
    }

    private void visualizzaConsegneDaAbilitare(){
        System.out.println("CONSEGNE DA ABILITARE PER IL RITIRO:");
        for(int i=0; i<controller.getConsegneDaAbilitareAlRitiro().size();i++){
            System.out.println("-------- CONSEGNA "+i+" --------");
            System.out.println("--------------------------------");
            System.out.println("Id Consegna: "+controller.getConsegneDaAbilitareAlRitiro().get(i).getId());
            System.out.println("Nome Ordine Cliente: "+controller.getConsegneDaAbilitareAlRitiro().get(i).getOrdine().getCliente().getNome()+" "+controller.getConsegneDaAbilitareAlRitiro().get(i).getOrdine().getCliente());
            System.out.println("Email Ordine Cliente: "+controller.getConsegneDaAbilitareAlRitiro().get(i).getOrdine().getCliente().getEmail());
            System.out.println("N. Prodotti Ordinati: "+controller.getConsegneDaAbilitareAlRitiro().get(i).getOrdine().getProdotti().size());
        }
    }

    private void visualizzaDettagliConsegna(Consegna consegna){
        System.out.println("DETTAGLI CONSEGNA:");
        System.out.println("Id Consegna: "+consegna.getId());
        System.out.println("Punto Ritiro: "+consegna.getPuntoRitiro().getIndirizzo());
        System.out.println("Prodotti: ");
        System.out.println("----------");
        for(Prodotto p:consegna.getOrdine().getProdotti()){
            System.out.println(p.getDescrizione()+" "+p.getPrezzo());
        }
        System.out.println("---------");
        System.out.println("TOTALE: "+consegna.getOrdine().getPrice());
    }

    private void visualizzaDettagliOrdine(Ordine ordine){
        System.out.println("DETTAGLI ORDINE:");
        System.out.println("Nominativo Cliente: "+ordine.getCliente().getNome()+" "+ordine.getCliente().getCognome());
        System.out.println("Email Cliente: "+ordine.getCliente().getEmail());
        System.out.println("Prodotti: ");
        System.out.println("----------");
        for(Prodotto p: ordine.getProdotti()){
            System.out.println(p.getDescrizione()+" "+p.getPrezzo());
        }
        System.out.println("---------");
        System.out.println("TOTALE: "+ordine.getPrice());
    }

    private void abilitaRitiroView() throws IOException {
        String line;
        this.visualizzaConsegneDaAbilitare();
        System.out.println("[number+enter per visualizzare una consegna nel dettaglio]");
        System.out.println("["+RETURN+"+enter per tornare indietro]");
        line=br.readLine();
        if (!line.equals(RETURN)) {
            this.visualizzaDettagliConsegna(controller.getConsegneDaAbilitareAlRitiro().get(Integer.parseInt(line)));
            System.out.println();
            System.out.println("Vuoi abilitare la consegna al ritiro?");
            System.out.println("[y+enter per abilitare la consegna al ritiro]");
            System.out.println("[n+enter per annullare l'operazione]");
            String lineChoice =br.readLine();
            if(lineChoice.equals("y")) controller.abilitaRitiro(controller.getConsegneDaAbilitareAlRitiro().get(Integer.parseInt(line)));
        }
    }

    private void visualizzaOrdiniInAttesa(){
        System.out.println("LISTA ORDINI IN ATTESA DI CONFERMA:\n");
        for(int i = 0; i<controller.getOrdini(StatoOrdine.IN_ATTESA).size(); i++){
            System.out.println("-------- ORDINE "+i+" --------");
            System.out.println("Nome Cliente: "+controller.getOrdini(StatoOrdine.IN_ATTESA).get(i).getCliente().getNome()+" "+controller.getOrdini(StatoOrdine.IN_ATTESA).get(i).getCliente().getCognome());
            System.out.println("Email Cliente: "+controller.getOrdini(StatoOrdine.IN_ATTESA).get(i).getCliente().getEmail());
            System.out.println("N. prodotti ordinati: "+controller.getOrdini(StatoOrdine.IN_ATTESA).get(i).getProdotti().size());
        }
    }

    private void visualizzaPuntiVendita(){
        System.out.println("LISTA PUNTI VENDITA:\n");
        for(int i=0; i<controller.getPuntiVendita().size(); i++){
            System.out.println("-------- PUNTO VENDITA "+i+" --------");
            System.out.println("Nome: "+controller.getPuntiVendita().get(i).getNome());
            System.out.println("Posizione: "+controller.getPuntiVendita().get(i).getPosizione());
            System.out.println();
        }
    }

    private PuntoRitiro scegliPuntoRitiroView() throws IOException {
        String line;
        if(controller.getPuntiRitiroDisponibili().size()>0) {
            System.out.println("PUNTI RITIRO DISPONIBILI:");
            System.out.println("-------------------------");
            for (int i = 0; i < controller.getPuntiRitiroDisponibili().size(); i++) {
                System.out.println();
                System.out.println("-------- Punto Ritiro " + i + " --------");
                System.out.println("Posizione: "+controller.getPuntiRitiroDisponibili().get(i).getIndirizzo());
                System.out.println("Slot Disponibili: "+controller.getPuntiRitiroDisponibili().get(i).getSlotDisponibili());
            }
            System.out.println("\n Scegli il punto di ritiro dove verrà effettuata la consegna:");
            System.out.println("[number+enter per scegliere il punto di ritiro]");
            System.out.println("[" + RETURN + "enter per annullare l'operazione]");
            line = br.readLine();
            if (!line.equals(RETURN)) return controller.getPuntiRitiroDisponibili().get(Integer.parseInt(line));
        } else{
            System.out.println("MOMENTANEAMENTE TUTTI I PUNTI DI RITIRO SONO OCCUPATI");
            System.out.println("LA PREGHIAMO DI RIPROVARE PIU TARDI!");
            return null;
        }
        return null;
    }


    private void ordiniInAttesaView() throws IOException {
        String line;
        this.visualizzaOrdiniInAttesa();
        System.out.println("[number+enter per visualizzare un ordine nel dettaglio]");
        System.out.println("["+RETURN+"+enter per tornare indietro]");
        line=br.readLine();
        if (!line.equals(RETURN)) {
            this.visualizzaDettagliOrdine(controller.getOrdini(StatoOrdine.IN_ATTESA).get(Integer.parseInt(line)));
            System.out.println("\nVuoi accettare l'ordine?");
            System.out.println("[y+enter per accettare l'ordine]");
            System.out.println("[n+enter per rifiutare l'ordine]");
            System.out.println("["+RETURN+"+enter per tornare indietro]");
            String lineChoice=br.readLine();
            /*TODO: PROBLEMA ENORME SUL FATTO CHE 50 COMMERCIANTI POTREBBERO SCEGLIERE IL PUNTO DI RITIRO CON UN SOLO POSTO DISPONIBILE
            TODO: E IL PRIMO CHE EFFETTUA LA CONSEGNA OCCUPA IL POSTO E TUTTI GLI DOVREBBERO ASPETTARE MA QUESTO NON E' SEGNALATO DAL SISTEMA
            TODO: SAREBBE MEGLIO METTERE CHE LO SLOT DEL PUNTO DI RITIRO DIMINUISCE QUANDO IL COMMERCIANTE SCEGLIE IL PUNTI DI RITIRO
            TODO: ANCHE SE QUESTO CAUSEREBBE POSTI FANTASMA OCCUPATI PER PARECCHIO TEMPO.
             TODO: NEL MODO IN CUI E' IMPLEMENTATO ORA SI DOVREBBE FARE UN SECONDO CONTROLLO PER IL CORRIERE
             TODO: CHE VEDE SE IL PUNTO RITIRO E' DISPONIBILE E QUINDI SE LA CONSEGNA PUO ESSERE EFFETTUATA.
             TODO: HO AGGIUNTO UNA VOCE IN PIU AL CORRIERE CHE MI FA VEDERE QUANTI SLOT DISPONIBILI HA LO SLOT PRIMA DI
             TODO: SCELIERE LA CONSEGNA, COSI' DECIDE AUTONOMAMENTE DI NON PRENDERE IN CARICO LA CONSEGNA SE GLI SLOT SONO POCHI.
             TODO SE FOSSE STATO IL CORRIERE A SCEGLIERE IL PUNTO DI RITIRO DOVE CONSEGNARE LA MERCE IL PROBLEMA
             TODO: NON ESISTREBBE !
             TODO: IL CORRIERE DEVE SCEGLIERE IL PUNTO DI RITIRO !!!
             */
            if(lineChoice.equals("y")) {
                PuntoRitiro pv =  this.scegliPuntoRitiroView();
                if(!pv.equals(null))controller.accettaOrdine(controller.getOrdini(StatoOrdine.IN_ATTESA).get(Integer.parseInt(line)), pv);
            }
            else if(lineChoice.equals("n")) controller.rifiutaOrdine(controller.getOrdini(StatoOrdine.IN_ATTESA).get(Integer.parseInt(line)));
        }
    }

    private void aggiungiPuntoVenditaView() throws IOException {
        String line;
        System.out.println("Inserisci il nome del punto vendita da creare: ");
        String nameLine = br.readLine();
        System.out.println("Inserisci vie e numero civico (es: Via Prova, 1000) \ndel punto vendita da creare: ");
        String positionLine = br.readLine();
        System.out.println("Vuoi davvero creare il nuovo punto vendita?");
        System.out.println("[y+enter per crearlo]");
        System.out.println("[n+enter per annullare l'operaione]");
        line = br.readLine();
        if(line.equals("y")) controller.addPuntoVendita(nameLine, positionLine);
    }

    private void aggiungiProdotto(PuntoVendita pv) throws IOException {
        String line;
        System.out.println("Inserisci la descrizione del prodotto");
        String descriptionLine= br.readLine();
        System.out.println("Inserisci il prezzo del prodotto");
        String priceLine= br.readLine();
        System.out.println("Sei sicuro di voler aggiungere il prodotto?");
        System.out.println("[y+enter per aggiungere il prodotto]");
        System.out.println("[n+enter per annullare l'operazione]");
        line=br.readLine();
        if(line.equals("y")) controller.addProdotto(pv,descriptionLine, Double.parseDouble(priceLine));
    }

    private void visualizzaProdotti(PuntoVendita pv){
        System.out.println("PRODOTTI VENDUTI:");
        System.out.println("------------------");
        for(int i=0; i<pv.getProdotti().size();i++){
            System.out.println(pv.getProdotti().get(i).getDescrizione());
            System.out.println("Prezzo: "+pv.getProdotti().get(i).getPrezzo());
            System.out.println();
        }
    }

    private void puntoVenditaChoice(PuntoVendita pv) throws IOException {
        String line;
        System.out.println("Nome: "+pv.getNome());
        System.out.println("Posizione: "+pv.getPosizione());
        System.out.println();
        System.out.println(VISUALIZZA_PRODOTTI+") Visualizza Prodotti");
        System.out.println(AGGIUNGI_PRODOTTO+") Aggiungi Prodotto");
        System.out.println(RETURN+") Tornare indietro");
        line= br.readLine();
        switch(line){
            case VISUALIZZA_PRODOTTI:
                this.visualizzaProdotti(pv);
                break;
            case AGGIUNGI_PRODOTTO:
                this.aggiungiProdotto(pv);
                break;
        }
    }

    private void puntiVenditaView() throws IOException {
        String line;
        this.visualizzaPuntiVendita();
        System.out.println("[number+enter per aprire nel dettaglio un punto vendita]");
        System.out.println("["+RETURN+" per tornare indietro]");
        line = br.readLine();
        if(!line.equals(RETURN)) this.puntoVenditaChoice(controller.getPuntiVendita().get(Integer.parseInt(line)));

    }
}
