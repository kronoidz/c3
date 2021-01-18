import it.unicam.c3.View.Console.ConsoleView;
import it.unicam.c3.View.Spring.SpringView;
import it.unicam.c3.View.View;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;

public class C3 {

    public static void main(String[] args)
            throws MessagingException, IOException, SQLException
    {
        View view;

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("console"))
                view = new ConsoleView();

            else if (args[0].equalsIgnoreCase("spring"))
                view = new SpringView();

            else {
                System.err.println("Argument " + args[0] + " is invalid");
                return;
            }
        }
        else view = new SpringView(); // Default view

        view.start();
    }

}
