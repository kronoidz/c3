package it.unicam.c3.View;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;

public interface View {
    void start() throws IOException, MessagingException, SQLException;
}
