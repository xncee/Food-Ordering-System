package food.roba;

import food.saif.Identifiable;

public class Item implements Identifiable {
    protected String id;
    protected String name;
    protected double price;
    protected String description;

    public Item(String id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // use a condition to ensure that the name is not empty.
        if (name.length()>1)
            this.name = name;
        else
            System.out.println("Food name shouldn't be empty!");
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        // use a condition to ensure that the price is a positive value.
        if (price>=0)
            this.price = price;
        else
            System.out.println("Price must be positive!");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +"name=" + name +", price=" + price +'}';
    }
}
