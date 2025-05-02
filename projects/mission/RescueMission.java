package mission;
import java.util.*;

public class RescueMission extends Mission{
    public RescueMission(String missionId, String missionName, Date start, Date end) {
        super(missionId, missionName, start, end);
    }

    @Override
    public void assignTask() {
        boolean hasMedic = assignedPersonnel.stream().anyMatch(p -> p.getPersonnelRole().equalsIgnoreCase("Medic"));
        if (!hasMedic) {
            System.out.println("RescueMission must have at least one Medic assigned.");
            return;
        }
        for (Personnel p : assignedPersonnel) {
            System.out.println("Assigned rescue task to: " + p.getPersonnelName());
        }
    }

    @Override
    public void allocateResources(List<Resource> availableResources) {
        for (Resource r : availableResources) {
            if ((r.getResourceName().equalsIgnoreCase("Ambulance") || r.getResourceType().equalsIgnoreCase("Medical Supplies")) && r.getQuantity() > 0) {
                allocatedResources.add(r);
                r.setQuantity(r.getQuantity() - 1);
                System.out.println(r.getResourceName() + " allocated to RescueMission.");
            }
        }
    }

    @Override
    public void trackMissionProgress() {
        System.out.println("Tracking rescue operations...");
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
