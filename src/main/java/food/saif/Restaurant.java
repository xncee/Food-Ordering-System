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
            Review review = (Review) r;
            sum += review.getRating();
        }

        return (sum/reviews.size());
    }
    public void displayMenu() {
        int x = 1;
        for (Item i: menu.getItems()) {
            Food foodItem = (Food) i;
            System.out.println(
                    (x++) + ". " +
                    foodItem.getName() + "\n\t"+
                    foodItem.getDescription()
            );
            if (foodItem.isHealthy()) {
                System.out.println("\t"+"Ingredients: "+((HealthyFood) foodItem).getIngredients());
                System.out.println("\t"+"Calories: "+((HealthyFood) foodItem).getCalories());
                System.out.print(", isVegan: "+((HealthyFood) foodItem).isVegan());
                System.out.println(", isGlutenFree: "+((HealthyFood) foodItem).isGlutenFree());
                System.out.print("\n\t"+", isOrganic: "+((HealthyFood) foodItem).isOrganic());
                System.out.println(", isOrganicCertified: "+((HealthyFood) foodItem).organicCertificateAgent());
            }
            System.out.println("\n\t"+"$"+foodItem.getPrice());
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
