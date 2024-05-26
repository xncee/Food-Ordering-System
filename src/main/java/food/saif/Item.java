package food.saif;

import food.saif.io.JsonFileWriter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Item {
    final static JsonFileWriter JSWriter = new JsonFileWriter("items.json");
    static ObjectNode items = JSWriter.getJsonNode();
    public Item() {
    }
    private static int getNewItemId() {
        return items.size()+1;
    }
    public static JsonNode getItem(int itemId) {
        return items.get(itemId);
    }
    public static int addItem(int restaurantId, String itemName, String itemDescription, double itemPrice) {
        int itemId = getNewItemId();
        items.put(
                String.valueOf(itemId),
                JSWriter.getNewJsonNode()
                        .put("restaurantId", restaurantId)
                        .put("name", itemName)
                        .put("description", itemDescription)
                        .put("price", itemPrice)
        );
        updateItems();
        return itemId;
    }
    public static boolean removeItem(int itemId) {
        if (items.get(itemId)!=null) {
            items.remove(String.valueOf(itemId));
            updateItems();
            return true;
        }
        return true;
    }
    private static void updateItems() {
        JSWriter.write(items);
    }
    public static double getPrice(long item) {
        return items.get(String.valueOf(item)).get("price").asDouble();
    }
}
