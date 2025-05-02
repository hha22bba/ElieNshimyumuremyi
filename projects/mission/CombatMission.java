package mission;
import java.util.*;
public class CombatMission extends Mission{
    public CombatMission(String missionId, String missionName, Date start, Date end) {
        super(missionId, missionName, start, end);
    }

    @Override
    public void assignTask() {
        if (assignedPersonnel.size() < 3) {
            System.out.println("CombatMission requires at least 3 personnel.");
            return;
        }
        for (Personnel p : assignedPersonnel) {
            System.out.println("Assigned combat task to: " + p.getPersonnelName());
        }
    }

    @Override
    public void allocateResources(List<Resource> availableResources) {
        for (Resource r : availableResources) {
            if ((r.getResourceName().equalsIgnoreCase("Weapon") || r.getResourceName().equalsIgnoreCase("Vehicle")) && r.getQuantity() > 0) {
                allocatedResources.add(r);
                r.setQuantity(r.getQuantity() - 1);
                System.out.println(r.getResourceName() + " allocated to CombatMission.");
            }
        }
    }

    @Override
    public void trackMissionProgress() {
        System.out.println("Monitoring combat outcomes...");
        status = "IN_PROGRESS";
    }

    @Override
    public void generateMissionReport() {
        System.out.println("Mission Report: " + missionName);
        System.out.println("Status: " + status);
        System.out.println("Personnel Assigned: " + assignedPersonnel.size());
        System.out.println("Resources Used: ");
        for (Resource r : allocatedResources) {
            System.out.println("- " + r.getResourceName());
        }
    }

}
