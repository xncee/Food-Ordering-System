package food.saif;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class orderMain {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        //Promo.addPromoCode(30, LocalDate.now().plusMonths(1), "S50");
        addItems();

        //Customer.newCustomer("Saif Alkhalili");
        //Login user = new Login();

        // You should login first, and then get the customer id using the username

        System.out.println("Enter customerId: ");
        int customerId = input.nextInt();
        Customer customer = new Customer(customerId);

        List<Integer> items = new ArrayList<>();
        items.add(1);
        items.add(2);

        System.out.println("Enter orderId: ");
        int orderId = input.nextInt();
        Order order = new Order(orderId);
        addPromoCodes(order);

        order.confirmOrder();
        System.out.println(order);
        //order.cancelOrder();
    }
    public static void addItems() {
        System.out.println("Enter itemName: ");
        String itemName = input.nextLine();

        System.out.println("Enter itemDescription: ");
        String itemDescription = input.nextLine();

        System.out.println("Enter itemPrice: ");
        double itemPrice = input.nextDouble(); input.nextLine();

        while (itemPrice>0) {
            Item.addItem(45, itemName, itemDescription, itemPrice);
            System.out.println();

            System.out.println("Enter itemName: ");
            itemName = input.nextLine();
            System.out.println("Enter itemDescription: ");
            itemDescription = input.nextLine();
            System.out.println("Enter itemPrice: ");
            itemPrice = input.nextDouble(); input.nextLine();
        }
    }
    public static void addPromoCodes(Order order) {
        System.out.println("Enter promo code: ");
        String promoCode = input.nextLine();
        while (!promoCode.isEmpty()) {
            order.addPromoCode(promoCode);
            System.out.println("Enter promo code: ");
            promoCode = input.nextLine();
        }
    }
}
