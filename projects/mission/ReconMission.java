package mission;
import java.util.*;
public class ReconMission extends Mission{
    public ReconMission(String missionId, String missionName, Date start, Date end) {
        super(missionId, missionName, start, end);
    }

    @Override
    public void assignTask() {
        if (assignedPersonnel.size() < 2) {
            System.out.println("ReconMission requires at least 2 personnel.");
            return;
        }
        for (Personnel p : assignedPersonnel) {
            System.out.println("Assigned reconnaissance task to: " + p.getPersonnelName());
        }
    }

    @Override
    public void allocateResources(List<Resource> availableResources) {
        for (Resource r : availableResources) {
            if (r.getResourceName().equalsIgnoreCase("Drone") && r.getQuantity() > 0) {
                allocatedResources.add(r);
                r.setQuantity(r.getQuantity() - 1);
                System.out.println("Drone allocated to ReconMission.");
                return;
            }
        }
        System.out.println("No drones available for ReconMission.");
    }

    @Override
    public void trackMissionProgress() {
        System.out.println("Tracking intelligence gathering for ReconMission...");
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
