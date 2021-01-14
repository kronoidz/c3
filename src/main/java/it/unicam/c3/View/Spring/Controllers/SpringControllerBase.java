package it.unicam.c3.View.Spring.Controllers;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Controller.ControllerCliente;
import it.unicam.c3.Controller.ControllerCommerciante;
import it.unicam.c3.Controller.ControllerCorriere;

import javax.servlet.http.HttpSession;

public class SpringControllerBase {

    // Attenzione: metodo orrendo e pericoloso, lasciare privato
    @SuppressWarnings("unchecked")
    private static <T> T getSessionAttribute (
            HttpSession session,
            String attribute,
            Class<T> type )
    {
        Object o = session.getAttribute(attribute);
        if (!type.isInstance(o)) return null;
        return (T)o;
    }
    
    protected static Commerciante getCommerciante(HttpSession session)
    {
        return getSessionAttribute (
                session,
                "utente",
                Commerciante.class );
    }

    protected static Cliente getCliente(HttpSession session)
    {
        return getSessionAttribute (
                session,
                "utente",
                Cliente.class );
    }

    protected static Corriere getCorriere(HttpSession session)
    {
        return getSessionAttribute (
                session,
                "utente",
                Corriere.class );
    }

    protected static ControllerCommerciante getControllerCommerciante(HttpSession session)
    {
        return getSessionAttribute (
                session,
                "controller",
                ControllerCommerciante.class );
    }

    protected static ControllerCliente getControllerCliente(HttpSession session)
    {
        return getSessionAttribute (
                session,
                "controller",
                ControllerCliente.class );
    }

    protected static ControllerCorriere getControllerCorriere(HttpSession session)
    {
        return getSessionAttribute (
                session,
                "controller",
                ControllerCorriere.class );
    }
}
