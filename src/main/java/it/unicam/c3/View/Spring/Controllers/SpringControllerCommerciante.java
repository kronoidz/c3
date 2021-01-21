package it.unicam.c3.View.Spring.Controllers;

import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Citta.PuntoRitiro;
import it.unicam.c3.Commercio.*;
import it.unicam.c3.Consegne.Consegna;
import it.unicam.c3.Controller.ControllerCommerciante;
import it.unicam.c3.Ordini.GestoreOrdini;
import it.unicam.c3.Ordini.Ordine;
import it.unicam.c3.Ordini.StatoOrdine;
import it.unicam.c3.View.Spring.OffertaGenerica;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("commerciante")
public class SpringControllerCommerciante extends SpringControllerBase {

    private Commerciante commerciante;
    private ControllerCommerciante controller;

    private boolean authorize(HttpSession session) {
        commerciante = getCommerciante(session);
        controller = getControllerCommerciante(session);

        return commerciante != null && controller != null;
    }

    @GetMapping
    public ModelAndView getHomeCommerciante (HttpSession session,
                                             Model model)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        model.addAttribute("emailCommerciante", commerciante.getEmail());
        model.addAttribute("puntiVendita", controller.getPuntiVendita());
        model.addAttribute("ordiniInAttesa",
                controller.getOrdini(StatoOrdine.IN_ATTESA));

