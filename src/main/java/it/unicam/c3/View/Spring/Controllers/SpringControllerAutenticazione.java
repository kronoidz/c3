package it.unicam.c3.View.Spring.Controllers;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Anagrafica.Utente;
import it.unicam.c3.Controller.ControllerAutenticazione;
import it.unicam.c3.Controller.ControllerCliente;
import it.unicam.c3.Controller.ControllerCommerciante;
import it.unicam.c3.Controller.ControllerCorriere;
import it.unicam.c3.View.Spring.FormModels.Credenziali;
import it.unicam.c3.View.Spring.FormModels.DatiRegistrazione;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class SpringControllerAutenticazione {

    private static final String TEMPLATE_AUTH = "auth";
    private static final String TEMPLATE_REG = "registrazione";

    @GetMapping("/auth")
    public String GetAuthForm() {
        return "auth";
    }

    @PostMapping("/auth")
    public ModelAndView DoAuth(HttpSession session, Credenziali credenziali, Model model) {
        ControllerAutenticazione auth = null;

        try {
            auth = new ControllerAutenticazione();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            model.addAttribute("error", "Errore database");
            return new ModelAndView(TEMPLATE_AUTH, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String email = credenziali.getEmail();
        String pwd = credenziali.getPassword();

        Utente utente = null;
        Object controller = null;
        try {
            switch (credenziali.getTipoUtente()) {
                case "cliente":
                    utente = auth.autenticaCliente(email, pwd);
                    if (utente != null)
                        controller = new ControllerCliente((Cliente) utente);
                    break;
                case "commerciante":
                    utente = auth.autenticaCommerciante(email, pwd);
                    if (utente != null) {
                        controller = new ControllerCommerciante((Commerciante) utente);
                    }
                    break;
                case "corriere":
                    utente = auth.autenticaCorriere(email, pwd);
                    if (utente != null)
                        controller = new ControllerCorriere((Corriere) utente);
                    break;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            model.addAttribute("error", "Errore database");
            return new ModelAndView(TEMPLATE_AUTH, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (controller == null) {
            model.addAttribute("error", "Credenziali invalide");
            return new ModelAndView(TEMPLATE_AUTH, HttpStatus.UNAUTHORIZED);
        }

        session.setAttribute("utente", utente);
        session.setAttribute("controller", controller);

        return new ModelAndView("redirect:/" + credenziali.getTipoUtente());
    }

    @GetMapping("/registra")
    public String getRegistra() { return TEMPLATE_REG; }

    @PostMapping("/registra")
    public ModelAndView postRegistra(Model model, DatiRegistrazione data) {
        ControllerAutenticazione auth = null;

        try {
            auth = new ControllerAutenticazione();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            model.addAttribute("error", "Errore database");
            return new ModelAndView(TEMPLATE_REG, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ControllerAutenticazione.TipoUtente tipo;

        switch (data.getTipoUtente()) {
            case "cliente":
                tipo = ControllerAutenticazione.TipoUtente.CLIENTE;
                break;
            case "commerciante":
                tipo = ControllerAutenticazione.TipoUtente.COMMERCIANTE;
                break;
            case "corriere":
                tipo = ControllerAutenticazione.TipoUtente.CORRIERE;
                break;
            default:
                model.addAttribute("error", "Tipo utente non valido");
                return new ModelAndView(TEMPLATE_REG, HttpStatus.BAD_REQUEST);
        }

        try {
            auth.registra ( data.getNome(),
                            data.getCognome(),
                            data.getEmail(),
                            data.getPassword(),
                            tipo );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            model.addAttribute("error", "Errore database");
            return new ModelAndView(TEMPLATE_REG, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        model.addAttribute("success",
                String.format("Utente %s %s iscritto",
                        data.getNome(), data.getCognome()));
        return new ModelAndView(TEMPLATE_REG);
    }
}
