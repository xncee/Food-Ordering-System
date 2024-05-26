package food.roba;

// Team leader provided feedback for each class and applied some tiny changes on this code.
// This isn't actually needed as the Main method will be implemented by Saif.
// Roba's code will be put in package 'Roba' and integrated with the project.
// Team leader might ask you to define more methods if needed.

/*
Feedback:
1- This class is well-implemented and doesn't contain any logical errors.
2- 'for' can be replaced with 'for-each'.
3- It's recommended to use 'Scanner' to get inputs from user. (use while and sentinel value)
4- It's also recommended to use 'List' instead of array as array is fixed-size while List is dynamic-size (its size can be changed).
   *you can know more about ArrayList here: https://www.youtube.com/watch?v=SV1EdFvhOCw
 */
public class Main {
    public static void main(String[] args) {
        Food[] m1 = new Food[7];
        m1[0] = new HealthyFood("fish",
                5,
                "it has a lot of protein and vitamins",
                150,
                false,
                true,
                "fish sweet potatoes and broccoli");
        m1[1] = new JunkFood("pizza",
                5.8,
                "it came wih large sama cola");

        // this can be replaced with for-each
        for (int i=0; i<3; i++) {
            System.out.println(m1[i].toString());
            if (m1[i] instanceof HealthyFood) {
                System.out.println(((HealthyFood) m1[i]).organicCertificateAgent());
            }
            else if (m1[i] instanceof JunkFood){
                System.out.println(((JunkFood) m1[i]).organicCertificateAgent());
            }
        }
    }
}
