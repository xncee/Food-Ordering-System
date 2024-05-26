package food.roba;

/*
Feedback:
1- Didn't follow naming conventions (for class name).
2- a zero-constructor was defined which is useless.
3- 'setName' and 'setPrice' methods didn't have a condition to ensure that the input value is valid.
4- 'setName' and 'setPrice' weren't used in constructor to ensure that the input value is valid.
5- needs to define more methods.
 */
public class Food {
    protected String name;
    protected double price;

    //public Food() {}
    public Food(String name, double price) {
        //this.name = name;
        //this.price = price;
        setName(name);
        setPrice(price);
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
            System.out.println("Food price must be positive!");
    }

    @Override
    public String toString() {
        return "Food{" + "name=" + name + ", price=" + price + '}';
    }
}
