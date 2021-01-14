package it.unicam.c3.View.Spring.Controllers;

import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Controller.ControllerCommerciante;
import it.unicam.c3.Ordini.GestoreOrdini;
import it.unicam.c3.Ordini.Ordine;
import it.unicam.c3.Ordini.StatoOrdine;
import it.unicam.c3.View.Spring.FormModels.CommercianteOrdineInAttesa;
import it.unicam.c3.View.Spring.FormModels.CommerciantePuntoVendita;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/commerciante")
public class SpringControllerCommerciante extends SpringControllerBase {

    private Commerciante commerciante;
    private ControllerCommerciante controller;

    private boolean authorize(HttpSession session) {
        commerciante = getCommerciante(session);
        controller = getControllerCommerciante(session);
        return commerciante != null && controller != null;
    }

    @GetMapping
    public ModelAndView GetHomeCommerciante (HttpSession session,
                                             Model model)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        model.addAttribute("emailCommerciante", commerciante.getEmail());
        model.addAttribute("puntiVendita",
                controller.getPuntiVendita()
                        .stream()
                        .map(CommerciantePuntoVendita::new)
                        .collect(Collectors.toList())
        );
        model.addAttribute("ordiniInAttesa",
                controller.getOrdini(StatoOrdine.IN_ATTESA)
                        .stream()
                        .map(CommercianteOrdineInAttesa::new)
                        .collect(Collectors.toList())
        );

        return new ModelAndView("commerciante-home");
    }

    @GetMapping("/ordine")
    public ModelAndView GetOrdine (HttpSession session,
                                   Model model,
                                   @RequestParam("id") String id)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        List<Ordine> ordini = GestoreOrdini.getInstance().getOrdini(commerciante);
        ordini = ordini.stream()
                .filter(ordine -> ordine.getId().equals(id))
                .collect(Collectors.toList());

        if (ordini.size() == 0)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        model.addAttribute("ordine", ordini.get(0));

        if (ordini.get(0).getStato() == StatoOrdine.IN_ATTESA) {
            model.addAttribute("puntiRitiro", CentroCittadino.getInstance()
                    .getPuntiRitiro().stream()
                    .filter(puntoRitiro -> puntoRitiro.getSlotDisponibili() > 0)
                    .collect(Collectors.toList()));
        }

        return new ModelAndView("commerciante-ordine");
    }
}
