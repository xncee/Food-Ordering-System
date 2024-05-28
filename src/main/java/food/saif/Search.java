package food.saif;

import java.util.ArrayList;
import java.util.List;

public class Search implements ApplicationData {
    public static List<Restaurant> findRestaurant(String searchKey, String searchQuery) {
        List<Restaurant> restaurants = new ArrayList<>();
        for (Identifiable restaurant: restaurantsList) {
            String s = switch (searchKey) {
                case "id":
                    yield ((Restaurant) restaurant).getName();
                case "name":
                    yield ((Restaurant) restaurant).getPhoneNumber();
                case "description":
                    yield ((Restaurant) restaurant).getDescription();
                case "location":
                    yield ((Restaurant) restaurant).getLocation();
                default:
                    System.out.println("Invalid searchKey!");
                    yield restaurant.getId();
            };

            if (s==null) break;
            s = s.toLowerCase();
            searchQuery = searchQuery.toLowerCase();

            if (s.equals(searchQuery)) {
                restaurants.add((Restaurant) restaurant);
            }
            else if ((searchKey.equals("name") || searchKey.equals("phoneNumber")) && s.startsWith(searchQuery)) {
                restaurants.add((Restaurant) restaurant);
            }
            else if ((searchKey.equals("description") && s.contains(searchQuery))) {
                restaurants.add((Restaurant) restaurant);
            }
        }
        return restaurants;
    }
}
