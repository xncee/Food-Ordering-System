package food.roba;

import java.util.Scanner;

public class Food extends Item {
    protected String category;
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
