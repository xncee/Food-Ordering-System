package food.noor;

import food.saif.Identifiable;
import food.saif.User;

public class Driver extends User implements Identifiable {

    public Driver(String id, String name, String phoneNumber) {
        super(id, name, phoneNumber);
    }
    @Override
    public String toString() {
        return "Driver{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", phoneNumber=" + getPhoneNumber() +
                "}";
    }
}
