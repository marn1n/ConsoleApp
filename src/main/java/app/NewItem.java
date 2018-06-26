package app;

import java.util.Scanner;

public class NewItem {
    private DBUtilities dbUtilities = new DBUtilities();
    NewItem() {
        Scanner input = new Scanner(System.in);
        if (input.next().equals("add")) {
            String insert = "insert into items (date, sum, currency, item) VALUES ('" +
                    input.next() + "', '" + input.next() + "', '" + input.next() + "', '" +
                    input.next() + "')";

            dbUtilities.executeStatement(insert);
        } else {
            System.out.println("Incorrect input");
        }
        Main.display();
    }
}
