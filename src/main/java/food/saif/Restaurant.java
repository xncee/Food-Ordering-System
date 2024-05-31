package food.saif;

import food.mahmoud.Menu;
import food.roba.Food;
import food.roba.HealthyFood;
import food.roba.Item;
import food.roba.JunkFood;

import java.util.List;

public class Restaurant extends User {
    private String location;
    private String description;
    private Menu menu;
    private List<Identifiable> reviews;

    public Restaurant(String id, String name, String phoneNumber, String location, String description, Menu menu, List<Identifiable> reviews) {
        super(id, name, phoneNumber);
        this.location = location;
        this.description = description;
        this.menu = menu;
        this.reviews = reviews;
    }

    public int getOrdersCount() {
        return reviews.size();
    }
    public double getRating() {
        if (reviews.isEmpty()) return 0.0;

        double sum = 0;
        for (Identifiable r: reviews) {
            if (r==null) continue;
            Review review = (Review) r;
            sum += review.getRating();
        }

        return (sum/reviews.size());
    }

    public void displayRestaurant() {
        System.out.println("\nID: "+getId());
        System.out.println("\tRestaurant name: "+getName());
        System.out.println("\tDescription: "+description);
        System.out.println("\tLocation: "+location);
        System.out.println("\tRating: "+getRating()+"/5.0"+" ("+reviews.size()+")");
    }
    public static void displayRestaurants() {
        for (Identifiable r: restaurantsList) {
            Restaurant restaurant = (Restaurant) r;
            if (restaurant==null) continue;
            System.out.println();
            restaurant.displayRestaurant();
        }
    }

    public void displayMenu() {
        int x = 1;
        System.out.println();
        for (Item i: menu.getItems()) {
            if (i==null) continue;
            Food foodItem = (Food) i;
            System.out.println(
                    (x++) + ". " +
                    foodItem.getName() + "\n\t"+
                    "Description: "+foodItem.getDescription()
            );
            if (foodItem.isHealthy()) {
                HealthyFood healthyFood = (HealthyFood) foodItem;
                System.out.println("\t"+"Ingredients: "+healthyFood.getIngredients());
                System.out.println("\t"+"Calories: "+healthyFood.getCalories());
                System.out.print("\tIsVegan: "+healthyFood.isVegan());
                System.out.print(", isGlutenFree: "+healthyFood.isGlutenFree());
                System.out.print("\n\tisOrganic: "+healthyFood.isOrganic());
                System.out.print(", isOrganicCertified: "+healthyFood.organicCertificateAgent() + "\n");
            }
            System.out.println("\t$"+foodItem.getPrice());
        }
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Identifiable> getReviews() {
        return reviews;
    }

    public void setReviews(List<Identifiable> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return super.toString()+"\nRestaurant{" +
                "location=" + location +
                ", description=" + description +
                ", menu=" + menu.getId() +
                ", reviews=" + (reviews.isEmpty()?null:reviews.get(0).getId()) +
                "}";
    }
}
