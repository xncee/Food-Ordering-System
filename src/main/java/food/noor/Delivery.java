
package food.noor;

import food.saif.Identifiable;

/*
@Saif, May 28th 2024
Feedback:
1- zero constructor isn't needed.
2- 'id' should be String.
2- 'location' data field is needed.
3- 'order' data type should be Order not String.
4- 'driver' data type should be Driver not String.
5- in 'calculateDeliveryFee' method, deliveryTime isn't needed to calculate the fee.
6- 'assignDriver' method was already defined in Order class.
 */
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
        System.out.println("Delivery status: " + status);
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