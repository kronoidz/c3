package it.unicam.c3.View.Console;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Controller.ControllerAutenticazione;
import it.unicam.c3.Controller.ControllerGestore;
import it.unicam.c3.View.View;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class ConsoleView implements View {
    private static final String ACCOUNT_CLIENTE="1";
    private static final String ACCOUNT_COMMERCIANTE="2";
    private static final String ACCOUNT_CORRIERE="3";
    private static final String AMMINISTRAZIONE="4";
    private static final String REGISTRAZIONE="1";
    private static final String LOGIN="2";
    private static final String CLOSE_APPLICATION="exit";
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private ControllerAutenticazione controllerAutenticazione;
    private ControllerGestore controllerGestore;
    ConsoleAccountCliente clienteView;
    ConsoleAccountCorriere corriereView;
    ConsoleAccountCommerciante commercianteView;
    ConsoleAmministrazione amministrazioneView;

    public ConsoleView() throws SQLException {
        controllerAutenticazione= new ControllerAutenticazione();
        controllerGestore=new ControllerGestore();
    }

    @Override
    public void start() throws IOException, MessagingException, SQLException {
        String line;
        do {
            choiceInit();
            line= br.readLine();
            choiceLoginOrRegistration(line);
        }while(!line.equals(CLOSE_APPLICATION));
        br.close();
    }

    private void choiceInit(){
        System.out.println("SCEGLI ACCOUNT:");
        System.out.println(ACCOUNT_CLIENTE+") Account Cliente");
        System.out.println(ACCOUNT_COMMERCIANTE+") Account Commerciante");
        System.out.println(ACCOUNT_CORRIERE+") Account Corriere");
        System.out.println(AMMINISTRAZIONE+") Area Amministrazione");
        System.out.println("\n"+CLOSE_APPLICATION+") CLOSE APPLICATION");
    }

    private void choiceLoginOrRegistration(String tipo) throws IOException, SQLException, MessagingException {
        String line;
        System.out.println(REGISTRAZIONE + ") Registrazione");
        System.out.println(LOGIN + ") Login");
        line = br.readLine();
        switch (line) {
            case REGISTRAZIONE:
                registration(tipo);
                break;
            case LOGIN:
                System.out.println("Inserisci una e-mail:");
                String email = br.readLine();
                System.out.println("Inserisci una password:");
                String password = br.readLine();
                switch (tipo) {
                    case ACCOUNT_CLIENTE:
                        if(this.autenticaCliente(email, password)!=null){
                            clienteView = new ConsoleAccountCliente(this.autenticaCliente(email,password));
                            clienteView.clienteView();
                        }else System.out.println("AUTENTICAZIONE FALLITA!");
                        break;
                    case ACCOUNT_COMMERCIANTE:
                        if(this.autenticaCommerciante(email, password)!=null){
                            commercianteView = new ConsoleAccountCommerciante(this.autenticaCommerciante(email,password));
                            commercianteView.commercianteView();
                        }else System.out.println("AUTENTICAZIONE FALLITA!");
                        break;
                    case ACCOUNT_CORRIERE:
                        if(this.autenticaCorriere(email, password)!=null){
                            corriereView = new ConsoleAccountCorriere(this.autenticaCorriere(email,password));
                            corriereView.corriereView();
                        }else System.out.println("AUTENTICAZIONE FALLITA!");
                        break;
                  /*  case AMMINISTRAZIONE:
                        if(controllerGestore.autorizza()){
                            corriereView = new ConsoleAccountCorriere(this.autenticaCorriere(email,password));
                            corriereView.corriereView();
                        }else System.out.println("AUTENTICAZIONE FALLITA!");
                        break; *///TODO: LOGIN AMMINISTRAZIONE !!!
                }
                break;

        }
    }

    private void registration(String tipo) {
        String name;
        String cognome;
        String mail;
        String password;
        try {
            System.out.println("Inserisci nome:");
            name = br.readLine();
            System.out.println("Inserisci cognome:");
            cognome = br.readLine();
            System.out.println("Inserisci una e-mail:");
            mail = br.readLine();
            System.out.println("Inserisci una password:");
            password = br.readLine();
            try {
                switch (tipo) {
                    case ACCOUNT_CLIENTE:
                        this.controllerAutenticazione.registra(name, cognome, mail, password, ControllerAutenticazione.TipoUtente.CLIENTE);
                        break;
                    case ACCOUNT_COMMERCIANTE:
                        this.controllerAutenticazione.registra(name, cognome, mail, password, ControllerAutenticazione.TipoUtente.COMMERCIANTE);
                        break;
                    case ACCOUNT_CORRIERE:
                        this.controllerAutenticazione.registra(name, cognome, mail, password, ControllerAutenticazione.TipoUtente.CORRIERE);
                        break;
                }
            }catch (SQLException e){
                System.out.println("ERROR: ERRORE DATABASE!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Cliente autenticaCliente(String email, String password){
       return this.controllerAutenticazione.autenticaCliente(email, password);
    }

    private Commerciante autenticaCommerciante(String email, String password){
        return this.controllerAutenticazione.autenticaCommerciante(email,password);
    }

    private Corriere autenticaCorriere(String email, String password){
        return this.controllerAutenticazione.autenticaCorriere(email,password);
    }




}
