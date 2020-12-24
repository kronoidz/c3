package it.unicam.c3.Controller;

import it.unicam.c3.Anagrafica.Cliente;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Commercio.Prodotto;
import it.unicam.c3.Commercio.PuntoVendita;
import it.unicam.c3.Ordini.GestoreOrdini;

import java.util.*;

public class ControllerCliente {
    private Cliente cliente;
    private List<Prodotto> carrello;

    public ControllerCliente(Cliente cliente){
        this.cliente=cliente;
        this.carrello=new LinkedList<>();
    }

}
