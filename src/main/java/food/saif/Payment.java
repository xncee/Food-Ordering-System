package food.saif;

import food.saif.io.JsonFileWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Payment implements Identifiable {//extends Customer {
    final static JsonFileWriter JSWriter = new JsonFileWriter("invoices.json");
    static ObjectNode invoices = JSWriter.getJsonNode();
    public String id;
    private int customerId;
    public double total;

    private Payment(double total, int customerId, String id) {
        this.total = total;
        this.id = id;
        this.customerId =  customerId;
        updateInvoice("unpaid");
    }
    public Payment(double total, int customerId, String paymentMethod) {
        this(total, customerId, invoices.size()+1);
    }
    @Override
    public String getId() {
        return id;
    }

    public boolean validateInvoiceId(String invoiceId) {
        return (invoices.get(String.valueOf(invoiceId))!=null);
    }
    private boolean deductBalance() {
        return true;
    }
    public boolean pay() {
        if (!validateInvoiceId(id))
            return false;

        if (deductBalance()) {
            System.out.println("Payment proceeded.");
            updateInvoice("paid");
            return true;
        }
        return false;
    }
    private void updateInvoice(String status) {
        invoices.put(
                String.valueOf(id),
                JSWriter.getNewJsonNode()
                        .put("customerId", customerId)
                        .put("status", status)
                        .put("total", total)
        );
        JSWriter.write(invoices);
    }

}
