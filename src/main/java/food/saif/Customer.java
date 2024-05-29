package food.saif;

import java.time.LocalDate;

public class Customer extends User {
    private String email;
    private String address;
    private double balance;
    private LocalDate datetime;

    public Customer(String id, String name, String email, String phoneNumber, String address, double balance, LocalDate datetime) {
        super(id, name, phoneNumber);
        this.email = email;
        this.address = address;
        this.balance = balance;
        this.datetime = datetime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDate datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return super.toString()+"\nCustomer{" +
                "balance=$" + balance +
                "}";
    }
}
