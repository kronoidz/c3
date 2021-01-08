package it.unicam.c3.View.Spring.Controllers;

import it.unicam.c3.View.Spring.FormBeans.AutenticaAmministratoreRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Controller
public class SpringControllerAutenticazione {

    @GetMapping("/auth")
    public String GetAuthForm() {
        return "auth";
    }

    @PostMapping("/auth-admin")
    public String AutenticaAmministratore(AutenticaAmministratoreRequest request)
    {
        throw new NotImplementedException();
    }
}
