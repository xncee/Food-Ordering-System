package food.saif;

import food.saif.io.JsonFileWriter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order extends Promo {
    final static JsonFileWriter JSWriter = new JsonFileWriter("orders.json");
    static ObjectNode orders = JSWriter.getJsonNode();
    private Long orderId;
    private int customerId;
    private List<Integer> items;
    private double total;
    private boolean paid;
    private int invoiceId;
    private List<String> appliedPromoCodes = new ArrayList<>();
    private double totalDiscount;
    private String paymentMethod;
    private String deliveryAddress;
    private String status;
    private String phoneNumber;
    private String orderDate;
    /*
                .put("status", status)
                .put("total", total)
                .put("invoiceId", invoiceId)
                .put("paymentMethod", paymentMethod)
                .put("deliveryAddress", deliveryAddress)
                .put("phoneNumber", phoneNumber)
                .put("orderDate", orderDate);
     */
    // ★ ★ ★ ★ ★
    // ★ ★ ★ ★ ★
    public Order(int orderId) {
        if (orders.get(String.valueOf(orderId))==null) {
            System.out.println("Invalid orderId.");
            return;
        }
        JsonNode order = orders.get(String.valueOf(orderId));
        this.customerId = order.get("customerId").asInt();
        ArrayNode a = new ObjectMapper().valueToTree(order.get("items"));
        //this.items = a.;
        System.out.println(new ObjectMapper().valueToTree(order.get("items")));

        this.deliveryAddress = order.get("deliveryAddress").asText();
    }
//    public Order(int customerId, List<Integer> items, String deliveryAddress) {
//        setOrderId();
//        this.customerId = customerId;
//        this.items = items;
//        this.deliveryAddress = deliveryAddress;
//
//        calculateTotal();
//        this.status = "open";
//        this.orderDate =  LocalDateTime.now().toString();
//    }

    private void setOrderId() {
        LocalDate date = LocalDateTime.now().toLocalDate();
        int YYYY = date.getYear();
        int MM = date.getMonthValue();
        this.orderId = Long.parseLong(""+YYYY+MM+(orders.size()+1));
    }

    public long getCustomerId() {
        return customerId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
        saveOrder();
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

    private void saveOrder() {
        ObjectNode order = JSWriter.getNewJsonNode();

        ArrayNode itemsArray = new ObjectMapper().valueToTree(items);
        ArrayNode appliedPromoCodesArray = new ObjectMapper().valueToTree(appliedPromoCodes);
        order.putArray("items").addAll(itemsArray);
        order.putArray("appliedPromoCodes").addAll(appliedPromoCodesArray);

        order.put("customerId", customerId)
                .put("status", status)
                .put("total", total)
                .put("invoiceId", invoiceId)
                .put("paymentMethod", paymentMethod)
                .put("deliveryAddress", deliveryAddress)
                .put("phoneNumber", phoneNumber)
                .put("orderDate", orderDate);

        orders.set(String.valueOf(orderId), order);
        JSWriter.write(orders);
    }
    public static List<Integer> getOrders(int customerId) {
        List<Integer> ordersList = new ArrayList<>();
        for (JsonNode order: orders) {
            if (order.get("customerId").asInt()==customerId) {
                ordersList.add(order.asInt());
            }
        }
        return ordersList;
    }
    @Override
    public String toString() {
        return orders.get(String.valueOf(orderId)).toPrettyString();
    }
}
