package it.unicam.c3.View.Spring.Controllers;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Anagrafica.Utente;
import it.unicam.c3.Controller.ControllerAutenticazione;
import it.unicam.c3.Controller.ControllerCliente;
import it.unicam.c3.Controller.ControllerCommerciante;
import it.unicam.c3.Controller.ControllerCorriere;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class SpringControllerAutenticazione {

    @GetMapping("auth")
    public String getAuth() {
        return "auth";
    }

    @PostMapping("auth")
    public ModelAndView doAuth (HttpSession session,
                                Model model,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password,
                                @RequestParam("tipoUtente") String tipoUtente)
    {
        ControllerAutenticazione auth = null;

        try {
            auth = new ControllerAutenticazione();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            model.addAttribute("error", "Errore database");
            return new ModelAndView("/auth", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Utente utente = null;
        Object controller = null;
        try {
            switch (tipoUtente) {
                case "cliente":
                    utente = auth.autenticaCliente(email, password);
                    if (utente != null)
                        controller = new ControllerCliente((Cliente) utente);
                    break;
                case "commerciante":
                    utente = auth.autenticaCommerciante(email, password);
                    if (utente != null) {
                        controller = new ControllerCommerciante((Commerciante) utente);
                    }
                    break;
                case "corriere":
                    utente = auth.autenticaCorriere(email, password);
                    if (utente != null)
                        controller = new ControllerCorriere((Corriere) utente);
                    break;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            model.addAttribute("error", "Errore database");
            return new ModelAndView("/auth", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (controller == null) {
            model.addAttribute("error", "Credenziali invalide");
            return new ModelAndView("/auth", HttpStatus.UNAUTHORIZED);
        }

        session.setAttribute("utente", utente);
        session.setAttribute("controller", controller);

        return new ModelAndView("redirect:/" + tipoUtente);
    }

    @GetMapping("/registra")
    public String getRegistra() { return "/registrazione"; }

    @PostMapping("/registra")
    public ModelAndView postRegistra (Model model,
                                      @RequestParam("nome") String nome,
                                      @RequestParam("cognome") String cognome,
                                      @RequestParam("email") String email,
                                      @RequestParam("password") String password,
                                      @RequestParam("tipoUtente") String tipoUtente)
    {
        ControllerAutenticazione auth = null;

        try {
            auth = new ControllerAutenticazione();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            model.addAttribute("error", "Errore database");
            return new ModelAndView("/registrazione", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ControllerAutenticazione.TipoUtente tipo;

        switch (tipoUtente) {
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
                return new ModelAndView("/registrazione", HttpStatus.BAD_REQUEST);
        }

        try {
            auth.registra ( nome,
                            cognome,
                            email,
                            password,
                            tipo );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            model.addAttribute("error", "Errore database");
            return new ModelAndView("/registrazione", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        model.addAttribute("success",
                String.format("Utente %s %s iscritto", nome, cognome));
        return new ModelAndView("/registrazione");
    }

    @GetMapping("/disconnetti")
    public String disconnetti(HttpSession session) {
        session.removeAttribute("utente");
        session.removeAttribute("controller");
        return "redirect:/auth";
    }
}
