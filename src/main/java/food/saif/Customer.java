package food.saif;

import java.time.LocalDateTime;

public class Customer extends User {
    public String email;
    public double balance;
    public LocalDateTime datetime;

    public Customer(String id, String name, String email, String phoneNumber, double balance, LocalDateTime datetime) {
        super(id, name, phoneNumber);
        this.email = email;
        this.balance = balance;
        this.datetime = datetime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return super.toString()+"\nCustomer{" +
                "balance=$" + balance +
                "}";
    }
}
