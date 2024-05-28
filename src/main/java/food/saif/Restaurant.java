package food.saif;

import food.mahmoud.Menu;
import food.roba.Item;

import java.util.List;

public class Restaurant extends User {
    private String location;
    private String description;
    private Menu menu;
    private List<Identifiable> reviews;

    public Restaurant(String id, String name, String phoneNumber, String location, String description, Menu menu, List<Identifiable> reviews) {
        super(id, name, phoneNumber);
        this.location = location;
        this.description = description;
        this.menu = menu;
        this.reviews = reviews;
    }

    public void displayMenu() {
        for (Item item: menu.getItems()) {
            System.out.println(item);
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Identifiable> getReviews() {
        return reviews;
    }

    public void setReviews(List<Identifiable> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return super.toString()+"\nRestaurant{" +
                "location=" + location +
                ", description=" + description +
                ", menu=" + menu.getId() +
                ", reviews=" + (reviews.isEmpty()?null:reviews.get(0).getId()) +
                "}";
    }
}
