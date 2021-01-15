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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class SpringControllerAutenticazione {

    @GetMapping("/auth")
    public String GetAuthForm() {
        return "auth";
    }

    @PostMapping("/auth")
    public ModelAndView DoAuth(HttpSession session, Credenziali credenziali, Model model)
    {
        ControllerAutenticazione auth = null;

        try {
            auth = new ControllerAutenticazione();
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("ERROR: ERRORE DATABASE!");
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
        System.out.println("ERROR: ERRORE DATABASE!");
    }
        if (controller == null) {
            model.addAttribute("error", "Credenziali invalide");
            return new ModelAndView("/auth", HttpStatus.UNAUTHORIZED);
        }

        session.setAttribute("utente", utente);
        session.setAttribute("controller", controller);

        return new ModelAndView("redirect:/" + credenziali.getTipoUtente());
    }
}
