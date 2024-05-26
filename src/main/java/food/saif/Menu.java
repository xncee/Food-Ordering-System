package food.saif;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Menu extends Restaurant {
    private JsonNode menu;
    int restaurantId;
    String restaurantName;
    String address;

    public Menu(int restaurantId) {
        this.restaurantId = restaurantId;
        this.address = restaurants.get(restaurantId).get("address").asText();
        this.restaurantName = restaurants.get(restaurantId).get("name").asText();
        menu = restaurants.get(restaurantId);
    }
    public JsonNode getMenu() {
        return menu;
    }
    public void addItem() {

    }
    public void updateMenu() {
        ObjectNode restaurant = JSWriter.getNewJsonNode();

        ArrayNode itemsArray = new ObjectMapper().valueToTree(menu);
        restaurant.putArray("menu").addAll(itemsArray);

        restaurant.put(
                String.valueOf(restaurantId),
                JSWriter.getNewJsonNode()
                        .put("name", restaurantName)
                        .put("address", address)
        );
        restaurants.set(String.valueOf(restaurantId), restaurant);
        JSWriter.write(restaurants);
    }
}
