package mission;
import java.util.Date;
import java.util.List;

public class HumanitarianMission extends Mission{
    public HumanitarianMission(String missionId, String missionName, Date start, Date end) {
        super(missionId, missionName, start, end);
    }

    @Override
    public void assignTask() {
        boolean validRoles = assignedPersonnel.stream().anyMatch(p ->
                p.getPersonnelRole().equalsIgnoreCase("Logistics") ||
                        p.getPersonnelRole().equalsIgnoreCase("Medic")
        );
        if (!validRoles) {
            System.out.println("HumanitarianMission must have personnel with Logistics or Medic roles.");
            return;
        }
        for (Personnel p : assignedPersonnel) {
            System.out.println("Assigned humanitarian task to: " + p.getPersonnelName());
        }
    }

    @Override
    public void allocateResources(List<Resource> availableResources) {
        for (Resource r : availableResources) {
            if ((r.getResourceType().equalsIgnoreCase("Medical Supplies") ||
                    r.getResourceType().equalsIgnoreCase("Food Supplies")) && r.getQuantity() > 0) {
                allocatedResources.add(r);
                r.setQuantity(r.getQuantity() - 1);
                System.out.println(r.getResourceName() + " allocated to HumanitarianMission.");
            }
        }
    }

    @Override
    public void trackMissionProgress() {
        System.out.println("Monitoring humanitarian aid distribution...");
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
