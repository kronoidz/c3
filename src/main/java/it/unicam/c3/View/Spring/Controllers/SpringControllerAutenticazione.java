package it.unicam.c3.View.Spring.Controllers;

import it.unicam.c3.Controller.ControllerAutenticazione;
import it.unicam.c3.View.Spring.FormModels.Credenziali;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class SpringControllerAutenticazione {

    @GetMapping("/auth")
    public String GetAuthForm() {
        return "auth";
    }

    @PostMapping( value = "/do-auth")
    public ModelAndView DoAuth(HttpSession session,
                               Credenziali credenziali,
                               BindingResult result,
                               Model model ) throws SQLException {
        if (result.hasErrors()) {
            model.addAttribute("error", "Invalid request");
            return new ModelAndView("/auth", HttpStatus.BAD_REQUEST);
        }
        else {
            ControllerAutenticazione auth;
            if (session.isNew()) {
                auth = new ControllerAutenticazione();
                session.setAttribute("auth", auth);
            }
            else auth = (ControllerAutenticazione) session.getAttribute("auth");

            String email = credenziali.getEmail();
            String pwd = credenziali.getPassword();

            Object controller = null;

            switch (credenziali.getTipoUtente()) {
                case "cliente":
                  controller = auth.autenticaCliente(email, pwd);
                  break;
                case "commerciante":
                    controller = auth.autenticaCommerciante(email, pwd);
                    break;
                case "corriere":
                    controller = auth.autenticaCorriere(email, pwd);
                    break;
            }

            session.setAttribute("controller", controller);

            if (controller == null) {
                model.addAttribute("error", "Credenziali invalide");
                return new ModelAndView("/auth", HttpStatus.UNAUTHORIZED);
            }

            return new ModelAndView("/home-" + credenziali.getTipoUtente());
        }
    }
}
