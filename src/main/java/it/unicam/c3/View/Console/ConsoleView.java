package it.unicam.c3.View.Console;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.View.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView implements View {
    private static final String ACCOUNT_CLIENTE="1";
    private static final String ACCOUNT_COMMERCIANTE="2";
    private static final String ACCOUNT_CORRIERE="3";
    private static final String CLOSE_APPLICATION="exit";
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ConsoleAccountCliente clienteView;
    ConsoleAccountCorriere corriereView;
    ConsoleAccountCommerciante commercianteView;

    //////////OGGETTI TEST CONSOLE !!
   private Cliente cliente = new Cliente("Lorenzo","Serini","lorenzose.1995@gmai.com","prova");
   private Corriere corriere = new Corriere("Jhon","Doe","jn@gmail.com","prova");
   private Commerciante commerciante = new Commerciante("Alessandro", "Pecugi", "alessandro.pecugi@gmail.com", "ciao");
    ///////////////////////////


    public ConsoleView(){
    }

    @Override
    public void start() throws IOException {
        String line;
        do {
            choiceLogin();
            line= br.readLine();
            switch(line){
                case ACCOUNT_CLIENTE:
                    clienteView = new ConsoleAccountCliente(cliente);
                    clienteView.clienteView();
                    break;
                case ACCOUNT_COMMERCIANTE:
                    commercianteView = new ConsoleAccountCommerciante(commerciante);
                    commercianteView.commercianteView();
                    break;
                case ACCOUNT_CORRIERE:
                    corriereView=new ConsoleAccountCorriere(corriere);
                    corriereView.corriereView();
                    break;
            }
        }while(!line.equals(CLOSE_APPLICATION));
        // TODO: br.close();
    }

    private void choiceLogin(){
        System.out.println("SCEGLI ACCOUNT:");
        System.out.println(ACCOUNT_CLIENTE+") Account Cliente");
        System.out.println(ACCOUNT_COMMERCIANTE+") Account Commerciante");
        System.out.println(ACCOUNT_CORRIERE+") Account Corriere");
        System.out.println("\n"+CLOSE_APPLICATION+") CLOSE APPLICATION");
    }



}
