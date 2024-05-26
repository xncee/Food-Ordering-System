package food.roba;

/*
Feedback:
1- Didn't follow naming conventions (for class name).
2- a zero-constructor was defined which is useless.
3- needs to define more methods.
 */
public class JunkFood extends Food implements Organic {
    private String description;
    //public JunkFood() {}
    public JunkFood(String name, double price, String description ) {
        super(name, price);
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String organicCertificateAgent() {
        return "It has no certificateAgent.";
    }

    @Override
    public String toString() {
        //return "JunkFood{" +super.toString()+ "description=" + description + '}';
        // this is better:
        return super.toString()+"\nJunkFood{" + "description=" + description + '}';
    }
  
}
