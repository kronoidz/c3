/*******************************************************************************
 * MIT License

 * Copyright (c) 2021 Lorenzo Serini and Alessandro Pecugi
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
/**
 *
 */

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
        } catch (Exception exception) {
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
    } catch (Exception e) {
        e.printStackTrace();
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
