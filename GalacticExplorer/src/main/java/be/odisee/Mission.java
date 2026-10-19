package be.odisee;

import java.util.Objects;

public abstract class Mission {

    // FIELDS
    private String name;
    private String destination;
    private int duration;
    private double distance;
    private boolean successful;

    // METHODS
    public static double calculateReturnDistance(double distance) {
        return 2 * distance;
    }
    public abstract void printMissionDetails();
    public boolean equals(Object o) {
        if (!(o instanceof Mission)) {
            return false;
        }
        Mission m = (Mission) o;
        return m.getName().hashCode() == this.name.hashCode() && m.getDestination().hashCode() == this.destination.hashCode();
    }
    public int hashCode() {
        return Objects.hash(name, destination);
    }

    // CONSTRUCTOR
    public Mission(String name, String destination, int duration, double distance, boolean successful) {
        this.name = name;
        this.destination = destination;
        this.duration = duration;
        this.distance = distance;
        this.successful = successful;
    }

    // GETTERS
    public String getName() {
        return name;
    }
    public String getDestination() {
        return destination;
    }
    public int getDuration() {
        return duration;
    }
    public double getDistance() {
        return distance;
    }
    public boolean isSuccessful() {
        return successful;
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

}
