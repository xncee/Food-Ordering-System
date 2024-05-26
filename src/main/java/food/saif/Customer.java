package food.saif;

import food.saif.io.JsonFileWriter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Customer {
    final static JsonFileWriter JSWriter = new JsonFileWriter("customers.json");
    static ObjectNode customers = JSWriter.getJsonNode();
    private int customerId;
    private String customerName;
    public double balance;
    private Order[] orders;
    public Customer(int customerId) {
        setCustomerId(customerId);
        JsonNode customer = customers.get(String.valueOf(customerId));
        this.customerName = customer.get("customerName").asText();
        this.balance = customer.get("balance").asDouble();
    }
    public void updateCustomers() {
        JSWriter.write(customers);
    }
    public static void newCustomer(String newCustomerName) {
        customers.put(
                String.valueOf(getNewCustomerId()),
                JSWriter.getNewJsonNode()
                        .put("customerName", newCustomerName)
                        .put("balance", 0)
        );
        JSWriter.write(customers);
    }
    public boolean addBalance(double amount) {
        balance+=amount;
        return true; //////////// ***
    }
    public boolean addCreditCard(String cardNo, String cvv, String expDate) {
        customers.put(
                String.valueOf(customerId),
                JSWriter.getNewJsonNode()
                        .put("creditCards", "") // the value is an array of cards
                );
        return true;
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
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    @Override
    public String toString() {
        return "Balance: $"+balance+"\nID: "+customerId+"\nName: "+customerName;
    }
}
