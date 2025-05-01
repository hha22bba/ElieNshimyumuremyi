# ElieNshimyumuremyi
## OOP Projects
### First: Mission Management System
### Main Class
```java 
import java.util.*;
public class Main {
    public static void main(String[] args) {
        // Create resources
        Resource drone = new Resource("R001", "Drone", 2, "Equipment");
        Resource medicKit = new Resource("R002", "Medical Kit", 3, "Medical Supplies");
        Resource ambulance = new Resource("R003", "Ambulance", 1, "Vehicle");
        Resource food = new Resource("R004", "Food Pack", 5, "Food Supplies");

        List<Resource> allResources = new ArrayList<>();
        allResources.add(drone);
        allResources.add(medicKit);
        allResources.add(ambulance);
        allResources.add(food);

        // Create personnel
        Personnel soldier1 = new Personnel("P001", "John Smith", "Soldier");
        Personnel soldier2 = new Personnel("P002", "Jane Doe", "Soldier");
        Personnel medic = new Personnel("P003", "Alice Lee", "Medic");
        Personnel logistics = new Personnel("P004", "Mark Chan", "Logistics");

        // Create a mission
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.APRIL, 1);
        Date startDate = calendar.getTime();
        calendar.set(2025, Calendar.APRIL, 10);
        Date endDate = calendar.getTime();

        Mission reconMission = new ReconMission("M001", "Desert Surveillance", startDate, endDate);
        reconMission.addPersonnel(soldier1);
        reconMission.addPersonnel(soldier2);

        reconMission.assignTask();
        reconMission.allocateResources(allResources);
        reconMission.trackMissionProgress();
        reconMission.generateMissionReport();

        Mission rescueMission = new RescueMission("M002", "Flood Relief", startDate, endDate);
        rescueMission.addPersonnel(soldier1);
        rescueMission.addPersonnel(medic);

        rescueMission.assignTask();
        rescueMission.allocateResources(allResources);
        rescueMission.trackMissionProgress();
        rescueMission.generateMissionReport();
    }
}

```
### CombatMission Class
```java 
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

```
### HumanitarianMission Class
```java 
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

```
### Mission Class
```java 
import java.util.*;
public abstract class Mission {
    protected String missionId;
    protected String missionName;
    protected Date missionStartDate;
    protected Date missionEndDate;
    protected String status;
    protected List<Personnel> assignedPersonnel = new ArrayList<>();
    protected List<Resource> allocatedResources = new ArrayList<>();

    public Mission(String missionId, String missionName, Date missionStartDate, Date missionEndDate) {
        if (!missionStartDate.before(missionEndDate)) {
            throw new IllegalArgumentException("Start date must be before end date.");
        }
        this.missionId = missionId;
        this.missionName = missionName;
        this.missionStartDate = missionStartDate;
        this.missionEndDate = missionEndDate;
        this.status = "PLANNED";
    }

    public abstract void assignTask();
    public abstract void allocateResources(List<Resource> availableResources);
    public abstract void trackMissionProgress();
    public abstract void generateMissionReport();

    public void addPersonnel(Personnel p) {
        if (!assignedPersonnel.contains(p)) {
            assignedPersonnel.add(p);
            p.setAssignedMission(this);
        }
    }

    public List<Resource> getAllocatedResources() {
        return allocatedResources;
    }
}

```
### Personnel Class
```java 
public class Personnel {
    private String personnelId;
    private String personnelName;
    private String personnelRole;
    private Mission assignedMission;

    public Personnel(String personnelId, String personnelName, String personnelRole) {
        this.personnelId = personnelId;
        this.personnelName = personnelName;
        this.personnelRole = personnelRole;
    }

    // Getters and Setters
    public String getPersonnelId() { return personnelId; }
    public String getPersonnelName() { return personnelName; }
    public String getPersonnelRole() { return personnelRole; }
    public Mission getAssignedMission() { return assignedMission; }
    public void setAssignedMission(Mission assignedMission) { this.assignedMission = assignedMission;
    }

}
```
### ReconMission Class
```java 
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
```
### RescueMission Class
```java
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
```
### Resource Class
```java 
public class Resource {
    private String resourceId;
    private String resourceName;
    private int quantity;
    private String resourceType;

    public Resource(String resourceId, String resourceName, int quantity, String resourceType) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.quantity = quantity;
        this.resourceType = resourceType;
    }

    // Getters and Setters
    public String getResourceId() { return resourceId; }
    public String getResourceName() { return resourceName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getResourceType() { return resourceType; }
}
```