        return new ModelAndView("/commerciante/home");
    }

    @GetMapping("puntoVendita")
    public ModelAndView getPuntoVendita (HttpSession session,
                                         Model model,
                                         @RequestParam("id") String id)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        PuntoVendita puntoVendita = controller.getPuntiVendita()
                .stream()
                .filter(pv -> pv.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (puntoVendita == null)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        model.addAttribute("puntoVendita", puntoVendita);
        List<OffertaGenerica> offerte = puntoVendita.getOfferte()
                .stream()
                .filter(o -> !
                            (o instanceof OffertaATempo &&
                            ((OffertaATempo) o).getScadenza().isBefore(LocalDate.now()))
                ) // (non è scaduta)
                .map(OffertaGenerica::new)
                .collect(Collectors.toList());
        model.addAttribute("offerte", offerte);
        return new ModelAndView("/commerciante/puntoVendita");
    }

    @GetMapping("puntoVendita/elimina")
    public ModelAndView eliminaPuntoVendita (HttpSession session,
                                             Model model,
                                             @RequestParam("id") String id)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        PuntoVendita puntoVendita = controller.getPuntiVendita()
                .stream()
                .filter(pv -> pv.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (puntoVendita == null)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        try {
            controller.removePuntoVendita(puntoVendita);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:/commerciante");
    }

    @GetMapping("puntoVendita/eliminaProdotto")
    public ModelAndView eliminaProdotto     (HttpSession session,
                                             Model model,
                                             @RequestParam("idPuntoVendita") String idPuntoVendita,
                                             @RequestParam("idProdotto") String idProdotto)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        PuntoVendita puntoVendita = controller.getPuntiVendita()
                .stream()
                .filter(pv -> pv.getId().equals(idPuntoVendita))
                .findFirst()
                .orElse(null);

        if (puntoVendita == null)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        Prodotto prodotto = puntoVendita.getProdotti()
                .stream()
                .filter(p -> p.getId().equals(idProdotto))
                .findFirst()
                .orElse(null);

        if (prodotto == null)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        puntoVendita.removeProdotto(prodotto);
        return new ModelAndView("redirect:/commerciante/puntoVendita?id=" + idPuntoVendita);
    }

    @GetMapping("puntoVendita/cambiaDisponibilita")
    public ModelAndView cambiaDisponibilitaProdotto (
                                             HttpSession session,
                                             Model model,
                                             @RequestParam("idPuntoVendita") String idPuntoVendita,
                                             @RequestParam("idProdotto") String idProdotto)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        PuntoVendita puntoVendita = controller.getPuntiVendita()
                .stream()
                .filter(pv -> pv.getId().equals(idPuntoVendita))
                .findFirst()
                .orElse(null);

        if (puntoVendita == null)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        Prodotto prodotto = puntoVendita.getProdotti()
                .stream()
                .filter(p -> p.getId().equals(idProdotto))
                .findFirst()
                .orElse(null);

        if (prodotto == null)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        prodotto.setDisponibilita(!prodotto.getDisponibilita());
        return new ModelAndView("redirect:/commerciante/puntoVendita?id=" + idPuntoVendita);
    }

    @GetMapping("puntoVendita/aggiungiProdotto")
    public ModelAndView getAggiungiProdotto (HttpSession session,
                                       Model model,
                                       @RequestParam("idPuntoVendita") String idPuntoVendita)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        PuntoVendita puntoVendita = controller.getPuntiVendita()
                .stream()
                .filter(pv -> pv.getId().equals(idPuntoVendita))
                .findFirst()
                .orElse(null);

        if (puntoVendita == null)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        model.addAttribute("puntoVendita", puntoVendita);
        return new ModelAndView("/commerciante/aggiungiProdotto");
    }

    @PostMapping("puntoVendita/aggiungiProdotto")
    public ModelAndView doAggiungiProdotto  (HttpSession session,
                                             Model model,
                                             @RequestParam("idPuntoVendita") String idPuntoVendita,
                                             @RequestParam("nome") String nome,
                                             @RequestParam("prezzo") double prezzo)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        PuntoVendita puntoVendita = controller.getPuntiVendita()
                .stream()
                .filter(pv -> pv.getId().equals(idPuntoVendita))
                .findFirst()
                .orElse(null);

        if (puntoVendita == null)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        puntoVendita.addProdotto(nome, prezzo);
        return new ModelAndView("redirect:/commerciante/puntoVendita?id=" + idPuntoVendita);
    }

    @GetMapping("puntoVendita/eliminaOfferta")
    public ModelAndView eliminaOfferta   (HttpSession session,
                                          @RequestParam("idPuntoVendita") String idPuntoVendita,
                                          @RequestParam("idOfferta") String idOfferta)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        PuntoVendita puntoVendita = controller.getPuntiVendita()
                .stream()
                .filter(pv -> pv.getId().equals(idPuntoVendita))
                .findFirst()
                .orElse(null);

        if (puntoVendita == null)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        IOfferta offerta = puntoVendita.getOfferte()
                .stream()
                .filter(o -> o.getId().equals(idOfferta))
                .findFirst()
                .orElse(null);

        if (offerta == null)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        puntoVendita.getOfferte().remove(offerta);
        return new ModelAndView("redirect:/commerciante/puntoVendita?id=" + idPuntoVendita);
    }

    @GetMapping("puntoVendita/aggiungiOfferta")
    public ModelAndView getAggiungiOfferta  (HttpSession session,
                                             Model model,
                                             @RequestParam("idPuntoVendita") String idPuntoVendita)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        PuntoVendita puntoVendita = controller.getPuntiVendita()
                .stream()
                .filter(pv -> pv.getId().equals(idPuntoVendita))
                .findFirst()
                .orElse(null);

        if (puntoVendita == null)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        model.addAttribute("puntoVendita", puntoVendita);
        return new ModelAndView("/commerciante/aggiungiOfferta");
    }

    @PostMapping("puntoVendita/aggiungiOfferta")
    public ModelAndView doAggiungiOfferta  (HttpSession session,
                                            Model model,
                                            @RequestParam("idPuntoVendita") String idPuntoVendita,
                                            @RequestParam("descrizione") String descrizione,
                                            @RequestParam("importo") String importo,
                                            @RequestParam(value = "scadenza", required = false) String scadenza)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        PuntoVendita puntoVendita = controller.getPuntiVendita()
                .stream()
                .filter(pv -> pv.getId().equals(idPuntoVendita))
                .findFirst()
                .orElse(null);

        if (puntoVendita == null)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        if (scadenza != null && !scadenza.isEmpty()) {
            LocalDate date = LocalDate.parse(scadenza, DateTimeFormatter.ISO_DATE_TIME);
            puntoVendita.addOfferta(descrizione, importo, date);
        }
        else {
            puntoVendita.addOfferta(descrizione, importo);
        }

        return new ModelAndView("redirect:/commerciante/puntoVendita?id=" + idPuntoVendita);
    }

    @GetMapping("ordini")
    public ModelAndView getOrdini (HttpSession session,
                                   Model model,
                                   @RequestParam(value = "stato", required = false) String stato)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        List<Ordine> ordini;

        if (stato != null && !stato.isEmpty() && !stato.equals("QUALSIASI")) {
            ordini = controller.getOrdini(StatoOrdine.valueOf(stato));
        }
        else ordini = controller.getOrdini();

        model.addAttribute("ordini", ordini);
        model.addAttribute("filtro", stato);
        return new ModelAndView("/commerciante/ordini");
    }

    @GetMapping("ordine")
    public ModelAndView getOrdine (HttpSession session,
                                   Model model,
                                   @RequestParam("id") String id)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        List<Ordine> ordini = GestoreOrdini.getInstance().getOrdini(commerciante);
        Ordine ordine = ordini.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (ordine == null)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        model.addAttribute("ordine", ordine);

        if (ordine.getStato() == StatoOrdine.IN_ATTESA) {
            model.addAttribute("puntiRitiro", controller.getPuntiRitiroDisponibili());
        }

        return new ModelAndView("/commerciante/ordine");
    }

    @GetMapping("ordine/accetta")
    public ModelAndView accettaOrdine(HttpSession session,
                                      Model model,
                                      @RequestParam("id") String id,
                                      @RequestParam("idPuntoRitiro") String idPuntoRitiro)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        List<Ordine> ordini = GestoreOrdini.getInstance().getOrdini(commerciante);
        Ordine ordine = ordini.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (ordine == null)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        PuntoRitiro puntoRitiro = CentroCittadino.getInstance().getPuntiRitiro()
                .stream()
                .filter(pr -> pr.getId().equals(idPuntoRitiro))
                .findFirst()
                .orElse(null);

        if (puntoRitiro == null)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        controller.accettaOrdine(ordine, puntoRitiro);
        return new ModelAndView("redirect:/commerciante/ordine?id=" + ordine.getId());
    }

    @GetMapping("ordine/rifiuta")
    public ModelAndView rifiutaOrdine(HttpSession session,
                                      Model model,
                                      @RequestParam("id") String id)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        List<Ordine> ordini = GestoreOrdini.getInstance().getOrdini(commerciante);
        Ordine ordine = ordini.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (ordine == null)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        controller.rifiutaOrdine(ordine);
        return new ModelAndView("redirect:/commerciante/ordine?id=" + ordine.getId());
    }

    @GetMapping("aggiungiPuntoVendita")
    public ModelAndView getAggiungiPuntoVendita(HttpSession session, Model model) {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        model.addAttribute("commerciante", commerciante);
        return new ModelAndView("commerciante/aggiungiPuntoVendita");
    }

    @PostMapping("aggiungiPuntoVendita")
    public ModelAndView doAggiungiPuntoVendita(HttpSession session,
                                               @RequestParam("nome") String nome,
                                               @RequestParam("posizione") String posizione)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        try {
            controller.addPuntoVendita(posizione, nome);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:/commerciante");
    }

    @GetMapping("abilitaRitiroConsegna")
    public ModelAndView getAbilitaRitiroConsegna(HttpSession session, Model model) {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        List<Consegna> consegne = controller.getConsegneDaAbilitareAlRitiro();
        model.addAttribute("consegne", consegne);
        return new ModelAndView("/commerciante/abilitaRitiroConsegna");
    }

    @PostMapping("abilitaRitiroConsegna")
    public ModelAndView doAbilitaRitiroConsegna(HttpSession session,
                                                @RequestParam("id") String id)
    {
        if (!authorize(session))
            return new ModelAndView("redirect:/auth", HttpStatus.UNAUTHORIZED);

        List<Consegna> consegne = controller.getConsegneDaAbilitareAlRitiro();
        Consegna consegna = consegne.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (consegna == null)
            return new ModelAndView("/not-found", HttpStatus.NOT_FOUND);

        controller.abilitaRitiro(consegna);
        return new ModelAndView("redirect:/commerciante/abilitaRitiroConsegna");
    }
}
