package food.roba;

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
        return super.toString()+"\nHealthyFood{" + "description=" + description + ", calories=" + calories + ", isVegan=" + isVegan + ", isGlutenFree=" + isGlutenFree + ", ingredients=" + ingredients + '}';
    }
}
