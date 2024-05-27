package food.saif;

public class Review {
    private String id;
    private Customer customer;
    private int rating;
    private String comment;

    public Review(String id, Customer customer, int rating, String comment) {
        this.id = id;
        this.customer = customer;
        this.rating = rating;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
