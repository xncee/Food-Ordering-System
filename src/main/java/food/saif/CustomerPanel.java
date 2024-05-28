package food.saif;

import food.roba.Item;
import food.saif.design.Color;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerPanel implements ApplicationData, Color {
    static Scanner input = new Scanner(System.in);
    final static Application application = new Application("Talabat");
    static Login login;
    static Customer customer;

    public static void main(String[] args) {
        LocalDateTime datetime = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        System.out.println();

        homePage();

    }

    public static void homePage() {
        if (login == null || !login.isLoggedIn) loginPage();

        System.out.println("\n# Home Page");
        System.out.println("1. Account");
        System.out.println("2. Order");
        System.out.println("99) <<");
        int c = getUserInput(new int[] {1, 2, 99});

        switch (c) {
            case 1: {
                accountPage();
                break;
            }
            case 2: {
                orderPage();
                break;
            }
            case 99:
                loginPage();
                break;
        }
        homePage();
    }
    public static void loginPage() {
        System.out.println("\n# Login page");
        System.out.println("1. Sign in");
        System.out.println("2. Sign up");
        System.out.println("99. Exit");
        int c = getUserInput(new int[] {1, 2, 99});

        switch (c) {
            case 1: {
                while (true) {
                    System.out.println("Enter username: ");
                    String username = input.nextLine();
                    System.out.println("Enter password: ");
                    String password = input.nextLine();

                    login = new Login(username, password);
                    if (login.isLoggedIn) break;
                    System.out.println(RED+"Invalid credentials. Please try again."+RESET);
                }
                break;
            }
            case 2: {
                while (true) {
                    System.out.println("Enter username: ");
                    String username = input.nextLine();
                    System.out.println("Enter password: ");
                    String password = input.nextLine();
                    System.out.println("Enter name: ");
                    String name = input.nextLine();
                    System.out.println("Enter email: ");
                    String email = input.nextLine();
                    System.out.println("Enter phoneNumber: ");
                    String phoneNumber = input.nextLine();

                    login = new Login(username, password, name, email, phoneNumber);
                    if (login.isLoggedIn) break;
                    System.out.println(RED+"Sign up failed. Please try again."+RESET);
                }
                break;
            }
            case 99:
                System.exit(0);
                break;
        }

        if (login != null) {
            System.out.println(GREEN+"You have been successfully logged in."+RESET);
            String customerId = login.getId();
            customer = application.getCustomer(customerId);
        }
        else loginPage();
    }

    public static void accountPage() {
        System.out.println("\n# Account Page");
        System.out.println("1) Profile");
        System.out.println("2) Change password");
        System.out.println("3) Wallet");
        System.out.println("99) <<");
        System.out.print("=> ");
        int c = getUserInput(new int[] {1, 2, 3, 99});

        switch (c) {
            case 1: {
                System.out.println("Username: "+ login.getUsername());
                System.out.println(customer);
                break;
            }
            case 2: {
                while (true) {
                    System.out.println("Enter current password: ");
                    String password = input.nextLine();
                    System.out.println("Enter new password: ");
                    String newPassword = input.nextLine();
                    if (login.changePassword(password, newPassword)) break;

                    System.out.println(RED+"Current password is incorrect."+RESET);
                }
                System.out.println(GREEN+"Your password has been changed successfully."+RESET);
                break;
            }
            case 3: {
                walletPage();
                break;
            }
            case 99:
                homePage();
                break;
        }
        accountPage();
    }

    public static void restaurantSearchPage() {
        restaurantSearchPage(false);
    }
    public static void restaurantSearchPage(boolean oneTime) {
        System.out.println("\n# Restaurant Search Page");
        System.out.println("1. Search by name");
        System.out.println("2. Search by description");
        System.out.println("3. Search by location");
        //System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3});

        switch (c) {
            case 1: {
                System.out.println("Enter restaurantName: ");
                String restaurantName = input.nextLine();
                List<Restaurant> restaurants = Search.findRestaurant("restaurantName", restaurantName);
                for (Restaurant restaurant: restaurants) {
                    System.out.println(restaurant);
                }

                break;
            }
            case 2: {
                break;
            }
            case 3: {
                break;
            }
        }

        if (!oneTime)
            restaurantSearchPage();
    }
    public static void orderPage() {
        System.out.println("\n# Order Page");
        System.out.println("1) Display restaurants");
        System.out.println("2) My Orders");
        System.out.println("3) New Order");
        System.out.println("99) <<");
        int c = getUserInput(new int[] {1, 2, 99});
        switch (c) {
            case 1: {
                for (Identifiable restaurant: restaurantsList) {
                    System.out.println(restaurant);
                }
                break;
            }
            case 2: {
                String customerId = customer.getId();
                List<Order> orders = Search.findOrder("customerId", customerId);
                for (Order order: orders) {
                    System.out.println(order); // change the design
                }
                /*
                date:
                items names:
                total:
                address:
                 */
                break;
            }
            case 3: {
                //String id, List<Item> items, List<Promo> promos, Customer customer, double total, Invoice invoice, String deliveryAddress, LocalDateTime datetime, String status
                String orderId = Application.getNewId("ORDER", ordersList);
                List<Item> items = new ArrayList<>();

                System.out.println("Enter restaurantId: ");
                break;
            }
            case 99:
                homePage();
                break;
        }
        orderPage();
    }
    public static void walletPage() {
        System.out.println("\n# Wallet Page");
        System.out.println("** Balance: $"+customer.getBalance());
        System.out.println("1) Add balance");
        System.out.println("2) Add credit card");
        System.out.println("99) <<");
        int choice = getUserInput(new int[] {1, 2, 99});
        if (choice==1) {
            System.out.println("Enter amount: ");
            double amount = input.nextInt(); input.nextLine();
            System.out.println("# Select payment method");
            System.out.println("1) Credit card");
            System.out.println("2) Cash");
            //customer.addBalance(amount);
        }
        else if (choice==2) {
            // add payment method
        }
        else if (choice==99) {
            accountPage();
        }
    }
    // 1, 2
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
