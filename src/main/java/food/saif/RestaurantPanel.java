package food.saif;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Scanner;

public class RestaurantPanel {
    static Scanner input = new Scanner(System.in);
    static Restaurant restaurant;
    public static void main(String[] args) {

    }
    public static void printHr() {
        System.out.println("============================");
    }
    public static void loginPage() {
        System.out.println("\n# Login Page");
        System.out.println("1) Sign in");
        System.out.println("2) Sign up");
        System.out.println("99) <<");
        System.out.println("=> ");
        int choice = getUserInput(new int[] {1, 2});


    }

    public static void navPage() {
        if (restaurant==null) {
            loginPage();
        }
        System.out.println("\n# Navigation Page");
        System.out.println("1) Account");
        System.out.println("2) Orders");
        System.out.println("3) Menu");
        System.out.println("99) <<");
        System.out.println("=> ");
        int choice = getUserInput(new int[] {1, 2, 3, 99});
        if (choice==1) {

        }
        else if (choice==2) {

        }
        else if (choice==3) {

        }
        else if (choice==99) {
            loginPage();
        }
    }
    public static void accPage() {

    }
    public static void menuPage() {
        System.out.println("\n# Menu Page");
        System.out.println("1) Display Menu");
        System.out.println("2) Edit Menu");
        System.out.println("=> ");
        int choice = getUserInput(new int[] {1, 2, 99});
        if (choice==1) {
            restaurant.displayMenu();
        }
        else if (choice==2) {

        }
    }
    public static void editMenuPage() {
        System.out.println("\n# Edit Menu Page");
        System.out.println("1) Add item");
        System.out.println("2) Remove item");
        System.out.println("99) <<");
        System.out.println("=> ");
        int choice = getUserInput(new int[] {1, 2, 99});
        if (choice==1) {
            System.out.println("Enter item's name: ");
            String itemName = input.nextLine();
            System.out.println("Enter item's description: ");
            String itemDescription = input.nextLine();
            System.out.println("Enter item's price: ");
            double itemPrice = input.nextDouble();
            int itemId = Item.addItem(restaurant.getId(), itemName, itemDescription, itemPrice);
            restaurant.addItem(itemId);
            System.out.println("Item #"+itemId+" was added.");
            editMenuPage();
        }
        else if (choice==2) {
            while (true) {
                System.out.println("Enter itemId: ");
                int itemId = input.nextInt();
                JsonNode item = Item.getItem(itemId);
                if (item==null) {
                    System.out.println("Item is not found.");
                    continue;
                }
                if (item.get("restaurantId").asInt()==restaurant.getId()) {
                    Item.removeItem(itemId);
                    restaurant.removeItem(itemId);
                    System.out.println("Item #"+itemId+" was removed.");
                }
            }
        }
    }
    public static int getUserInput(int[] choices) {
        int choice;
        try {
            choice = input.nextInt();
            input.nextLine();
            for (int c: choices) {
                if (c==choice) {
                    return choice;
                }
            }
        }
        catch (Exception e) {
            input.nextLine();
        }

        System.out.println("Invalid input!");
        return getUserInput(choices);
    }
}
