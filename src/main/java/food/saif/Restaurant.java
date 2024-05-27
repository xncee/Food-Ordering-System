package food.saif;

import com.fasterxml.jackson.databind.JsonNode;
import food.mahmoud.Menu;
import food.roba.Item;

import java.util.List;

public class Restaurant {
    private String id;
    private String name;
    private String location;
    private String description;
    private List<Review> reviews;
    private Menu menu;

    public Restaurant(String id, String name, String location, String description, Menu menu, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.menu = menu;
    }

    public static String getNewId() {
        return "REST";
    }

    public void displayMenu() {
        for (Item item: menu.getItems()) {
            System.out.println(item);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
