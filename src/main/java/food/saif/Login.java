package food.saif;

import food.saif.design.Color;
import food.saif.io.JsonFileWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Login implements ApplicationData, Color {
    private String id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;

    public boolean isLoggedIn;
    public Login(String username, String password) {
        isLoggedIn = signIn(username, password);
    }
    public Login(String username, String password, String name, String email, String phoneNumber) {
        isLoggedIn = signUp(username, password, name, email, phoneNumber);
        if (isLoggedIn) addNewUser();
    }

    private void addNewUser() {
        if (customersJson.get(id)==null) {
            usersJson.put(
                    username,
                    JsonFileWriter.getNewJsonNode()
                            .put("id", id)
                            .put("username", username)
                            .put("password", password)
                            .put("phoneNumber", phoneNumber)
            );
            LocalDateTime datetime = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a")));
            Customer customer = new Customer(id, name, email, phoneNumber, 0.0, datetime);
            Application.add(customer);
        }
    }

    private boolean signIn(String username, String password) {
        username = username.toLowerCase();
        if (usersJson.get(username)==null) return false;

        if (!usersJson.get(username).get("password").asText().equals(password)) return false;

        this.id = usersJson.get(username).get("id").asText();
        this.username = username;
        this.password = password;
        this.name = customersJson.get(id).get("name").asText();
        this.email = usersJson.get(username).get("email").asText();
        this.phoneNumber = usersJson.get(username).get("phoneNumber").asText();

        return true;
    }

    private boolean isUsernameAvailable(String username) {
        username = username.toLowerCase();
        return usersJson.get(username)==null;
    }

    private boolean isPasswordValid(String password) {
        return password.length()>=8;
    }

    private boolean isEmailValid(String email) {
        email = email.toLowerCase();
        if (!email.contains("@")) return false;
        if (!email.contains(".")) return false;
        if (email.split("@")[0].isEmpty()) return false;
        if (email.split("@")[1].isEmpty()) return false;
        // ensuring that it contains one '@':
        if (email.length()-email.replace("@", "").length()>1) return false;
        if (!email.split("@")[1].contains(".")) return false;
        if (!email.split("@")[1].split("[.]")[0].isEmpty()) return false;
        if (!email.split("@")[1].split("[.]")[1].isEmpty()) return false;

        return true;
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return true;
    }
    private boolean signUp(String username, String password, String name, String email, String phoneNumber) {
        username = username.toLowerCase();
        if (!isUsernameAvailable(username)) {
            System.out.println(RED+"Username isn't available!"+RESET);
            return false;
        }
        if (!isPasswordValid(password)) {
            System.out.println(RED+"Invalid password!"+RESET);
            System.out.println(YELLOW+"*password must be at least 8 characters."+RESET);
            return false;
        }
        if (!isEmailValid(email)) {
            System.out.println(RED+"Invalid email!"+RESET);
            System.out.println(YELLOW+"*Example: email@address.com"+RESET);
            return false;
        }
        if (!isPhoneNumberValid(phoneNumber)) {
            System.out.println(RED+"Invalid phoneNumber!"+RESET);
            System.out.println(YELLOW+"*Example: +9627X XXXX XXXX"+RESET);
            return false;
        }

        this.id = Application.getNewId("USER", customersList);
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;

        return true;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}