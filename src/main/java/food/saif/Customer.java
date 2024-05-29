package food.saif;

import java.time.LocalDate;

public class Customer extends User {
    private String email;
    private String address;
    private double balance;
    private LocalDate date;

    public Customer(String id, String name, String email, String phoneNumber, String address, double balance, LocalDate date) {
        super(id, name, phoneNumber);
        this.email = email;
        this.address = address;
        this.balance = balance;
        this.date = date;
    }

    public void addBalance(double amount) {
        balance += amount;
    }

    private boolean checkSufficientBalance(double amount) {
        return (balance>=amount);
    }
    public boolean deductBalance(double amount) {
        if (!checkSufficientBalance(amount)) return false;

        balance -= amount;
        return true;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return super.toString()+"\nCustomer{" +
                "email=" + email +
                ", address=" + address +
                ", balance=$" + balance +
                ", registrationDate=" + date +
                "}";
    }
}
