package be.odisee;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static void main() {
        // EnergyCalculable
        EnergyCalculable researchMission = new ResearchMission("Mars Life", "Mars", 210, 225.0, true, "Search for microbial life");
        EnergyCalculable cargoMission = new CargoMission("Supply Run 7", "Moon", 30, 384400.0, false, 12500);
        System.out.println(researchMission.calculateEnergy());
        System.out.println(cargoMission.calculateEnergy());
        // Equals
        ResearchMission m1 = new ResearchMission("Mars Life", "Mars",  210, 225.0, true, "Search for microbial life");
        ResearchMission m2 = new ResearchMission("Mars Life", "Mars",  500, 999.0, false, "Search for microbial life");
        System.out.println(m1.equals(m2));
        //
        // Array, List, Map, Set
        //
        ResearchMission marsLife = new ResearchMission("Mars Life", "Mars", 210, 225.0, true, "Search for microbial life");
        CargoMission supplyRun = new CargoMission("Supply Run 7", "Moon", 30, 384400.0, false, 12500);
        ResearchMission lunarResearch = new ResearchMission("Lunar Research", "Moon", 30, 384400.0, false, "Lunar Research");
        ExplorationMission explorer1 = new ExplorationMission("Explorer 1", "Space", 1000, 1000, true, 4);
        // Array
        Mission[] missionArray = {marsLife, supplyRun, lunarResearch, explorer1};
        printMissions(missionArray);
        // List
        List<Mission> missionList = new ArrayList<>();
        missionList = List.of(marsLife, supplyRun,  lunarResearch, explorer1);
        for  (Mission mission : missionList) {
            System.out.println(mission);
        }
        // Map
        Map<String, Mission> missionMap = new HashMap<>();
        missionMap.put("Mars Life", marsLife);
        missionMap.put("Supply Run 7", supplyRun);
        missionMap.put("Lunar Research", lunarResearch);
        missionMap.get("Mars Life").printMissionDetails();
        // Set
        Set<Mission> uniqueMissions = new HashSet<>();
        uniqueMissions.add(marsLife);
        uniqueMissions.add(supplyRun);
        uniqueMissions.add(lunarResearch);
        uniqueMissions.add(explorer1);
        ResearchMission newMarsLife = new ResearchMission("Mars Life", "Mars", 999, 999.0, false, "Completely differen research");
        uniqueMissions.add(newMarsLife);
        System.out.println(marsLife.equals(newMarsLife));
        System.out.printf("Number of unique missions: %d%n", uniqueMissions.size());
    }

    private static void printMissions(Mission[] missions) {
        System.out.println("=========================================");
        System.out.println("==          GALACTIC EXPLORER          ==");
        System.out.println("=========================================");
        int count = 1;
        for  (Mission m : missions) {
            System.out.printf("%nMission %d:%n", count);
            m.printMissionDetails();
            printCrew();
            countDown(m.isSuccessful());
            if (m.isSuccessful()) {
                System.out.printf("Successful: SUCCESS%n");
            } else {
                System.out.printf("Successful: FAILED%n");
            }
            count++;
        }
    }

    private static void countDown(boolean success) {
        int count = 5;
        System.out.printf("%nCountdown:%n");
        while (count > 0) {
            System.out.printf("%d%n", count);
            count--;
        }
        if (success) {
            System.out.printf("%nLiftoff!%n%n");
        }  else {
            System.out.printf("%nAbort!%n%n");
        }
    }

    public static void printCrew() {
        List<String> bemanning = new ArrayList<>();
        bemanning = List.of("Alice", "Bob", "Charlie");
        System.out.println("Crew:");
        for (String crewName : bemanning) {
            System.out.printf("- %s%n", crewName);
        }
    }
}
