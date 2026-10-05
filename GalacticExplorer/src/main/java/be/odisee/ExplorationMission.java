package be.odisee;

public class ExplorationMission extends Mission implements EnergyCalculable {

    // FIELDS
    private int numberOfPlanets;

    // METHODS
    @Override
    public void printMissionDetails() {
        System.out.println("=== Research Mission ===");
        System.out.printf("Mission name: %s%n", super.getName());
        System.out.printf("Destination: %s%n", super.getDestination());
        System.out.printf("Duration: %d days%n", super.getDuration());
        System.out.printf("Distance: %.1f million km%n", super.getDistance());
        System.out.printf("Return distance: %.1f million km%n", calculateReturnDistance(super.getDistance()));
        System.out.printf("Number of planets: %d%n", numberOfPlanets);
    }
    public double calculateEnergy() {
        return super.getDuration() * 2000 + numberOfPlanets * 5000;
    }

    // CONSTRUCTOR
    public ExplorationMission(String name, String destination, int duration, double distance, boolean successful, int numberOfPlanets) {
        super(name, destination, duration, distance, successful);
        this.numberOfPlanets = numberOfPlanets;
    }

    // GETTERS
    public int getNumberOfPlanets() {
        return numberOfPlanets;
    }

    // SETTERS
    public void setNumberOfPlanets(int numberOfPlanets) {
        this.numberOfPlanets = numberOfPlanets;
    }
}
