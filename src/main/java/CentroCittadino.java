import java.util.List;

public class CentroCittadino {

    private List<PuntoVendita> puntiVendita;

    public List<PuntoVendita> getPuntiVendita() {
        return puntiVendita;
    }

    public void addPuntoVendita(PuntoVendita puntoVendita) {
        puntiVendita.add(puntoVendita);
    }
}
