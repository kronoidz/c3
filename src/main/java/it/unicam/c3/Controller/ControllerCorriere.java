package it.unicam.c3.Controller;

import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Consegne.Consegna;
import it.unicam.c3.Consegne.GestoreConsegne;
import it.unicam.c3.Consegne.StatoConsegna;

import javax.mail.MessagingException;
import java.util.List;

public class ControllerCorriere {
    private Corriere corriere;
    private ControllerEmail infoEmail;

    public ControllerCorriere(Corriere corriere){
        this.corriere=corriere;
        this.infoEmail=new ControllerEmail();
    }

    /**
     *
     * @return lista delle consegne pronte per essere consegnate
     */
    public List<Consegna> getConsegneInAttesa(){
        return GestoreConsegne.getInstance().getConsegne(StatoConsegna.IN_ATTESA);
    }

    /**
     *
     * @return lista delle consegne prese in carico
     */
    public List<Consegna> getConsegneInCarico(){
        return GestoreConsegne.getInstance().getConsegnePreseInCaricoDa(this.corriere);
    }

    /**
     * Utilizzato per prendere in carico una consegna
     * @param consegna
     */
    public void prendiInCarico(Consegna consegna) throws Exception {
        GestoreConsegne.getInstance().prendiInCaricoConsegna(consegna,this.corriere);
    }

    /**
     * Utilizzato per prendere in carico una consegna
     * @param indexConsegna
     */
    public void prendiInCarico(int indexConsegna) throws Exception {
       this.prendiInCarico(this.getConsegneInAttesa().get(indexConsegna));
    }

    /**
     * Utilizzato quando la consegna è stata portata a termine
     * @param consegna
     */
    public void effettuaConsegna(Consegna consegna) throws Exception {
        GestoreConsegne.getInstance().consegnaEffettuata(consegna,this.corriere);
        infoEmail.sendEmail(consegna.getOrdine().getCliente().getEmail(), "Consegna effettuata", "Si informa che la sua consegna \u00E8 stata effettuata presso un punto di ritiro.\nAppena l'ordine verr\u00E0 pagato presso il punto vendita"+
        ", sar\u00E0 disponibile per il ritiro.\nIl punto di ritiro e l'id di sblocco verranno resi disponibili " +
                "successivamente al pagamento dell'ordine\n\nPunto Vendita: "+consegna.getOrdine().getPuntoVendita().getNome()+" sito in "+consegna.getOrdine().getPuntoVendita().getPosizione()+"\n\nNB: Questa mail \u00E8 generata da un sistema automatico non presidiato pertanto si invita cortesemente a non rispondere - eventuali email ricevute rimarranno inevase.\n" +
                "\nGrazie e cordiali saluti.\nC3 Team System");
    }

    /**
     * Utilizzato quando la consegna è stata portata a termine
     * @param indexConsegna
     */
    public void effettuaConsegna(int indexConsegna) throws Exception {
        this.effettuaConsegna(this.getConsegneInCarico().get(indexConsegna));
    }

    public void annullaPresaInCarico(Consegna consegna){
        GestoreConsegne.getInstance().annullaPresaInCarico(consegna,this.corriere);
    }

    public void annullaPresaInCarico(int indexConsegna){
        this.annullaPresaInCarico(this.getConsegneInCarico().get(indexConsegna));
    }

}
