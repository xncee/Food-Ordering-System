package food.saif;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import food.roba.Item;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String id;
    List<Item> items;
    private List<Promo> promos;
    private Customer customer;
    private String status;
    private double total;
    private Invoice invoice;
    private String deliveryAddress;
    private LocalDateTime datetime;
    // ★ ★ ★ ★ ★
    // ★ ★ ★ ★ ★


    public Order(String id, List<Item> items, List<Promo> promos, Customer customer, double total, Invoice invoice, String deliveryAddress, LocalDateTime datetime, String status) {
        this.id = id;
        this.items = items;
        this.promos = promos;
        this.customer = customer;
        this.total = total;
        this.invoice = invoice;
        this.deliveryAddress = deliveryAddress;
        this.datetime = datetime;
        this.status = status;
    }

    public void confirmOrder() {
        System.out.println("Order confirmed.");
        setStatus("confirmed");
    }
    public void cancelOrder() {
        setStatus("canceled");
    }

    public void addItem(int itemId) {
        double price = Item.getPrice(itemId);
        items.add(itemId);
    }
    public void removeItem(int itemId) {
        items.remove(itemId);
    }
    public List<Integer> getItems() {
        return items;
    }
    public boolean addPromoCode(String code) {
        if (appliedPromoCodes.contains(code)) {
            System.out.println("This discount was already applied.");
            return false;
        }
        double percentage = usePromoCode(code);
        if (percentage!=-1) {
            double discount = total*percentage/100;
            if (discount<total) {
                totalDiscount += percentage;
                appliedPromoCodes.add(code);

                System.out.println(percentage+"% discount was applied.");
                total-=discount;
                return true;
            }
        }
        System.out.println("This promo code is invalid.");
        return false;
    }
    public void calculateTotal() {
        double total = 0;
        for (int item: items) {
            total += Item.getPrice(item);
        }
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return orders.get(String.valueOf(id)).toPrettyString();
    }
}
