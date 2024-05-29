package food.saif;

public class Review implements Identifiable {
    private String id;
    private String customer;
    private int rating; // ★ ★ ★ ★ ★
    private String comment;

    public Review(String id, String customer, int rating, String comment) {
        this.id = id;
        this.customer = customer;
        setRating(rating);
        this.comment = comment;
    }

    // getNewId()
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating>=0 && rating<=5)
            this.rating = rating;
        else
            System.out.println("rating must be between 0 and 5.");
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id +
                ", customer=" + customer +
                ", rating=" + rating +
                ", comment=" + comment +
                '}';
    }
}
