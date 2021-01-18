package it.unicam.c3.View.Spring;

import it.unicam.c3.View.View;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SpringView implements View {

    @SuppressWarnings("RedundantThrows")
    @Override
    public void start() throws IOException {
        SpringApplication.run(SpringView.class);
    }

}
