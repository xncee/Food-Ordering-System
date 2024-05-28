package food.saif;

import food.roba.Item;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Order implements Identifiable {
    private String id;
    private List<Item> items;
    private List<Promo> promos;
    private Customer customer;
    private String status;
    private double total;
    private Invoice invoice;
    private String deliveryAddress; // change it to Delivery
    private LocalDateTime datetime;

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

    public void addItem(Item item) {
        double price = item.getPrice();
        items.add(item);
    }
    public void removeItem(Item item) {
        items.remove(item);
    }
//    public boolean addPromoCode(String code) {
//        if (appliedPromoCodes.contains(code)) {
//            System.out.println("This discount was already applied.");
//            return false;
//        }
//        double percentage = usePromoCode(code);
//        if (percentage!=-1) {
//            double discount = total*percentage/100;
//            if (discount<total) {
//                totalDiscount += percentage;
//                appliedPromoCodes.add(code);
//
//                System.out.println(percentage+"% discount was applied.");
//                total-=discount;
//                return true;
//            }
//        }
//        System.out.println("This promo code is invalid.");
//        return false;
//    }
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

    public Identifiable getInvoice() {
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
        return "Order{" +
                "id=" + id  +
                ", items=" + Arrays.toString(items.toArray()) +
                ", promos=" + Arrays.toString(promos.toArray()) +
                ", customer=" + customer +
                ", status=" + status +
                ", total=" + total +
                ", invoice=" + invoice +
                ", deliveryAddress=" + deliveryAddress +
                ", datetime=" + datetime +
                "}";
    }
}
