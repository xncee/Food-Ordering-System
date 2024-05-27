package food.roba;

/*
@Saif, May 26th 2024
Feedback:
1- Didn't follow naming conventions (for class name).
2- a zero-constructor was defined which is useless.
3- needs to define more methods.
 */
public class JunkFood extends Food implements isOrganic  {
    //private String description; -> moved to Item class

    //public JunkFood() {}
    public JunkFood(String id, String name, double price, String description, String category) {
        super(id, name, price, description, category, false);
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
