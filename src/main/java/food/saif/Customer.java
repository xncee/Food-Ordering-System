package food.saif;

public class Customer extends User {
    public double balance;
    public Customer(String id, String name, String phoneNumber, double balance) {
        super(id, name, phoneNumber);
        this.balance = balance;
    }

    public static String getNewId() {
        String str;
        if (!customersList.isEmpty()) {
            str = customersList.get(customersList.size() - 1).getId().split("CUST")[1];
        }
        else
            str = "0";
        return "CUST"+(Integer.parseInt(str)+1);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return super.toString()+"\nCustomer{" +
                "balance=$" + balance +
                "}";
    }
}
