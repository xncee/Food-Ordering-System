package food.saif;

import food.saif.io.JsonFileWriter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.LocalDateTime;

public class Login {

    static JsonFileWriter JSWriter = new JsonFileWriter("users.json");
    static ObjectNode users = JSWriter.getJsonNode();

    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String registrationDate;
    public boolean isLoggedIn;
    public Login() {

    }

    private void updateUsers() {
        JSWriter.write(users);
    }
    public boolean isUsernameTaken(String username) {
        return (users.get(username)!=null);
    }
    public boolean isEmailTaken(String email) {
        //return (users.findParent(email)!=null);
        return false;
    }
    public boolean validatePassword(String password) {
        if (password.length()>=8) {
            return true;
        }
        System.out.println("Your password must consist of at least 8 characters.");
        return false;
    }
    // String s = "saif.noor.roba";
    // sout(s.split("."))[2] -> {"saif", "noor", "roba"}
    private void loggedIn(String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        try {
            this.registrationDate = users.get(username).get("registrationDate").asText();
        }
        catch (NullPointerException e) {
            this.registrationDate = LocalDateTime.now().toString();
        }
        isLoggedIn = true;
    }
    private void saveUser() {
        boolean isNewUser = !isUsernameTaken(username);
        int customerId;
        String regDate;
        if (isNewUser) {
            customerId = Customer.getNewCustomerId();
            regDate = String.valueOf(LocalDateTime.now());
        }
        else {
            customerId = users.get(username).get("customerId").asInt();
            regDate = users.get(username).get("registrationDate").asText();
        }
        this.registrationDate = regDate;
        users.put(
                username,
                JSWriter.getNewJsonNode()
                        .put("customerId", customerId)
                        .put("password", password)
                        .put("email", email)
                        .put("phoneNumber", phoneNumber)
                        .put("registrationDate", regDate)
        );
        updateUsers();
    }
    public boolean checkPassword(String password) {
        JsonNode PASSWORD = users.get(username).get("password");
        return PASSWORD!=null && password.equals(PASSWORD.asText());
    }
    public boolean signIn(String username, String password) {
        if (!isUsernameTaken(username)) {
            System.out.println("Username doesn't exist!");
            return false;
        }

        this.username = username;
        if (checkPassword(password)) {
            loggedIn(username, password, users.get(username).get("email").asText(),  users.get(username).get("phoneNumber").asText());
            return true;
        }
        else {
            System.out.println("Incorrect password!");
            return false;
        }
    }
    public boolean signUp(String username, String password, String email, String phoneNumber, String customerName) {
        username = username.toLowerCase(); // SaiF -> saif
        password = password.toLowerCase();
        email = email.toLowerCase();
        customerName = customerName.toLowerCase();

        if (!isUsernameTaken(username)) {
            loggedIn(username, password, email, phoneNumber);
            saveUser();
            Customer.newCustomer(customerName);
            return true;
        }
        else {
            System.out.println("Username is taken!");
        }
        return false;
    }
    public boolean changePassword(String newPassword) {
        if (validatePassword(newPassword)) {
            this.password = newPassword;
            saveUser();
            isLoggedIn = false;
            return true;
        }
        return false;
    }
    public String getCustomerId() {
        if (isLoggedIn)
            return users.get(username).get("customerId").asInt();
        return -1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }
}