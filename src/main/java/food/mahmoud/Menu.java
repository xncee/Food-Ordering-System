package food.mahmoud;

import food.roba.Item;
import food.saif.Identifiable;

import java.util.ArrayList;
import java.util.List;


// .add(x)
// .remove(x)
// .size(x)
// .get(x)
public class Menu implements Identifiable {
    private String id;
    private String restaurantId;
    List<Item> items; //== Item[] items;

    public Menu(String id, String restaurantId, List<Item> items) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.items = items;
    }

    public void add(Item item) {
        items.add(item);
    }
    public void remove(Item item) {
        items.remove(item);
    }

    public void displayMenu() {
        for (int i=0; i<items.size(); i++) {
            System.out.println(items.get(i));
        }
    }

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

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id  +
                ", restaurantId=" + restaurantId +
                ", items=" + items +
                "}";
    }
}
