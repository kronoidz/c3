package it.unicam.c3.View;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Corriere;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView implements View{
    private static final String ACCOUNT_CLIENTE="1";
    private static final String ACCOUNT_COMMERCIANTE="2";
    private static final String ACCOUNT_CORRIERE="3";
    private static final String CLOSE_APPLICATION="exit";
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ConsoleAccountCliente clienteView;
    ConsoleAccountCorriere corriereView;

    //////////OGGETTI TEST CONSOLE !!
   private Cliente cliente = new Cliente("Lorenzo","Serini","lorenzose.1995@gmai.com","prova");
   private Corriere corriere = new Corriere("Jhon","Doe","jn@gmail.com","prova");
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
                    System.out.println("STAY TUNED");
                    break;
                case ACCOUNT_CORRIERE:
                    corriereView=new ConsoleAccountCorriere(corriere);
                    corriereView.corriereView();
                    break;
            }
        }while(!line.equals(CLOSE_APPLICATION));
      //  br.close();
    }

    private void choiceLogin(){
        System.out.println("SCEGLI ACCOUNT:");
        System.out.println(ACCOUNT_CLIENTE+") Account Cliente");
        System.out.println(ACCOUNT_COMMERCIANTE+") Account Commerciante");
        System.out.println(ACCOUNT_CORRIERE+") Account Corriere");
        System.out.println("\n"+CLOSE_APPLICATION+") CLOSE APPLICATION");
    }



}
