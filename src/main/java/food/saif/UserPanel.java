package food.saif;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserPanel {
    static Scanner input = new Scanner(System.in);
    static Login user;
    static Customer customer;
    static Payment payment;
    static Restaurant restaurants;

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        //System.out.println("Welcome to ");
        navPage();

    }
    public static void loginPage() {
        user = new Login();
        System.out.println("\n# Login page");
        System.out.println("1) Sign in");
        System.out.println("2) Sign up");
        System.out.print("=> ");
        int choice = getUserInput(new int[] {1, 2});

        boolean newCustomer = choice==2;
        boolean isUsernameVaild = false, isPasswordValid = false;
        String username = "", password = "", email = "";
        while (!user.isLoggedIn) {
            if (!isUsernameVaild) {
                System.out.println("Enter username: ");
                username = input.nextLine();
            }
            if (choice==1) isUsernameVaild = true;

            if (!isPasswordValid) {
                System.out.println("Enter password: ");
                password = input.nextLine();
            }

            if (!user.validatePassword(password)) {
                continue;
            }
            isPasswordValid = true;


            if (newCustomer) {
                if (user.isUsernameTaken(username)) {
                    System.out.println("this username is taken!");
                    continue;
                }
                isUsernameVaild = true;

                System.out.println("Enter email: ");
                email = input.nextLine();
                if (!user.validateEmail(email)) {
                    continue;
                }

                System.out.println("Enter your name: ");
                String customerName = input.nextLine();

                System.out.println("Enter your phone number: ");
                String phoneNumber = input.nextLine();

                user.signUp(username, password, email, phoneNumber, customerName);
            }

            else if (choice==1) {
                if (!user.signIn(username, password)) {
                    isUsernameVaild = false;
                    isPasswordValid = false;
                }

            }
        }
        //Screen.clear();
        printHr();
        System.out.println("\nYou have been successfully logged in.");

    }
    public static void printHr() {
        System.out.println("============================");
    }
    public static void navPage() {
        if (user==null || !user.isLoggedIn) {
            loginPage();
        }
        int customerId = user.getCustomerId();
        customer = new Customer(customerId);

        System.out.println("\n# Navigation Page");
        System.out.println("1) Your Account");
        System.out.println("2) Order");
        System.out.println("3) Cart");
        System.out.println("99) <<");
        System.out.print("=> ");
        int choice = getUserInput(new int[] {1, 2, 3, 99});
        if (choice==1) {
            accPage();
        }
        else if (choice==2) {
            orderPage();
            printHr();
            Restaurant.displayRestaurants();
            // restasurant Page
        }
        else if (choice==3) {

        }
        else if (choice==99) {
            loginPage();
            navPage();

        }
    }
    public static void accPage() {
        System.out.println("\n# Account Page");
        System.out.println("1) Information (username, email, balance, ...)");
        System.out.println("2) Change password");
        System.out.println("3) Wallet");
        System.out.println("99) <<");
        System.out.print("=> ");
        int choice = getUserInput(new int[] {1, 2, 3, 99});
        if (choice==1) {
            printHr();
            System.out.println(customer);
            System.out.println("Username: "+user.getUsername());
            System.out.println("Email: "+user.getEmail());
            System.out.println("Phone Number: "+user.getPhoneNumber());
            System.out.println("Registration Date: "+user.getRegistrationDate());
            accPage();
        }
        else if (choice==2) {
            while (true) {
                System.out.println("Enter current password: ");
                String currentPassword = input.nextLine();
                System.out.println("Enter new password: ");
                String newPassword = input.nextLine();

                if (currentPassword.equals(newPassword)) {
                    System.out.println("the new password cannot be the same as the current password.");
                }
                if (user.checkPassword(currentPassword)) {
                    if (user.validatePassword(newPassword)) {
                        user.changePassword(newPassword);
                        System.out.println("Your password has been changed successfully.");
                        System.out.println("You have been logged out. Please log in again.");
                        navPage();
                        break;
                    }
                }
                else {
                    System.out.println("Current password is incorrect.");
                }
            }
        }
        else if (choice==3) {
            walletPage();
        }
        else if (choice==99) {
            navPage();
        }
    }
    public static void orderPage() {
        System.out.println("\n# Order Page");
        System.out.println("1) Display restaurants");
        System.out.println("2) ");
        System.out.println("99) <<");
        int choice = getUserInput(new int[] {1, 2, 99});
        if (choice==1) {
            Restaurant.displayRestaurants();
        }
        else if (choice==2) {

        }
        else if (choice==99) {
            navPage();
        }
    }
    public static void walletPage() {
        System.out.println("\n# Wallet Page");
        System.out.println("** Balance: $"+customer.getBalance());
        System.out.println("1) Add balance");
        System.out.println("2) Add credit card");
        System.out.println("99) <<");
        int choice = getUserInput(new int[] {1, 2, 99});
        if (choice==1) {
            System.out.println("Enter amount: ");
            double amount = input.nextInt(); input.nextLine();
            System.out.println("# Select payment method");
            System.out.println("1) Credit card");
            System.out.println("2) Cash");
            customer.addBalance(amount);
        }
        else if (choice==2) {
            // add payment method
        }
        else if (choice==99) {
            accPage();
        }
    }
    // 1, 2
    public static int getUserInput(int[] choices) {
        int choice;
        try {
            choice = input.nextInt();
            input.nextLine();
            for (int c: choices) {
                if (c==choice) {
                    return choice;
                }
            }
        }
        catch (Exception e) {
            input.nextLine();
        }

        System.out.println("Invalid input!");
        return getUserInput(choices);
    }
}
