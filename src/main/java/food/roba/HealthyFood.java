
package food.roba;

/*
Feedback:
1- some spelling mistakes in data fields and didn't follow naming conventions.
2- zero-constructor was defined which is useless.
3- 'setVegan' and 'setGlutenFree' methods had a logical error as they used '=' to check if value is true, which is incorrect
    because the expression 'value=true' will assign value to true and will always return true.
4- 'setVegan' and 'setGlutenFree' methods didn't have an else statement to demonstrate that the input value is invalid.
5- 'setVegan' and 'setGlutenFree' methods have another logical error as they don't allow a healthy food to be not vegan or not gluten-free.
6- 'setVegan' and 'setGlutenFree' weren't used in constructor to ensure that the input value is valid.
*/
public class HealthyFood extends Food implements Organic {
    private String description;
    private String ingredients;
    private int calories;
    private boolean isVegan;
    private boolean isGlutenFree;
    private String isOrganic;

    //public HealthyFood() {}

    public HealthyFood(String name, double price, String description, int calories, boolean isVegan, boolean isGlutenFree, String ingredients) {
        super(name, price);
        this.description = description;
        this.calories = calories;
        //this.isVegan = isVegan;
        //this.isGlutenFree = isGlutenFree;
        setVegan(isVegan);
        setGlutenFree(isGlutenFree);
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsOrganic() {
        return isOrganic;
    }

    public void setIsOrganic(String isOrganic) {
        this.isOrganic = isOrganic;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public boolean isGlutenFree() {
        return isGlutenFree;
    }

    public void setVegan(boolean isVegan) {
        //if(isVegan=true) -> '=='
        // still unnecessary though.
        if (isVegan)
            this.isVegan = isVegan;
        // else?
        else
            System.out.println("Healthy food must be vegan!");
    }

    public void setGlutenFree(boolean isGlutenFree) {
        //if(glutenFree=true) -> '=='
        // still unnecessary though.
        if (isGlutenFree)
            this.isGlutenFree = isGlutenFree;
        // else?
        else
            System.out.println("Healthy food must be gluten free!");
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String organicCertificateAgent() {
       return "It has alot of certificateAgent.";
    }

    @Override
    public String toString() {
        //return "HealthyFood{" +super.toString()+ "description=" + description + ", calories=" + calories + ", isVegan=" + isVegan + ", isGlutenFree=" + isGlutenFree + ", ingredients=" + ingredients + '}';
        // this is better:
        return super.toString()+"\nHealthyFood{" + "description=" + description + ", calories=" + calories + ", isVegan=" + isVegan + ", isGlutenFree=" + isGlutenFree + ", ingredients=" + ingredients + '}';
    }
}
