package food.roba;

public class Main {
    public static void main(String[] args) {
        Food[] m1 = new Food[7];
        m1[0] = new HealthyFood(
                "HF1",
                "Fish",
                10,
                "it has a lot of protein and vitamins",
                "dinner",
                150,
                false,
                true,
                "fish sweet potatoes and broccoli"
                );
        m1[1] = new JunkFood(
                "JF1",
                "Pizza",
                5.8,
                "it came wih large sama cola",
                "snack"
        );

        // this can be replaced with for-each
        for (int i=0; i<2; i++) {
            System.out.println(m1[i].toString());
            if (m1[i] instanceof HealthyFood) {
                System.out.println(((HealthyFood) m1[i]).organicCertificateAgent());
            }
            else if (m1[i] instanceof JunkFood) {
                System.out.println(((JunkFood) m1[i]).organicCertificateAgent());
            }
            System.out.println();
        }
    }
}
