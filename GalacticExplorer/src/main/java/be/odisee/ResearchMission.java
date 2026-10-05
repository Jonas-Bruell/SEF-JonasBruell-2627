package be.odisee;

public class ResearchMission extends Mission implements EnergyCalculable {

    // FIELDS
    private String researchTopic;

    // METHODS
    @Override
    public void printMissionDetails() {
        System.out.println("=== Research Mission ===");
        System.out.printf("Mission name: %s%n", super.getName());
        System.out.printf("Destination: %s%n", super.getDestination());
        System.out.printf("Duration: %d days%n", super.getDuration());
        System.out.printf("Distance: %.1f million km%n", super.getDistance());
        System.out.printf("Return distance: %.1f million km%n", calculateReturnDistance(super.getDistance()));
        System.out.printf("Research topic: %s%n", researchTopic);
    }
    public double calculateEnergy() {
        return super.getDuration() * 1500;
    }

    // CONSTRUCTOR
    public ResearchMission(String name, String destination, int duration, double distance, boolean successful, String researchTopic){
        super(name, destination, duration, distance, successful);
        this.researchTopic = researchTopic;
    }

    // GETTERS
    public String getResearchTopic() {
        return researchTopic;
    }

    // SETTERS
    public void setResearchTopic(String researchTopic) {
        this.researchTopic = researchTopic;
    }
}
