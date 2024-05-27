package food.roba;

import java.util.Scanner;
/*
@Saif, May 26th 2024
Feedback:
1- Didn't follow naming conventions (for class name).
2- a zero-constructor was defined which is useless.
3- 'setName' and 'setPrice' methods didn't have a condition to ensure that the input value is valid.
4- 'setName' and 'setPrice' weren't used in constructor to ensure that the input value is valid.
5- needs to define more methods.
*/
public class Food extends Item {
    //protected String name;
    //protected double price; -> moved to Item class.
    protected String category;
    /*
    In restaurants, meals are categorized and referred to using specific terms. Here are the common terms used for different types of meals and courses:

    Breakfast: The first meal of the day, typically served in the morning.
    Brunch: A meal that combines breakfast and lunch, usually served late in the morning or early afternoon.
    Lunch: The meal eaten around midday.
    Dinner: The main meal of the day, usually eaten in the evening.
    Appetizer (or Starter): A small dish served before the main course to stimulate the appetite.
    Main Course (or Entrée): The primary dish of a meal, typically larger and more substantial than the other courses.
    Dessert: A sweet course served at the end of a meal.
    Side Dish (or Side): Additional food items served alongside the main course, such as vegetables, salad, or bread.
    Snack: A small portion of food eaten between meals.
    */
    protected boolean isHealthy;

    //public Food() {}
    public Food(String id, String name, double price, String description, String category, boolean isHealthy) {
        super(id, name, price, description);
        this.category = category;
        this.isHealthy = isHealthy;
    }

    // idk what is this supposed to be for.
    public double quantity() {
        Scanner input = new Scanner(System.in);
        System.out.println("enter the quantity;");
        double m = input.nextDouble();
        // why?
        return  getPrice();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    @Override
    public String toString() {
        return super.toString()+"\nFood{" + "name=" + name + ", price=" + price + '}';
    }

}
