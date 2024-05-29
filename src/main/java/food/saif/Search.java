package food.saif;

import java.util.ArrayList;
import java.util.List;

public class Search implements ApplicationData {
    public static List<Restaurant> findRestaurant(String searchKey, String searchQuery) {
        List<Restaurant> restaurants = new ArrayList<>();
        for (Identifiable r: restaurantsList) {
            Restaurant restaurant = (Restaurant) r;
            String s = switch (searchKey) {
                case "id":
                    yield restaurant.getId();
                case "name":
                    yield restaurant.getName();
                case "description":
                    yield restaurant.getDescription();
                case "location":
                    yield restaurant.getLocation();
                default:
                    System.out.println("Invalid searchKey!");
                    yield restaurant.getId();
            };

            if (s==null) break;
            s = s.toLowerCase();
            searchQuery = searchQuery.toLowerCase();

            if (s.equals(searchQuery)) {
                restaurants.add(restaurant);
            }
            else if ((searchKey.equals("name") || searchKey.equals("location")) && s.startsWith(searchQuery)) {
                restaurants.add(restaurant);
            }
            else if ((searchKey.equals("description") && s.contains(searchQuery))) {
                restaurants.add(restaurant);
            }
        }
        return restaurants;
    }

    public static Promo findPromo(String searchKey, String searchQuery) {
        for (Promo promo: promosList) {
            String s = switch (searchKey) {
                case "code":
                    yield promo.getCode();
                default:
                    System.out.println("Invalid searchKey!");
                    yield promo.getCode();
            };

            //if (s==null) break;
            s = s.toLowerCase();
            searchQuery = searchQuery.toLowerCase();

            if (s.equals(searchQuery)) {
                return promo;
            }
        }
        return null;
    }

    public static List<Order> findOrder(String searchKey, String searchQuery) {
        List<Order> orders = new ArrayList<>();
        for (Identifiable o: ordersList) {
            Order order = (Order) o;
            String s = switch (searchKey) {
                case "id":
                    yield order.getId();
                case "customerId":
                    yield order.getCustomer().getId();
                case "address":
                    yield order.getDelivery().getLocation();
                default:
                    System.out.println("Invalid searchKey!");
                    yield order.getId();
            };

            if (s==null) break;
            s = s.toLowerCase();
            searchQuery = searchQuery.toLowerCase();

            if (s.equals(searchQuery)) {
                orders.add(order);
            }
            else if (searchKey.equals("address") && s.startsWith(searchQuery)) {
                orders.add(order);
            }
        }
        return orders;
    }
}
