package food.mahmoud;

import food.roba.Item;
import food.saif.Restaurant;

import java.util.ArrayList;

public class Menu {
    private String id;
    private Restaurant restaurant;
    ArrayList<Item> items = new ArrayList<>();

    public Menu(String id, Restaurant restaurant, ArrayList<Item> items) {
        this.id = id;
        this.restaurant = restaurant;
        this.items = items;
    }

    public void add(Item item) {}
    public void remove(Item item) {}

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
