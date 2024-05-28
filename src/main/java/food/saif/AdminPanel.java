package food.saif;

import food.mahmoud.Menu;
import food.roba.Item;

import java.util.ArrayList;
import java.util.List;

import static food.saif.CustomerPanel.getUserInput;
import static food.saif.CustomerPanel.input;

public class AdminPanel implements ApplicationData {
    final static Application application = new Application("Talabat");
    public static void main(String[] args) {

    }
    public static void homePage() {
        System.out.println("\n# Home Page");
        System.out.println("1. Restaurants");
        System.out.println("2. Customers");
        System.out.println("3. Orders");
        System.out.println("99. Exit");
        int c = getUserInput(new int[] {1, 2, 3, 99});

        switch (c) {
            case 1: {
                restaurantsPage();
                break;
            }
            case 2: {
                customersPage();
                break;
            }
            case 3: {
                break;
            }
            default:
                System.exit(0);
        }
        homePage();
    }

    public static void restaurantsPage() {
        System.out.println("\n# Restaurants Page");
        System.out.println("1. View Restaurants");
        System.out.println("2. Manage Restaurants");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 99});

        switch (c) {
            case 1: {
                for (Identifiable restaurant: restaurantsList) {
                    System.out.println(restaurant);
                }
                break;
            }
            case 2: {
                manageRestaurantsPage();
                break;
            }
            default:
                homePage();
                break;
        }
        restaurantsPage();
    }

    public static void manageRestaurantsPage() {
        System.out.println("\n# Manage Restaurants Page");
        System.out.println("1. Add Restaurant");
        System.out.println("2. Remove Restaurant");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 99});

        switch (c) {
            case 1: {
                Restaurant id = application.getNewId("REST", customersList);
                System.out.println();
                System.out.println("Enter name: ");
                String name = input.nextLine();
                System.out.println("Enter location: ");
                String location = input.nextLine();
                System.out.println("Enter description: ");
                String description = input.nextLine();

                Restaurant menuId = application.getNewId("MENU", menusList);
                ArrayList<Item> items = new ArrayList<>();
                Customer menu = new Menu(menuId, id, items);

                List<Identifiable> reviews = new ArrayList<>();
                Restaurant restaurant = new Restaurant(id, name, location, description, menu, reviews);

                application.add(restaurant);
            }
            case 2: {

            }
            default:
                restaurantsPage();
                break;
        }
        manageRestaurantsPage();
    }
}
