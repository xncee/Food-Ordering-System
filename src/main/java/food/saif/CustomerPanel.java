package food.saif;

import com.sun.source.tree.WhileLoopTree;
import food.mahmoud.Menu;
import food.noor.Delivery;
import food.noor.Driver;
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
    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds*1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static double getRandomNumber(double min, double max) {
        return min+Math.random()*(max-min);
    }

    public static void homePage() {
        waitFor(1);
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
        waitFor(1);
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
                    System.out.println("Enter address: ");
                    String address = input.nextLine();

                    login = new Login(username, password, name, email, phoneNumber, address);
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
        waitFor(1);
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
        waitFor(1);
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
                List<Restaurant> restaurants = Search.findRestaurant("name", restaurantName);
                for (Restaurant restaurant: restaurants) {
                    System.out.println(restaurant);
                }
                break;
            }
            case 2: {
                System.out.println("Enter description: ");
                String description = input.nextLine();
                List<Restaurant> restaurants = Search.findRestaurant("description", description);
                for (Restaurant restaurant: restaurants) {
                    System.out.println(restaurant);
                }
                break;
            }
            case 3: {
                System.out.println("Enter location: ");
                String location = input.nextLine();
                List<Restaurant> restaurants = Search.findRestaurant("location", location);
                for (Restaurant restaurant: restaurants) {
                    System.out.println(restaurant);
                }
                break;
            }
        }

        if (!oneTime)
            restaurantSearchPage();
    }
    public static void orderPage() {
        waitFor(1);
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
                newOrderPage();
                break;
            }
            case 99:
                homePage();
                break;
        }
        orderPage();
    }

    public static void newOrderPage() {
        waitFor(1);
        System.out.println("\n# New Order Page");

        Restaurant restaurant = null;
        restaurantSearchPage();
        while (true) {
            System.out.println("Enter restaurantId: ");
            String restaurantId = input.nextLine();
            List<Restaurant> restaurants = Search.findRestaurant("id", restaurantId);
            if (!restaurants.isEmpty()) {
                restaurant = restaurants.get(0);
                break;
            }

            System.out.println(RED + "Restaurant not found. Please try again." + RESET);
        }
        if (restaurant==null) return;

        System.out.println("# "+restaurant.getName());
        System.out.println("Description: "+restaurant.getDescription());
        System.out.println("Rating: "+restaurant.getRating() + "("+restaurant.getOrdersCount()+")");
        System.out.println("Location: "+restaurant.getLocation());
        System.out.println();
        System.out.println("Enter to display menu: ");
        input.nextLine();

        Menu menu = restaurant.getMenu();
        List<Item> menuItems = menu.getItems();
        restaurant.displayMenu();

        if (menuItems.isEmpty()) {
            System.out.println(RED+"This restaurant doesn't have items in menu."+RESET);
            return;
        }

        double total = 0;
        List<Item> items = new ArrayList<>();
        System.out.println("\nSelect items (enter -1 to finish):");
        while (true) {
            System.out.println("Item number: ");
            int itemNo = input.nextInt(); input.nextLine();
            if (itemNo==-1) break;
            Item item = menuItems.get(itemNo-1);
            if (item==null) {
                System.out.println(RED+"Invalid item. try again."+RESET);
                continue;
            }
            System.out.println("Quantity: ");
            int quantity = input.nextInt();
            input.nextLine();

            total += item.getPrice()*quantity;
            for (int i=0; i<quantity; i++) {
                items.add(item);
            }
        }
        if (items.isEmpty()) {
            System.out.println(RED+"No items were added."+RESET);
            newOrderPage();
            return;
        }

        List<Promo> promos = new ArrayList<>();
        while (true) {
            System.out.println("Enter promo code (keep blank if not available): ");
            String promoCode = input.nextLine();

            if (promoCode.isEmpty()) break;
            Promo promo = Promo.validateCode(promoCode);
            if (promo==null) continue;

            promos.add(promo);
            System.out.println(PURPLE+promo.getPercentage()*100+"% discount was applied."+RESET);
        }
        //if (order==null) newOrderPage();
        String orderId = Application.getNewId("ORDER", ordersList);
        System.out.println("Enter delivery address (keep blank for default address): ");
        String address = input.nextLine();
        if (address.isEmpty())
            address = customer.getAddress();

        String deliveryId = Application.getNewId("DELV", ordersList); // yes ordersList
        if (driversList.isEmpty()) {
            System.out.println(RED+"No available drivers."+WHITE);
            return;
        }
        Driver driver = (Driver) driversList.get((int) getRandomNumber(0, driversList.size()));
        double distance = (int) (getRandomNumber(5, 20));

        Delivery delivery = new Delivery(deliveryId, address, orderId, driver, "not delivered", distance);
        double deliveryFee = delivery.calculateDeliveryFee();
        int deliveryTime = delivery.calculateDeliveryTime();
        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println("\n# Order Overview");
        System.out.println("Restaurant: "+restaurant.getName());
        System.out.println("Items: ");
        for (Item item: items) {
            int quantity = 0;
            for (Item i: items) {
                if (i.equals(item))
                    quantity++;
            }
            System.out.println("\t$"+item.getPrice()*quantity+" - "+item.getName()+"*"+quantity);
        }
        System.out.println("Total: ");
        System.out.println("\t$"+total);
        System.out.println("\t$"+deliveryFee+" (items)");
        System.out.println("\t=$"+(total+=deliveryFee)+" (delivery fee)"); // delivery fee is added to total
        System.out.println("Delivery address: "+address);
        System.out.println("Enter to confirm (enter # to cancel): ");
        String con = input.nextLine();
        if (con.equals("#")) {
            System.out.println("\nOrder canceled.");
            return;
        }
        Order order = new Order(orderId, items, promos, customer, total, delivery, dateTime, "confirmed");
        Application.add(delivery);
        Application.add(order);

        System.out.println(GREEN+"Order confirmed."+RESET);

        System.out.println("\n# Delivery Details");
        System.out.println("Driver: ");
        System.out.println("\t"+driver.getName());
        System.out.println("\t"+driver.getPhoneNumber());
        System.out.println("Approximate delivery time: "+deliveryTime);

    }
    public static void walletPage() {
        waitFor(1);
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
