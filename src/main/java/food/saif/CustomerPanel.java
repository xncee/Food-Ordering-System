package food.saif;

import food.mahmoud.Menu;
import food.noor.Delivery;
import food.noor.Driver;
import food.roba.Item;
import food.saif.design.Color;

import java.time.LocalDateTime;
import java.util.*;

public class CustomerPanel implements ApplicationData, Color {
    static Scanner input = new Scanner(System.in);
    final static Application application = new Application("Talabat");
    static Login login;
    static Customer customer;

    public static void main(String[] args) {
        //LocalDateTime datetime = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

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
            waitFor(1);
        }
        else loginPage();
    }

    public static void accountPage() {
        waitFor(1);
        System.out.println("\n# Account Page");
        System.out.println("1. Profile");
        System.out.println("2. Change password");
        System.out.println("3. Wallet");
        System.out.println("99) <<");

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
                    restaurant.displayRestaurant();
                }
                if (restaurants.isEmpty()) {
                    System.out.println(RED + "No Results." + RESET);
                    restaurantSearchPage(oneTime);
                }
                break;
            }
            case 2: {
                System.out.println("Enter description: ");
                String description = input.nextLine();
                List<Restaurant> restaurants = Search.findRestaurant("description", description);
                for (Restaurant restaurant: restaurants) {
                    restaurant.displayRestaurant();
                }
                if (restaurants.isEmpty()) {
                    System.out.println(RED + "No Results." + RESET);
                    restaurantSearchPage(oneTime);
                }
                break;
            }
            case 3: {
                System.out.println("Enter location: ");
                String location = input.nextLine();
                List<Restaurant> restaurants = Search.findRestaurant("location", location);
                for (Restaurant restaurant: restaurants) {
                    restaurant.displayRestaurant();
                }
                if (restaurants.isEmpty()) {
                    System.out.println(RED + "No Results." + RESET);
                    restaurantSearchPage(oneTime);
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
        System.out.println("1. Display restaurants");
        System.out.println("2. Manage My Orders");
        System.out.println("3. New Order");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 99});

        switch (c) {
            case 1: {
                Restaurant.displayRestaurants();
                break;
            }
            case 2: {
                manageOrdersPage();
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

    public static void manageOrdersPage() {
        waitFor(1);
        System.out.println("\n# Manage Orders Page");
        System.out.println("1. Display My Orders");
        System.out.println("2. Confirm Order");
        System.out.println("3. Cancel Order");
        System.out.println("4. Edit Order");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 99});
        switch (c) {
            case 1: {
                String customerId = customer.getId();
                List<Order> orders = Search.findOrder("customerId", customerId);
                for (Order order: orders) {
                    System.out.println("ID: "+order.getId());
                    System.out.println("\tRestaurant: "+order.getRestaurant().getName());
                    System.out.println("\tDate: "+order.getDatetime());
                    System.out.println("\tStatus: "+order.getStatus());
                    System.out.println("\tTotal: $"+order.getTotal());
                    System.out.println("\tPayment method: "+order.getPaymentMethod());
                }
                if (orders.isEmpty())
                    System.out.println(RED+"No orders."+RESET);
               break;
            }
            case 2: {
                System.out.println("Enter orderId: ");
                String orderId = input.nextLine();
                List<Order> orders = Search.findOrder("id", orderId);
                if (orders.isEmpty()) {
                    System.out.println(RED+"Order not found."+RESET);
                    break;
                }

                Order order = orders.get(0);

                if (order.isConfirmed()) {
                    System.out.println(RED+"This order is already confirmed."+RESET);
                    break;
                }
                if (order.getPaymentMethod().equals("cash"))
                    order.confirmOrder();
                else if (order.getPaymentMethod().equals("balance")) {
                    if (customer.deductBalance(order.getTotal())) {
                        System.out.println(GREEN + "Order paid." + RESET);
                        order.confirmOrder();
                    }
                    else
                        System.out.println(RED+"Insufficient balance."+RESET);
                }

                if (order.isConfirmed()) {
                    Application.updateCustomers();
                    Application.updateOrders();
                    System.out.println(GREEN + "Order cofirmed." + RESET);
                }

                break;
            }
            case 3: {
                String orderId = input.nextLine();
                List<Order> orders = Search.findOrder("id", orderId);
                if (orders.isEmpty()) {
                    System.out.println(RED+"Order not found."+RESET);
                    break;
                }

                Order order = orders.get(0);

                if (order.isCanceled()) {
                    System.out.println(RED+"This order is already canceled."+RESET);
                    break;
                }
                order.cancelOrder();
                System.out.println(YELLOW+"Order canceled."+RESET);
                break;
            }
            case 4: {
                break;
            }
            case 99:
                orderPage();
                break;
        }
        manageOrdersPage();
    }
    public static void newOrderPage() {
        waitFor(1);
        System.out.println("\n# New Order Page");

        Restaurant restaurant;

        while (true) {
            System.out.println("\nEnter restaurantId (enter # to search or = to quit): ");
            String restaurantId = input.nextLine();
            if (restaurantId.equals("#")) {
                restaurantSearchPage(true);
                continue;
            }
            else if (restaurantId.equals("=")) {
                return;
            }
            List<Restaurant> restaurants = Search.findRestaurant("id", restaurantId);
            if (!restaurants.isEmpty()) {
                restaurant = restaurants.get(0);
                break;
            }

            System.out.println(RED + "Restaurant not found. Please try again." + RESET);
        }
        if (restaurant==null) return;

        System.out.println("\n# "+restaurant.getName());
        System.out.println("Description: "+restaurant.getDescription());
        System.out.println("Rating: "+restaurant.getRating() + " ("+restaurant.getOrdersCount()+")");
        System.out.println("Location: "+restaurant.getLocation());
        System.out.println();
        System.out.print("Enter to display menu: ");
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
        HashMap<Item, Integer> itemsMap = new HashMap<>();
        while (true) {
            System.out.println("Item number (enter -1 to finsh): ");
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
            if (itemsMap.containsKey(item)) itemsMap.put(item, itemsMap.get(item)+quantity);
            else itemsMap.put(item, quantity);
        }
        if (items.isEmpty()) {
            System.out.println(RED+"No items were added."+RESET);
            newOrderPage();
            return;
        }

        List<Promo> promos = new ArrayList<>();
        double discount = 0;
        while (true) {
            System.out.println("Enter promo code (keep blank if not available): ");
            String promoCode = input.nextLine();

            if (promoCode.isEmpty()) break;
            Promo promo = Promo.validateCode(promoCode);
            if (promo==null) continue;
            if (discount+promo.getDiscountPercentage()>1) {
                System.out.println(YELLOW + "Promo code wasn't applied. You reached the maximum discount." + RESET);
                break;
            }

            promos.add(promo);
            discount+=promo.getDiscountPercentage();
            System.out.println(PURPLE+promo.getDiscountPercentage()*100+"% discount was applied."+RESET);
        }
        //if (order==null) newOrderPage();
        String orderId = Application.getNewId("ORDER", ordersList);
        System.out.println("Enter delivery address (keep blank for default address): ");
        String address = input.nextLine();
        if (address.isEmpty())
            address = customer.getAddress();

        String deliveryId = Application.getNewId("DELV", deliveriesList); // yes ordersList
        if (driversList.isEmpty()) {
            System.out.println(RED+"No available drivers."+WHITE);
            return;
        }
        Driver driver = (Driver) driversList.get((int) getRandomNumber(0, driversList.size()));
        double distance = (int) (getRandomNumber(1, 10));

        Delivery delivery = new Delivery(deliveryId, address, orderId, driver, "not delivered", distance);
        double deliveryFee = Math.ceil(delivery.calculateDeliveryFee());
        int deliveryTime = delivery.calculateDeliveryTime();
        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println("\n# Order Overview");
        System.out.println("Restaurant: "+restaurant.getName());
        System.out.println("Items: ");

        for (Item item: itemsMap.keySet()) {
            int quantity = itemsMap.get(item);
            System.out.println("\t$"+item.getPrice()*quantity+"  "+quantity+"*"+item.getName());
        }

        total = Math.round(total);

        System.out.println("Total: ");
        System.out.println("\t+$"+total+" (items)");
        System.out.println("\t+$"+deliveryFee+" (delivery fee)");
        System.out.println("\t"+YELLOW+"-$"+(total*discount)+" ("+discount*100+"% discount)"+RESET);
        System.out.println("\t=$"+(total+=(deliveryFee-total*discount)));
        // delivery fee is added to total and discount is applied.
        System.out.println("Delivery address: "+address);
        System.out.println("# Payment Method");
        System.out.println("1. Cash");
        System.out.println("2. Balance");
        //System.out.println("3. Credit card");

        String paymentMethod = "";
        int p = getUserInput(new int[] {1, 2});
        switch (p) {
            case 1: {
                paymentMethod = "cash";
                break;
            }
            case 2: {
                paymentMethod = "balance";
                break;
            }
            default:
                paymentMethod = "";
        }

        Order order = new Order(orderId, restaurant, items, promos, customer, total, paymentMethod, delivery, dateTime);
        System.out.println("\nEnter to confirm (enter # to cancel): ");
        String con = input.nextLine();
        if (con.equals("#")) {
            order.cancelOrder();
            System.out.println(YELLOW+"Order canceled."+RESET);
            return;
        }

        if (paymentMethod.equals("balance")) {
            if (customer.deductBalance(total)) {
                System.out.println(GREEN + "Order paid." + RESET);
                order.confirmOrder();
            }
            else
                System.out.println(RED+"Insufficient balance."+RESET);
        }
        else if (paymentMethod.equals("cash"))
            order.cancelOrder();

        Application.add(order);
        Application.add(delivery);
        if (!order.isConfirmed()) return;

        System.out.println(GREEN+"Order confirmed."+RESET);
        //System.out.println("\n# Delivery Details");
        System.out.println("\nDriver: ");
        System.out.println("\tName: "+driver.getName());
        System.out.println("\tPhone number: "+driver.getPhoneNumber());
        System.out.println("\tApproximate delivery time: "+deliveryTime+" minutes");

    }
    public static void walletPage() {
        waitFor(1);
        System.out.println("\n# Wallet Page");
        System.out.println("1. Display balance");
        System.out.println("2. Add balance");
        //System.out.println("3. Add credit card");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 99});

        switch (c) {
            case 1: {
                System.out.println(PURPLE+"Balance: $"+customer.getBalance()+RESET);
                break;
            }
            case 2: {
                while (true) {
                    System.out.println("Enter amount: ");
                    try {
                        double amount = input.nextInt();
                        input.nextLine();
                        customer.addBalance(amount);
                        break;
                    }
                    catch (InputMismatchException e) {
                        System.out.println(RED+"Invalid input. try again."+RESET);
                    }
                }
                Application.updateCustomers();
                System.out.println(GREEN+"Balance added."+RESET);
                //System.out.println("# Select payment method");
                //System.out.println("1) Credit card");
                //System.out.println("2) Cash");
                //customer.addBalance(amount);
                break;
            }
            case 3: {
                //addCreditCardPage();
                break;
            }
            case 99: {
                accountPage();
                break;
            }
        }

        walletPage();
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
