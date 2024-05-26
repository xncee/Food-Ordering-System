package food.saif;

import food.saif.io.JsonFileWriter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    final static JsonFileWriter JSWriter = new JsonFileWriter("restaurants.json");
    static ObjectNode restaurants = JSWriter.getJsonNode();
    List<Integer> menu = new ArrayList<>();

    public Restaurant() {

    }
    int restaurantId;
    public int getNewRestaurantId() {
        return 0;
    }
    public void newRestaurant(String restaurantName, String address) {
        restaurants.put(
                String.valueOf(getNewRestaurantId()),
                JSWriter.getNewJsonNode()
                .put("name", restaurantName)
                .put("address", address)
        );
    }
    public void updateRestaurants() {
        JSWriter.write(restaurants);
    }
    public void updateMenu() {
        // restaurants.put()
        // you need to make a menu as array.
        updateRestaurants();
    }
    public int getRestaurantId() {
        return restaurantId;
    }

    public void addItem(int item) {
        menu.add(item);
        updateMenu();
    }
    public void removeItem(int item) {
        menu.remove(item);
        updateMenu();
    }
    public void displayMenu() {
        int j = 1;
        for (int i: menu) {
            JsonNode item = Item.getItem(i).get("name");
            System.out.println((j++)+") "+item.get("name")+"\n\t"+item.get("description")+"\n\t"+item.get("price"));
        }
    }
    public static void displayRestaurants() {
        int i = 1;
        for (JsonNode r: restaurants) {
            System.out.println((i++)+") "+r.get("name")+", "+r.get("address"));
        }
    }
}
