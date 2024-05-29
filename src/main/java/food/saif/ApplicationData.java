package food.saif;

import com.fasterxml.jackson.databind.node.ObjectNode;
import food.saif.io.File;

import java.util.ArrayList;
import java.util.List;

public interface ApplicationData {
    File usersFile = new File("users.json");
    ObjectNode usersJson = usersFile.read();
    //List<Identifiable> usersList = new ArrayList<>(); not needed
    File customersFile = new File("customers.json");
    ObjectNode customersJson = customersFile.read();
    List<Identifiable> customersList = new ArrayList<>(); // ((Customer) customersList.get(0)).
    File driversFile = new File("drivers.json");
    ObjectNode driversJson = driversFile.read();
    List<Identifiable> driversList = new ArrayList<>();
    File deliveriesFile = new File("deliveries.json");
    ObjectNode deliveriesJson = deliveriesFile.read();
    List<Identifiable> deliveriesList = new ArrayList<>();
    File itemsFile = new File("items.json");
    ObjectNode itemsJson = itemsFile.read();
    List<Identifiable> itemsList = new ArrayList<>();
    File menusFile = new File("menus.json");
    ObjectNode menusJson = menusFile.read();
    List<Identifiable> menusList = new ArrayList<>();
    File reviewsFile = new File("reviews.json");
    ObjectNode reviewsJson = reviewsFile.read();
    List<List<Identifiable>> reviewsList = new ArrayList<>();
    File restaurantsFile = new File("restaurants.json");
    ObjectNode restaurantsJson = restaurantsFile.read();
    List<Identifiable> restaurantsList = new ArrayList<>();
    File promosFile = new File("promos.json");
    ObjectNode promosJson = promosFile.read();
    List<Promo> promosList = new ArrayList<>();
    File ordersFile = new File("orders.json");
    ObjectNode ordersJson = ordersFile.read();
    List<Identifiable> ordersList = new ArrayList<>();

}
