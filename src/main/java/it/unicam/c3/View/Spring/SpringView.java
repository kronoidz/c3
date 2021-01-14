package it.unicam.c3.View.Spring;

import it.unicam.c3.Anagrafica.Commerciante;
import it.unicam.c3.Anagrafica.Corriere;
import it.unicam.c3.Citta.CentroCittadino;
import it.unicam.c3.Controller.ControllerAutenticazione;
import it.unicam.c3.View.View;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
public class SpringView implements View {
    @SuppressWarnings("RedundantThrows")
    @Override
    public void start() throws IOException {

        MakeTestData(); // todo: DEBUG ONLY

        SpringApplication.run(SpringView.class);
    }

    private void MakeTestData() {
       /* CentroCittadino.getInstance().addCommerciante (
                new Commerciante (
                        "Alessandro",
                        "Pecugi",
                        "alessandro.pecugi@example.com",
                        "ciaone" )
        );

        CentroCittadino.getInstance().addCorriere (
                new Corriere(
                        "Corriere",
                        "Corrieri",
                        "c.corrieri@example.com",
                        "ciaone" )
        );*/
        try {
            ControllerAutenticazione controller = new ControllerAutenticazione();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
