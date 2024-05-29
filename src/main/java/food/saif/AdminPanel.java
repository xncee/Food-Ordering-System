package food.saif;

import food.mahmoud.Menu;
import food.roba.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static food.saif.CustomerPanel.waitFor;
import static food.saif.CustomerPanel.getUserInput;
import static food.saif.design.Color.*;

public class AdminPanel implements ApplicationData {
    static Scanner input = new Scanner(System.in);
    final static Application application = new Application("Talabat");
    public static void main(String[] args) {
        homePage();
    }
    
    public static void homePage() {
        waitFor(1);
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
        waitFor(1);
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

    public static void customersPage() {
        waitFor(1);
        System.out.println("\n# Customers Page");
        System.out.println("1. View Customers");
        System.out.println("2. Search");
        System.out.println("3. Manage Customers");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 99});

        switch (c) {
            case 1: {
                for (Identifiable customer: customersList) {
                    System.out.println(customer);
                }
                break;
            }
            case 2: {
                //searchCustomersPage();
                break;
            }
            case 3: {
                //manageCustomersPage();
                break;
            }
            default:
                homePage();
                break;
        }
        customersPage();
    }

    public static void ordersPage() {
        waitFor(1);
        System.out.println("\n# Orders Page");
        System.out.println("1. View Orders");
        System.out.println("2. Search");
        System.out.println("3. Manage Orders");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 99});

        switch (c) {
            case 1: {
                for (Identifiable order: ordersList) {
                    System.out.println(order);
                }
                break;
            }
            case 2: {
                //searchOrdersPage();
                break;
            }
            case 3: {
                //manageOrdersPage();
                break;
            }
            default:
                homePage();
                break;
        }
        ordersPage();
    }

    public static Restaurant validateRestaurant() {
        Restaurant restaurant;
        while (true) {
            System.out.println("Enter restaurantId (enter '#' to search or '=' to quit): ");
            String restaurantId = input.nextLine().toLowerCase();
            if (restaurantId.equals("#")) {
                restaurantSearchPage(true);
                continue;
            } else if (restaurantId.equals("=")) {
                return null;
            }

            //List<Restaurant> restaurants = Restaurant.find("restaurantId", restaurantId);
            List<Restaurant> restaurants = new ArrayList<>();

            if (restaurants.isEmpty()) {
                System.out.println(RED + "No Results!" + RESET);
                continue;
            }

            restaurant = restaurants.get(0);
            return restaurant;
        }
    }

    public static void restaurantSearchPage() {
        restaurantSearchPage(false);
    }

    public static void restaurantSearchPage(boolean oneTime) {
        waitFor(1);
        System.out.println("\n# Manage Restaurants Page");
        System.out.println("1. Search by id");
        System.out.println("2. Search by name");
        System.out.println("3. Search by description");
        System.out.println("4. Search by location");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 4, 99});

        switch (c) {
            case 1: {
                System.out.println("Enter id: ");
                String id = input.nextLine();

                break;
            }
            case 2: {
                System.out.println("Enter name: ");
                String name = input.nextLine();
                break;
            }
            case 3: {
                System.out.println("Enter description: ");
                String description = input.nextLine();
                break;
            }
            case 4: {
                System.out.println("Enter location: ");
                String location = input.nextLine();
                break;
            }
            case 99:
                if (!oneTime)
                    restaurantsPage();
                break;
        }

        if (!oneTime)
            restaurantSearchPage();

    }
    public static void manageRestaurantsPage() {
        waitFor(1);
        System.out.println("\n# Manage Restaurants Page");
        System.out.println("1. Add Restaurant");
        System.out.println("2. Remove Restaurant");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 99});

        switch (c) {
            case 1: {
                String id = application.getNewId("REST", restaurantsList);
                System.out.println(id);
                System.out.println();
                System.out.println("Enter name: ");
                String name = input.nextLine();
                System.out.println("Enter phoneNumber: ");
                String phoneNumber = input.nextLine();
                System.out.println("Enter location: ");
                String location = input.nextLine();
                System.out.println("Enter description: ");
                String description = input.nextLine();

                String menuId = application.getNewId("MENU", menusList);
                ArrayList<Item> items = new ArrayList<>();
                Menu menu = new Menu(menuId, id, items);

                List<Identifiable> reviews = new ArrayList<>();
                //application.add(reviews);

                Restaurant restaurant = new Restaurant(id, name, phoneNumber, location, description, menu, reviews);

                application.add(restaurant);
                System.out.println(GREEN+"Restaurant added."+RESET);
                break;
            }
            case 2: {
                Restaurant restaurant = validateRestaurant();
                if (restaurant==null) break;

                application.remove(restaurant);
                System.out.println(YELLOW+"Restaurant removed."+RESET);
                break;
            }
            case 99:
                restaurantsPage();
                break;
        }
        manageRestaurantsPage();
    }
}
