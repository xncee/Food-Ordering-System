package food.saif;

import com.fasterxml.jackson.databind.node.ObjectNode;
import food.mahmoud.Menu;
import food.roba.Item;
import food.saif.io.File;

import java.util.ArrayList;
import java.util.List;

public interface ApplicationData {
    File customersFile = new File("customers.json");
    ObjectNode customersJson = customersFile.read();
    List<Customer> customersList = new ArrayList<>();
    File itemsFile = new File("items.json");
    ObjectNode itemsJson = itemsFile.read();
    List<Item> itemsList = new ArrayList<>();
    File menusFile = new File("menus.json");
    ObjectNode menusJson = menusFile.read();
    List<Menu> menusList = new ArrayList<>();
    File reviewsFile = new File("reviews.json");
    ObjectNode reviewsJson = reviewsFile.read();
    List<List<Review>> reviewsList = new ArrayList<>();
    File restaurantsFile = new File("restaurants.json");
    ObjectNode restaurantsJson = restaurantsFile.read();
    List<Restaurant> restaurantsList = new ArrayList<>();
    File promosFile = new File("promos.json");
    ObjectNode promosJson = promosFile.read();
    List<Promo> promosList = new ArrayList<>();
    File invoicesFile = new File("invoices.json");
    ObjectNode invoicesJson = invoicesFile.read();
    List<Invoice> invoicesList = new ArrayList<>();
    File ordersFile = new File("orders.json");
    ObjectNode ordersJson = ordersFile.read();
    List<Order> ordersList = new ArrayList<>();


}
