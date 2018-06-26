package app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class ListItems {
    private DBUtilities dbUtilities = new DBUtilities();
    ListItems() {
        list();
    }
    private void list() {
        try {
            Scanner input = new Scanner(System.in);
            if (input.next().equals("list")) {
                String select = "select * from items order by date desc";
                ResultSet list = dbUtilities.read(select);
                Date previous = null;

                if (list.next()) {
                    do {
                        Date current = list.getDate(2);
                        int sum = list.getInt(3);
                        String currency = list.getString(4);
                        String item = list.getString(5);

                        if (current.equals(previous)) {
                            System.out.println(item + " " + sum + " " + currency);
                        } else {
                            System.out.println(current);
                            System.out.println(item + " " + sum + " " + currency);
                        }

                        previous = current;
                    } while (list.next());

                } else {
                    System.out.println("No records");
                }
                dbUtilities.disconnect();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Main.display();
        }
    }
}
