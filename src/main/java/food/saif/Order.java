package food.saif;

import food.noor.Delivery;
import food.roba.Item;
import food.saif.design.Color;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Order implements Identifiable, Color {
    private String id;
    private Restaurant restaurant;
    private List<Item> items;
    private List<Promo> promos;
    private Customer customer;
    private String status;
    private double total;
    private String paymentMethod;
    private Delivery delivery;
    private LocalDateTime datetime;

    public Order(String id, Restaurant restaurant, List<Item> items, List<Promo> promos, Customer customer, double total, String paymentMethod, Delivery delivery, LocalDateTime datetime, String status) {
        this.id = id;
        this.restaurant = restaurant;
        this.items = items;
        this.promos = promos;
        this.customer = customer;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.delivery = delivery;
        this.datetime = datetime;
        this.status = status;
    }
    public Order(String id, Restaurant restaurant, List<Item> items, List<Promo> promos, Customer customer, double total, String paymentMethod, Delivery delivery, LocalDateTime datetime) {
        this(id, restaurant, items, promos, customer, total, paymentMethod, delivery, datetime, "open");
    }

    public void confirmOrder() {
        setStatus("confirmed");
    }
    public void cancelOrder() {
        setStatus("canceled");
    }

    public boolean isConfirmed() {
        return status.equals("confirmed");
    }

    public boolean isCanceled() {
        return status.equals("canceled");
    }

    public void addItem(Item item) {
        if (item==null) {
            System.out.println(RED+"Item not found"+RESET);
            return;
        }
        double price = item.getPrice();
        total += price;
        items.add(item);
    }
    public void removeItem(Item item) {
        if (item==null || !items.remove(item)) {
            System.out.println(RED+"Item not found"+RESET);
            return;
        }

        double price = item.getPrice();
        total += price;
    }

    public void calculateTotal() {
        double total = 0;
        for (Item item: items) {
            total += item.getPrice();
        }
        this.total = total;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Promo> getPromos() {
        return promos;
    }

    public void setPromos(List<Promo> promos) {
        this.promos = promos;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id  +
                ", items=" + Arrays.toString(items.toArray()) +
                ", promos=" + Arrays.toString(promos.toArray()) +
                ", customer=" + customer +
                ", status=" + status +
                ", total=" + total +
                ", delivery=" + delivery.toString() +
                ", datetime=" + datetime +
                "}";
    }
}
