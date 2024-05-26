package food.saif.io;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your age: ");
        int age = input.nextInt();
        input.nextLine();
        System.out.println("Enter your name: ");
        String name = input.nextLine();
        // 7 9 Saif
        System.out.println("====");
        System.out.println(age);
        System.out.println(name);
    }

}
