package food.saif;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import food.mahmoud.Menu;
import food.noor.Delivery;
import food.noor.Driver;
import food.roba.HealthyFood;
import food.roba.Item;
import food.roba.JunkFood;
import food.saif.design.Color;

import javax.management.InvalidAttributeValueException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Application implements ApplicationData, Color {
    public final String NAME;
    //HashMap<Class<?>, List<?>> listsMap = new HashMap<>();

    public Application(String NAME) {
        this.NAME = NAME;

        // no need to initialize users.
        initializeDrivers();
        initializeDeliveries();
        initializeCustomers();
        initializeItems();
        initializeMenus();
        initializeReviews();
        initializeRestaurants();
        initializePromos();
        initializeOrders();
    }

    private static List getList(Object o) throws InvalidAttributeValueException {
        if (o instanceof Customer)
            return customersList;
        else if (o instanceof Driver)
            return driversList;
        else if (o instanceof Delivery)
            return deliveriesList;
        else if (o instanceof Item)
            return itemsList;
        else if (o instanceof Menu)
            return menusList;
        else if (o instanceof Review)
            return reviewsList;
        else if (o instanceof Restaurant)
            return restaurantsList;
        else if (o instanceof Promo)
            return promosList;
        else if (o instanceof Order)
            return ordersList;
        else
            throw new InvalidAttributeValueException();
    }

    public static void add(Object o) {
        try {
            List list = getList(o);
            list.add(o);
            updateData();
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException("Failed to add object: " + e.getMessage(), e);
        }
    }

    public boolean remove(Object o) {
        try {
            List list = getList(o);
            boolean removed = list.remove(o);
            updateData();
            return removed;
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException("Failed to add object: " + e.getMessage(), e);
        }
    }

    public static String getNewId(String prefix, List<Identifiable> list) {
        // ((Customer) customersList.get(0)).
        String str;
        if (!list.isEmpty()) {
            str = list.get(list.size() - 1).getId().split(prefix)[1];
        }
        else
            str = "0";
        return prefix+(Integer.parseInt(str)+1);
    }

    public Driver getDriver(JsonNode node, String id) {
        if (node==null) return null;
        String name = node.get("name").asText();
        String phoneNumber = node.get("phoneNumber").asText();

        return new Driver(id, name, phoneNumber);
    }
    public Driver getDriver(String id) {
        for (Identifiable driver: driversList) {
            if (driver.getId().equals(id))
                return (Driver) driver;
        }

        return null;
    }
    public void initializeDrivers() {
        Iterator<String> ids = driversJson.fieldNames();
        for (JsonNode node: driversJson) {
            Driver c = getDriver(node, ids.next());
            if (c!=null)
                driversList.add(c);
        }
    }

    public Delivery getDelivery(JsonNode node, String id) {
        if (node==null) return null;
        String deliveryId = node.get("id").asText();
        String location = node.get("location").asText();
        String order = node.get("order").asText();
        String driverId = node.get("driver").asText();
        Driver driver = getDriver(driverId);
        String status = node.get("status").asText();
        double distance = node.get("distance").asDouble();

        return new Delivery(deliveryId, location, order, driver, status, distance);
    }
    public Delivery getDelivery(String id) {
        for (Identifiable delivery: deliveriesList) {
            if (delivery.getId().equals(id))
                return (Delivery) delivery;
        }

        return null;
    }
    public void initializeDeliveries() {
        Iterator<String> ids = deliveriesJson.fieldNames();
        for (JsonNode node: deliveriesJson) {
            Delivery c = getDelivery(node, ids.next());
            if (c!=null)
                deliveriesList.add(c);
        }
    }
    public Customer getCustomer(JsonNode node, String id) {
        if (node==null) return null;
        String name = node.get("customerName").asText();
        String email = node.get("email").asText();
        String phoneNumber = node.get("phoneNumber").asText();
        String address = node.get("address").asText();
        double balance = node.get("balance").asDouble();
        LocalDate date;
        try {
            date = LocalDate.parse(node.get("date").asText());
        }
        catch (DateTimeParseException e) {
            date = LocalDate.now();
        }

        return new Customer(id, name, email, phoneNumber, address, balance, date);
    }

    public Customer getCustomer(String id) {
        for (Identifiable customer: customersList) {
            if (customer.getId().equals(id))
                return (Customer) customer;
        }

        return null;
    }

    private void initializeCustomers() {
        Iterator<String> ids = customersJson.fieldNames();
        for (JsonNode node: customersJson) {
            Customer c = getCustomer(node, ids.next());
            if (c!=null)
                customersList.add(c);
        }
    }

    public Item getItem(JsonNode node, String id) {
        if (node==null) return null;

        String name = node.get("name").asText();
        double price = node.get("price").asDouble();
        String description = node.get("description").asText();
        String category = node.get("category").asText();

        boolean isHealthy = node.get("isHealthy").asBoolean();
        int calories = node.get("calories").asInt();
        boolean isVegan = node.get("isVegan").asBoolean();
        boolean isGlutenFree = node.get("isGlutenFree").asBoolean();
        boolean isOrganic = node.get("isOrganic").asBoolean();
        String ingredients = node.get("ingredients").asText();

        Item item;
        if (isHealthy)
            item = new HealthyFood(id, name, price, description, category, calories, isVegan, isGlutenFree, isOrganic, ingredients);
        else
            item = new JunkFood(id, name, price, description, category);

        return item;
    }
    public Item getItem(String id) {
        for (Identifiable item: itemsList) {
            if (item.getId().equals(id))
                return (Item) item;
        }

        return null;
    }

    private void initializeItems() {
        Iterator<String> ids = itemsJson.fieldNames();
        for (JsonNode node: itemsJson) {
            Item c = getItem(node, ids.next());
            if (c!=null)
                itemsList.add(c);
        }
    }
    public Menu getMenu(JsonNode node, String menuId) {
        if (node==null) return null;
        String restaurantId = node.get("restaurant").asText();
        List<Item> items = new ArrayList<>();
        for (JsonNode jsonNode: node.get("items")) {
            items.add(getItem(jsonNode.asText()));
        }
        return new Menu(menuId, restaurantId, items);
    }
    public Menu getMenu(String id) {
        for (Identifiable menu: menusList) {
            if (menu.getId().equals(id))
                return (Menu) menu;
        }

        return null;
    }
    private void initializeMenus() {
        Iterator<String> ids = menusJson.fieldNames();
        for (JsonNode node: menusJson) {
            Menu m = getMenu(node, ids.next());
            if (m!=null)
                menusList.add(m);
        }
    }

    public List<Identifiable> getReviews(JsonNode node, String id) {
        if (node==null) return null;
        List<Identifiable> reviews = new ArrayList<>();
        for (JsonNode jsonNode: node) {
            String customerId = jsonNode.get("customer").asText();
            Customer customer = getCustomer(customerId);
            int rating = jsonNode.get("rating").asInt();
            String comment = jsonNode.get("comment").asText();
            reviews.add(new Review(id, customer, rating, comment));
        }

        return reviews;
    }
    public List<Identifiable> getReviews(String id) {
        for (List<Identifiable> reviewList: reviewsList) {
            if (reviewList.get(0).getId().equals(id))
                return reviewList;
        }

        return null;
    }

    private void initializeReviews() {
        Iterator<String> ids = reviewsJson.fieldNames();
        for (JsonNode node: reviewsJson) {
            List<Identifiable> r = getReviews(node, ids.next());
            if (r!=null)
                reviewsList.add(r);
        }
    }

    public Restaurant getRestaurant(JsonNode node, String id) {
        if (node==null) return null;
        String name = node.get("name").asText();
        String phoneNumber = node.get("phoneNumber").asText();
        String location = node.get("location").asText();
        String description = node.get("description").asText();
        String menuId = node.get("menu").asText();
        Menu menu = getMenu(menuId);
        String reviewsId = node.get("reviews").asText();
        List<Identifiable> reviews = getReviews(reviewsId);

        return new Restaurant(id, name, phoneNumber, location, description, menu, reviews);
    }
    public Restaurant getRestaurant(String id) {
        for (Identifiable restaurant: restaurantsList) {
            if (restaurant.getId().equals(id))
                return (Restaurant) restaurant;
        }

        return null;
    }
    private void initializeRestaurants() {
        Iterator<String> ids = restaurantsJson.fieldNames();
        for (JsonNode node: restaurantsJson) {
            Restaurant r = getRestaurant(node, ids.next());
            if (r!=null)
                restaurantsList.add(r);
        }
    }

    public Promo getPromo(JsonNode node, String id) {
        if (node==null) return null;
        double percentage = node.get("percentage").asDouble();
        LocalDate expirationDate = LocalDate.parse(node.get("expirationDate").asText());

        return new Promo(id, percentage, expirationDate);
    }
    public Promo getPromo(String code) {
        for (Promo promo: promosList) {
            if (promo.getCode().equals(code))
                return promo;
        }

        return null;
    }
    private void initializePromos() {
        Iterator<String> codes = promosJson.fieldNames();
        for (JsonNode node: promosJson) {
            Promo m = getPromo(node, codes.next());
            if (m!=null)
                promosList.add(m);
        }
    }

    public Order getOrder(JsonNode node, String id) {
        if (node==null) return null;
        List<Item> items = new ArrayList<>();
        for (JsonNode jsonNode: node.get("items")) {
            items.add(getItem(jsonNode.asText()));
        }

        List<Promo> promos = new ArrayList<>();
        for (JsonNode jsonNode: node.get("promos")) {
            promos.add(getPromo(jsonNode.asText()));
        }
        Customer customer = getCustomer(node.get("customer").asText());
        String status = node.get("status").asText();
        double total = node.get("total").asDouble();
        String deliveryId = node.get("delivery").asText();
        Delivery delivery = getDelivery(deliveryId);
        LocalDateTime datetime = LocalDateTime.parse(node.get("datetime").asText());

        return new Order(id, items, promos, customer, total, delivery, datetime, status);
    }
    public Order getOrder(String id) {
        for (Identifiable order: ordersList) {
            if (order.getId().equals(id))
                return (Order) order;
        }

        return null;
    }
    private void initializeOrders() {
        Iterator<String> ids = ordersJson.fieldNames();
        for (JsonNode node: ordersJson) {
            Order i = getOrder(node, ids.next());
            if (i!=null)
                ordersList.add(i);
        }
    }

    public static void updateUsers() {
        usersFile.write();
    }
    public static void updateRestaurants() {
        restaurantsJson.removeAll();
        for (Identifiable r: restaurantsList) {
            Restaurant restaurant = (Restaurant) r;
            restaurantsJson.put(
                    restaurant.getId(),
                    new ObjectMapper().createObjectNode()
                            .put("name", restaurant.getName())
                            .put("phoneNumber", restaurant.getPhoneNumber())
                            .put("location", restaurant.getLocation())
                            .put("description", restaurant.getDescription())
                            .put("menu", restaurant.getMenu().getId())
                            .put("reviews", restaurant.getReviews().isEmpty()?"":restaurant.getReviews().get(0).getId())
            );
        }
        //System.out.println(restaurantsJson.toPrettyString());
        restaurantsFile.write();
    }
    public static void updateData() {
        updateRestaurants();
    }
}
