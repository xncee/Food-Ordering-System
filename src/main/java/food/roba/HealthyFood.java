package food.roba;

/*
@Saif, May 26th 2024
Feedback:
1- some spelling mistakes in data fields and didn't follow naming conventions.
2- zero-constructor was defined which is useless.
3- 'setVegan' and 'setGlutenFree' methods had a logical error as they used '=' to check if value is true, which is incorrect
    because the expression 'value=true' will assign value to true and will always return true.
4- 'setVegan' and 'setGlutenFree' methods didn't have an else statement to demonstrate that the input value is invalid.
5- 'setVegan' and 'setGlutenFree' methods have another logical error as they don't allow a healthy food to be not vegan or not gluten-free.
6- 'setVegan' and 'setGlutenFree' weren't used in constructor to ensure that the input value is valid.
*/
public class HealthyFood extends Food implements isOrganic {
    //private String description; -> moved to Item class.
    private String ingredients;
    private int calories;
    private boolean isVegan;
    private boolean isGlutenFree;
    private boolean isOrganic;

    //public HealthyFood() {}

    public HealthyFood(String id, String name, double price, String description, String category, int calories, boolean isVegan, boolean isGlutenFree, boolean isOrganic, String ingredients) {
        super(id, name, price, description, category, true);
        this.calories = calories;
        this.isVegan = isVegan;
        this.isGlutenFree = isGlutenFree;
        this.isOrganic = isOrganic;
        this.ingredients = ingredients;
    }

    public boolean isOrganic() {
        return isOrganic;
    }

    public void setIsOrganic(boolean isOrganic) {
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
