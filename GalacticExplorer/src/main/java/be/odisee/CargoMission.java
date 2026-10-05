package be.odisee;

public class CargoMission extends Mission implements EnergyCalculable {

    // FIELDS
    private double cargoWeight;

    // METHODS
    @Override
    public void printMissionDetails() {
        System.out.println("=== Cargo Mission ===");
        System.out.printf("Mission name: %s%n", super.getName());
        System.out.printf("Destination: %s%n", super.getDestination());
        System.out.printf("Duration: %d days%n", super.getDuration());
        System.out.printf("Distance: %.1f km%n", super.getDistance());
        System.out.printf("Return distance: %.1f million km%n", calculateReturnDistance(super.getDistance()));
        System.out.printf("Cargo weight: %.1f kg%n", cargoWeight);
    }
    public double calculateEnergy() {
        return super.getDuration() * 1000 + cargoWeight * 0.5;
    }

    // CONSTRUCTOR
    public CargoMission(String name, String destination, int duration, double distance, boolean successful, double cargoWeight){
        super(name, destination, duration, distance, successful);
        this.cargoWeight = cargoWeight;
    }

    // GETTERS
    public double getCargoWeight() {
        return cargoWeight;
    }

    // SETTERS
    public void setCargoWeight(double cargoWeight) {
        this.cargoWeight = cargoWeight;
    }
}
