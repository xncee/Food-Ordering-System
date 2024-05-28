package food.noor;

import food.saif.Identifiable;

public class Driver implements Identifiable {
    private String id;
    private String name;
    private String phoneNumber;

    public Driver(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name=" + name +
                ", phoneNumber=" + phoneNumber +
                "}";
    }
}
