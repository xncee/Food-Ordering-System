package food.saif;

public class Customer {
    private String id;
    private String name;
    private String phoneNumber;
    public double balance;
    public Customer(String id, String name, String phoneNumber, double balance) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static int getNewCustomerId() {
        return customers.size()+1;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name=" + name +
                ", balance=$" + balance +
                "}";
    }
}
