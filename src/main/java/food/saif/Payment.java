package food.saif;

import food.saif.io.JsonFileWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Payment {//extends Customer {
    final static JsonFileWriter JSWriter = new JsonFileWriter("invoices.json");
    static ObjectNode invoices = JSWriter.getJsonNode();
    public int invoiceId;
    private int customerId;
    public double total;

    private Payment(double total, int customerId, int invoiceId) {
        this.total = total;
        this.invoiceId = invoiceId;
        this.customerId =  customerId;
        updateInvoice("unpaid");
    }
    public Payment(double total, int customerId, String paymentMethod) {
        this(total, customerId, invoices.size()+1);
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public boolean validateInvoiceId(int invoiceId) {
        return (invoices.get(String.valueOf(invoiceId))!=null);
    }
    private boolean deductBalance() {
        return true;
    }
    public boolean pay() {
        if (!validateInvoiceId(invoiceId))
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
                String.valueOf(invoiceId),
                JSWriter.getNewJsonNode()
                        .put("customerId", customerId)
                        .put("status", status)
                        .put("total", total)
        );
        JSWriter.write(invoices);
    }

}
