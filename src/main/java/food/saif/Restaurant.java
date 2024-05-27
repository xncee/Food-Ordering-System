package food.saif;

import com.fasterxml.jackson.databind.JsonNode;

public class Restaurant {
    private String id;
    private String name;
    private Menu menu;

    public Restaurant() {

    }

    public static String getNewRestaurantId() {
        return "R";
    }


    public void addItem(Item item) {
        menu.add(item);
    }
    public void removeItem(Item item) {
        menu.remove(item);
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
