
package food.noor;

import food.saif.Application;
import food.saif.Identifiable;

public class Delivery implements DeliveryServices, Identifiable {
    private final double DELIVERY_RATE_PER_KM = 0.1;
    private String id;
    private String location;
    private String order;
    private Driver driver;
    private String status;
    private double distance;
    //private int deliveryTime; // in minutes

    public Delivery(String id, String location, String order, Driver driver, String status, double distance) {
        this.id = id;
        this.location = location;
        this.driver = driver;
        this.order = order;
        this.status = status;
        this.distance = distance;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public void cancel() {
        setStatus("canceled");
        Application.updateDeliveries();
    }

    @Override
    public double calculateDeliveryFee() {
        return distance*DELIVERY_RATE_PER_KM;
    }
    @Override
    public int calculateDeliveryTime() {
        // distance = speed*hours
        // hours = distance/speed
        // mins = hours*60
        double speed = 60.0; // km/h
        int deliveryTime = (int) ((distance/speed)*60);

        return deliveryTime;
    }

    //public void assignDriver(String driver , int deliveryId) {System.out.println("Driver name : " + driver + "Driver ID : " + deliveryId);}

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "driver=" + driver.getName() +
                ", status=" + status +
                ", deliveryFee=" + calculateDeliveryFee() +
                ", deliveryTime=" + calculateDeliveryTime() +
                '}';
    }
}