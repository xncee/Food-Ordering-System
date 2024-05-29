package food.roba;

public class JunkFood extends Food implements isOrganic  {

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
        return super.toString()+"\nJunkFood{" + "description=" + description + '}';
    }
}
