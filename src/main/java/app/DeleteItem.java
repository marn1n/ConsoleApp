package app;

import java.util.Scanner;

public class DeleteItem {
    private DBUtilities dbUtilities = new DBUtilities();

    DeleteItem() {
        Scanner input = new Scanner(System.in);
        if (input.next().equals("clear")){
            String delete = "delete from items where date like " + "'" + input.next() + "'";
            dbUtilities.executeStatement(delete);
        } else {
            System.out.println("Incorrect input");
        }
        Main.display();
    }
}
