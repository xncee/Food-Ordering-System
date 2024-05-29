package food.saif;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import food.mahmoud.Menu;
import food.noor.Delivery;
import food.noor.Driver;
import food.roba.Food;
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
            //System.out.println(list.get(list.size() - 1).getId());
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
        String location = node.get("location").asText();
        String order = node.get("order").asText();
        String driverId = node.get("driver").asText();
        Driver driver = getDriver(driverId);
        String status = node.get("status").asText();
        double distance = node.get("distance").asDouble();

        return new Delivery(id, location, order, driver, status, distance);
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
        String name = node.get("name").asText();
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
            //System.out.println(customer+":"+customerId);
            int rating = jsonNode.get("rating").asInt();
            String comment = jsonNode.get("comment").asText();
            reviews.add(new Review(id, customerId, rating, comment));
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

    public Promo getPromo(JsonNode node, String code) {
        if (node==null) return null;
        double percentage = node.get("discountPercentage").asDouble();
        LocalDate expirationDate = LocalDate.parse(node.get("expirationDate").asText());

        return new Promo(code, percentage, expirationDate);
    }
    public Promo getPromo(String code) {
        for (Promo promo: promosList) {
            if (promo!=null)
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
        String restaurantId = node.get("restaurant").asText();
        Restaurant restaurant = getRestaurant(restaurantId);
        if (restaurant==null) return null;

        List<Item> items = new ArrayList<>();
        for (JsonNode jsonNode: node.get("items")) {
            items.add(getItem(jsonNode.asText()));
        }

        List<Promo> promos = new ArrayList<>();
        for (JsonNode jsonNode: node.get("promos")) {
            Promo promo = getPromo(jsonNode.asText());
            if (promo!=null)
                promos.add(promo);
        }
        Customer customer = getCustomer(node.get("customer").asText());
        String status = node.get("status").asText();
        double total = node.get("total").asDouble();
        String paymentMethod = node.get("paymentMethod").asText();
        String deliveryId = node.get("delivery").asText();
        Delivery delivery = getDelivery(deliveryId);
        LocalDateTime datetime = LocalDateTime.parse(node.get("datetime").asText());

        return new Order(id, restaurant, items, promos, customer, total, paymentMethod, delivery, datetime, status);
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

    public static void updateCustomers() {
        customersJson.removeAll();
        for (Identifiable r: customersList) {
            Customer customer = (Customer) r;
            customersJson.put(
                    customer.getId(),
                    new ObjectMapper().createObjectNode()
                            .put("name", customer.getName())
                            .put("email", customer.getEmail())
                            .put("phoneNumber", customer.getPhoneNumber())
                            .put("address", customer.getAddress())
                            .put("balance", customer.getBalance())
                            .put("date", String.valueOf(customer.getDate()))
            );
        }
        //System.out.println(customersJson.toPrettyString());
        customersFile.write();
    }

    public static void updateDrivers() {
        driversJson.removeAll();
        for (Identifiable r: driversList) {
            Driver driver = (Driver) r;
            driversJson.put(
                    driver.getId(),
                    new ObjectMapper().createObjectNode()
                            .put("name", driver.getName())
                            .put("phoneNumber", driver.getPhoneNumber())
            );
        }
        //System.out.println(driversJson.toPrettyString());
        driversFile.write();
    }

    public static void updateDeliveries() {
        deliveriesJson.removeAll();
        for (Identifiable r: deliveriesList) {
            Delivery delivery = (Delivery) r;
            deliveriesJson.put(
                    delivery.getId(),
                    new ObjectMapper().createObjectNode()
                            .put("location", delivery.getLocation())
                            .put("order", delivery.getOrder())
                            .put("driver", delivery.getDriver().getId())
                            .put("status", delivery.getStatus())
                            .put("distance", delivery.getDistance())
            );
        }
        //System.out.println(deliveriesJson.toPrettyString());
        deliveriesFile.write();
    }

    public static void updateItems() {
        itemsJson.removeAll();
        for (Identifiable r: itemsList) {
            Item item = (Item) r;
            ObjectNode itemNode = new ObjectMapper().createObjectNode()
                    .put("name", item.getName())
                    .put("price", item.getPrice())
                    .put("description", item.getDescription())
                    .put("category", (item instanceof Food) ? ((Food) item).getCategory() : "")
                    .put("isHealthy", (item instanceof HealthyFood))
                    .put("isVegan", (item instanceof HealthyFood) ? ((HealthyFood) item).isVegan() : false)
                    .put("isGlutenFree", (item instanceof HealthyFood) ? ((HealthyFood) item).isGlutenFree() : false)
                    .put("isOrganic", (item instanceof HealthyFood) ? ((HealthyFood) item).isOrganic() : false)
                    .put("ingredients", (item instanceof HealthyFood) ? ((HealthyFood) item).getIngredients() : "")
                    .put("calories", (item instanceof HealthyFood) ? ((HealthyFood) item).getCalories() : 0);
            itemsJson.put(item.getId(), itemNode);
        }
        //System.out.println(itemsJson.toPrettyString());
        itemsFile.write();
    }

    public static void updateMenus() {
        menusJson.removeAll();
        for (Identifiable r : menusList) {
            Menu menu = (Menu) r;
            String menuId = menu.getId();
            ArrayNode itemsArray = new ObjectMapper().createArrayNode();
            for (Item item : menu.getItems()) {
                itemsArray.add(item.getId());
            }
            ObjectNode menuNode = new ObjectMapper().createObjectNode();
            menuNode.put("restaurant", menu.getRestaurantId())
                    .set("items", itemsArray);
            menusJson.set(menuId, menuNode);
        }

        //System.out.println(menusJson.toPrettyString());
        menusFile.write();
    }

    public static void updateReviews() {
        reviewsJson.removeAll();
        for (List<Identifiable> rev : reviewsList) {
            String reviewId = rev.get(0).getId();
            ArrayNode reviewsArray = new ObjectMapper().createArrayNode();
            for (Identifiable r: rev) {
                Review review = (Review) r;
                ObjectNode reviewNode = new ObjectMapper().createObjectNode();
                //System.out.println(review);
                reviewNode.put("customer", review.getCustomer())
                        .put("rating", review.getRating())
                        .put("comment", review.getComment());

                reviewsArray.add(reviewNode);
            }
            reviewsJson.set(reviewId, reviewsArray);
        }

        // System.out.println(reviewsJson.toPrettyString());
        reviewsFile.write();
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

    public static void updatePromos() {
        promosJson.removeAll();
        for (Promo promo: promosList) {
            promosJson.put(
                    promo.getCode(),
                    new ObjectMapper().createObjectNode()
                            .put("discountPercentage", (promo==null?0.0:promo.getDiscountPercentage()))
                            .put("expirationDate", (promo==null?String.valueOf(LocalDate.now()):String.valueOf(promo.getExpirationDate())))
            );
        }
        //System.out.println(promosJson.toPrettyString());
        promosFile.write();
    }

    public static void updateOrders() {
        ordersJson.removeAll();
        for (Identifiable o: ordersList) {
            Order order = (Order) o;
            ArrayNode itemsArray = new ObjectMapper().createArrayNode();
            for (Item item : order.getItems()) {
                itemsArray.add(item.getId());
            }
            ArrayNode promosArray = new ObjectMapper().createArrayNode();
            for (Promo promo : order.getPromos()) {
                if (promo!=null)
                    promosArray.add(promo.getCode());
            }
            ObjectNode node = new ObjectMapper().createObjectNode();
            node.putArray("items").addAll(itemsArray);
            node.putArray("promos").addAll(promosArray);
            node.put("restaurant", (order.getRestaurant()==null?"":order.getRestaurant().getId()));
            node.put("customer", (order.getCustomer()==null?"":order.getCustomer().getId()));
            node.put("total", order.getTotal());
            node.put("paymentMethod", order.getPaymentMethod());
            node.put("delivery", order.getDelivery().getId());
            node.put("address", order.getDelivery().getLocation());
            node.put("datetime", String.valueOf(order.getDatetime()));
            node.put("status", order.getStatus());

            ordersJson.set(order.getId(), node);
        }

        // System.out.println(ordersJson.toPrettyString());
        ordersFile.write();
    }

    public static void updateData() {
        updateUsers();
        updateCustomers();
        updateDrivers();
        updateDeliveries();
        updateItems();
        updateRestaurants();
        updateMenus();
        updateReviews();
        updatePromos();
        updateOrders();
    }
}
