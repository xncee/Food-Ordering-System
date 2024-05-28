package food.saif;


import java.time.LocalDate;

public class Invoice implements Identifiable {
    private String id;
    private Order order;
    private double amount;
    private LocalDate date;

    public Invoice(String id, Order order, double amount, LocalDate date) {
        this.id = id;
        this.order = order;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
