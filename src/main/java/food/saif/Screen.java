package food.saif;

public class Screen {
    public final static void clear() {
        try {
            Runtime.getRuntime().exec("clear");
        }
        catch (Exception e) {}
    }
}
