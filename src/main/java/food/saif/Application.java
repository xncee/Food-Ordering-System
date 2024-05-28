package food.saif;

import com.fasterxml.jackson.databind.JsonNode;
import food.mahmoud.Menu;
import food.roba.HealthyFood;
import food.roba.Item;
import food.roba.JunkFood;
import food.saif.design.Color;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Application implements ApplicationData, Color {
    public final String NAME;

    public Application(String NAME) {
        this.NAME = NAME;

        initializeCustomers();
        initializeMenus();
        initializeReviews();
        initializeRestaurants();
        initializePromos();
        initializeInvoices();
        initializeOrders();
    }

    public String getNewId(String prefix, List<Customer> list) {
        // ((Customer) customersList.get(0)).
        String str;
        if (!list.isEmpty()) {
            str = list.get(list.size() - 1).getId().split(prefix)[1];
        }
        else
            str = "0";
        return prefix+(Integer.parseInt(str)+1);
    }

    public Customer getCustomer(JsonNode node, String id) {
        if (node==null) return null;
        String name = node.get("customerName").asText();
        String phoneNumber = node.get("phoneNumber").asText();
        int balance = node.get("balance").asInt();

        return new Customer(id, name, phoneNumber, balance);
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

    public Menu getMenu(JsonNode node, String menuId) {
        if (node==null) return null;
        String restaurantId = node.get("restaurantId").asText();
        Restaurant restaurant = getRestaurant(restaurantId);
        List<Item> items = new ArrayList<>();
        for (JsonNode jsonNode: node) {
            String itemId = jsonNode.get("id").asText();
            String name = jsonNode.get("name").asText();
            double price = jsonNode.get("price").asDouble();
            String description = jsonNode.get("description").asText();
            String category = jsonNode.get("category").asText();

            boolean isHealthy = jsonNode.get("isHealthy").asBoolean();
            int calories = jsonNode.get("calories").asInt();
            boolean isVegan = jsonNode.get("isVegan").asBoolean();
            boolean isGlutenFree = jsonNode.get("isGlutenFree").asBoolean();
            boolean isOrganic = jsonNode.get("isOrganic").asBoolean();
            String ingredients = jsonNode.get("ingredients").asText();

            Item item;
            if (isHealthy)
                item = new HealthyFood(itemId, name, price, description, category, calories, isVegan, isGlutenFree, isOrganic, ingredients);
            else
                item = new JunkFood(itemId, name, price, description, category);

            items.add(item);
        }
        return new Menu(menuId, restaurant, items);
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
    public Promo getPromo(String id) {
        for (Promo promo: promosList) {
            if (promo.getId().equals(id))
                return promo;
        }

        return null;
    }
    private void initializePromos() {
        Iterator<String> ids = promosJson.fieldNames();
        for (JsonNode node: promosJson) {
            Promo m = getPromo(node, ids.next());
            if (m!=null)
                promosList.add(m);
        }
    }

    public Invoice getInvoice(JsonNode node, String id) {
        if (node==null) return null;
        String orderId = node.get("orderId").asText();
        Order order = getOrder(orderId);
        double amount = node.get("amount").asDouble();
        LocalDate date = LocalDate.parse(node.get("date").asText());

        return new Invoice(id, order, amount, date);
    }
    public Invoice getInvoice(String id) {
        for (Identifiable invoice: invoicesList) {
            if (invoice.getId().equals(id))
                return (Invoice) invoice;
        }

        return null;
    }
    private void initializeInvoices() {
        Iterator<String> ids = invoicesJson.fieldNames();
        for (JsonNode node: invoicesJson) {
            Invoice i = getInvoice(node, ids.next());
            if (i!=null)
                invoicesList.add(i);
        }
    }

    public Item getItem(String id) {
        for (Identifiable item: itemsList) {
            if (item.getId().equals(id))
                return (Item) item;
        }

        return null;
    }
    public Order getOrder(JsonNode node, String id) {
        if (node==null) return null;
        List<Item> items = new ArrayList<>();
        // I'm not sure if this works (test it)
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
        Invoice invoice = getInvoice(node.get("invoice").asText());
        String address = node.get("address").asText();
        LocalDateTime datetime = LocalDateTime.parse(node.get("datetime").asText());

        return new Order(id, items, promos, customer, total, invoice, address, datetime, status);
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
}
