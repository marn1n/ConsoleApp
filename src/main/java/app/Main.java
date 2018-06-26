package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        display();
    }
    public static void display() {
        Scanner input = new Scanner(System.in);

        System.out.println("***************************************************************************");
        System.out.println("                      PERSONAL EXPENSES MANAGEMENT APP                     ");
        System.out.println("***************************************************************************");
        System.out.println("For adding an item press 1 and type 'add /date/ /sum/ EUR /item/'");
        System.out.println("For listing all items press 2 and type 'list'");
        System.out.println("For deleting items for a specific day press 3 and type 'clear /date/'");
        System.out.println("For checking total amount in any currency press 4 and type 'total /currency/'");
        System.out.println("Press 5 for exit");
        System.out.println();

        switch (input.next()) {
            case "1":
                NewItem newItem = new NewItem();
                break;
            case "2":
                ListItems listItems = new ListItems();
                break;
            case "3":
                DeleteItem deleteItem = new DeleteItem();
                break;
            case "4":
                TotalPLN totalPLN = new TotalPLN();
                break;
            case "5":
                System.exit(0);
                break;
            default:
                System.out.println("Incorrect input");
                display();
                break;
        }
    }
}
