package food.mahmoud;

import food.roba.Item;
import food.saif.Identifiable;
import food.saif.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Menu implements Identifiable {
    private String id;
    private Restaurant restaurant;
    List<Item> items = new ArrayList<>();

    public Menu(String id, Restaurant restaurant, List<Item> items) {
        this.id = id;
        this.restaurant = restaurant;
        this.items = items;
    }

    public void add(Item item) {}
    public void remove(Item item) {}

    public List<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    @Override
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
}
