# ElieNshimyumuremyi
## OOP Projects
## First Project: Mission Management System
--------------------------------------------
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
### Second Project: Land Management System
### Main Class
```java 
import java.util.Date;
import java.util.Scanner;

public class Main {

        public static void main(String[] args) {
            LandRegistry registry = new LandRegistry();
            Scanner scanner = new Scanner(System.in);

            System.out.println("=== Land Management System ===");
            while (true) {
                System.out.println("\nSelect an option:");
                System.out.println("1. Register new land");
                System.out.println("2. Generate all reports");
                System.out.println("3. Search by owner");
                System.out.println("4. Search by type");
                System.out.println("5. Exit");
                System.out.print("Your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter land type (agricultural, residential, commercial, industrial): ");
                        String type = scanner.nextLine().trim().toLowerCase();

                        System.out.print("Enter Land ID: ");
                        String landId = scanner.nextLine();

                        System.out.print("Enter Owner Name: ");
                        String ownerName = scanner.nextLine();

                        System.out.print("Enter Location: ");
                        String location = scanner.nextLine();

                        System.out.print("Enter Size in Acres: ");
                        double size = scanner.nextDouble();
                        scanner.nextLine(); // consume newline

                        System.out.print("Enter Land Use Status: ");
                        String useStatus = scanner.nextLine();

                        Land land = null;
                        switch (type) {
                            case "agricultural" -> land = new AgriculturalLand(landId, ownerName, location, size, new Date(), useStatus);
                            case "residential" -> {
                                System.out.print("Enter number of residential units: ");
                                int units = scanner.nextInt();
                                scanner.nextLine(); // consume newline
                                land = new ResidentialLand(landId, ownerName, location, size, new Date(), useStatus, units);
                            }
                            case "commercial" -> land = new CommercialLand(landId, ownerName, location, size, new Date(), useStatus);
                            case "industrial" -> {
                                System.out.print("Environmental clearance obtained (true/false): ");
                                boolean clearance = scanner.nextBoolean();
                                scanner.nextLine(); // consume newline
                                land = new IndustrialLand(landId, ownerName, location, size, new Date(), useStatus, clearance);
                            }
                            default -> System.out.println("Invalid land type.");
                        }

                        if (land != null) {
                            registry.registerLand(land);
                        }
                    }
                    case 2 -> registry.generateAllReports();

                    case 3 -> {
                        System.out.print("Enter owner name to search: ");
                        String ownerSearch = scanner.nextLine();
                        registry.searchByOwner(ownerSearch);
                    }

                    case 4 -> {
                        System.out.print("Enter type to search (agricultural, residential, commercial, industrial): ");
                        String typeSearch = scanner.nextLine().trim().toLowerCase();
                        switch (typeSearch) {
                            case "agricultural" -> registry.searchByType(AgriculturalLand.class);
                            case "residential" -> registry.searchByType(ResidentialLand.class);
                            case "commercial" -> registry.searchByType(CommercialLand.class);
                            case "industrial" -> registry.searchByType(IndustrialLand.class);
                            default -> System.out.println("Unknown type.");
                        }
                    }

                    case 5 -> {
                        System.out.println("Exiting system. Goodbye!");
                        scanner.close();
                        return;
                    }

                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }
```
### AgriculturalLand Class
```java 
import java.util.Date;

public class AgriculturalLand extends Land{
    public AgriculturalLand(String landId, String ownerName, String location, double sizeInAcres, Date registrationDate, String landUseStatus) {
        super(landId, ownerName, location, sizeInAcres, registrationDate, landUseStatus);
    }

    @Override
    public boolean validateOwnership() {
        return ownerName != null && !ownerName.trim().isEmpty();
    }

    @Override
    public boolean checkZoningCompliance() {
        // Simulating a check (you could add a zoning attribute)
        return location.toLowerCase().contains("farm") && sizeInAcres >= 1;
    }

    @Override
    public double calculateTax() {
        return sizeInAcres * 5000 * 0.01;
    }

    @Override
    public void generateLandReport() {
        System.out.println("=== Agricultural Land Report ===");
        System.out.printf("Land ID: %s%nOwner: %s%nLocation: %s%nSize: %.2f acres%nUse: %s%n", landId, ownerName, location, sizeInAcres, landUseStatus);
        System.out.printf("Ownership Valid: %s%nZoning Compliant: %s%nTax: $%.2f%n", validateOwnership(), checkZoningCompliance(), calculateTax());
    }
}
```
### CommercialLand Class
```java    
import java.util.Date;

public class CommercialLand extends Land{
    public CommercialLand(String landId, String ownerName, String location, double sizeInAcres, Date registrationDate, String landUseStatus) {
        super(landId, ownerName, location, sizeInAcres, registrationDate, landUseStatus);
    }

    @Override
    public boolean validateOwnership() {
        return ownerName != null && !ownerName.trim().isEmpty();
    }

    @Override
    public boolean checkZoningCompliance() {
        return location.toLowerCase().contains("commercial");
    }

    @Override
    public double calculateTax() {
        return sizeInAcres * 10000 * 0.025;
    }

    @Override
    public void generateLandReport() {
        System.out.println("=== Commercial Land Report ===");
        System.out.printf("Land ID: %s%nOwner: %s%nLocation: %s%nSize: %.2f acres%nUse: %s%n", landId, ownerName, location, sizeInAcres, landUseStatus);
        System.out.printf("Ownership Valid: %s%nZoning Compliant: %s%nTax: $%.2f%n", validateOwnership(), checkZoningCompliance(), calculateTax());
    }
}
```
### IndustrialLand Class
```java 
import java.util.Date;

public class IndustrialLand extends Land {
    private boolean hasEnvironmentalClearance;

    public IndustrialLand(String landId, String ownerName, String location, double sizeInAcres, Date registrationDate, String landUseStatus, boolean hasEnvironmentalClearance) {
        super(landId, ownerName, location, sizeInAcres, registrationDate, landUseStatus);
        this.hasEnvironmentalClearance = hasEnvironmentalClearance;
    }

    @Override
    public boolean validateOwnership() {
        return ownerName != null && !ownerName.trim().isEmpty();
    }

    @Override
    public boolean checkZoningCompliance() {
        return location.toLowerCase().contains("industrial") && hasEnvironmentalClearance;
    }

    @Override
    public double calculateTax() {
        return sizeInAcres * 12000 * 0.03;
    }

    @Override
    public void generateLandReport() {
        System.out.println("=== Industrial Land Report ===");
        System.out.printf("Land ID: %s%nOwner: %s%nLocation: %s%nSize: %.2f acres%nUse: %s%n", landId, ownerName, location, sizeInAcres, landUseStatus);
        System.out.printf("Ownership Valid: %s%nZoning Compliant: %s%nTax: $%.2f%n", validateOwnership(), checkZoningCompliance(), calculateTax());
    }
}
```
### Land Class
```java 
import java.util.Date;
public abstract class Land {
        protected String landId;
        protected String ownerName;
        protected String location;
        protected double sizeInAcres;
        protected Date registrationDate;
        protected String landUseStatus;

        public Land(String landId, String ownerName, String location, double sizeInAcres, Date registrationDate, String landUseStatus) {
            this.landId = landId;
            this.ownerName = ownerName;
            this.location = location;
            this.sizeInAcres = sizeInAcres;
            this.registrationDate = registrationDate;
            this.landUseStatus = landUseStatus;
        }

        public abstract boolean validateOwnership();

        public abstract boolean checkZoningCompliance();

        public abstract double calculateTax();

        public abstract void generateLandReport();
    }
```
### LandRegistry Class
```java 
import java.util.*;
public class LandRegistry {
        private List<Land> lands = new ArrayList<>();

        public void registerLand(Land land) {
            if (land.validateOwnership()) {
                lands.add(land);
                System.out.println("Land registered successfully: " + land.landId);
            } else {
                System.out.println("Ownership validation failed. Cannot register land: " + land.landId);
            }
        }

        public void generateAllReports() {
            for (Land land : lands) {
                land.generateLandReport();
                System.out.println("----------------------------------");
            }
        }

        public void searchByOwner(String owner) {
            for (Land land : lands) {
                if (land.ownerName.equalsIgnoreCase(owner)) {
                    land.generateLandReport();
                    System.out.println("----------------------------------");
                }
            }
        }

        public void searchByType(Class<?> type) {
            for (Land land : lands) {
                if (type.isInstance(land)) {
                    land.generateLandReport();
                    System.out.println("----------------------------------");
                }
            }
        }
    }
```
### ResidentialLand Class
```java 
import java.util.Date;

public class ResidentialLand extends Land{
    private int residentialUnits;

    public ResidentialLand(String landId, String ownerName, String location, double sizeInAcres, Date registrationDate, String landUseStatus, int residentialUnits) {
        super(landId, ownerName, location, sizeInAcres, registrationDate, landUseStatus);
        this.residentialUnits = residentialUnits;
    }

    @Override
    public boolean validateOwnership() {
        return ownerName != null && !ownerName.trim().isEmpty();
    }

    @Override
    public boolean checkZoningCompliance() {
        return residentialUnits <= (sizeInAcres * 2);
    }

    @Override
    public double calculateTax() {
        return sizeInAcres * 8000 * 0.015;
    }

    @Override
    public void generateLandReport() {
        System.out.println("=== Residential Land Report ===");
        System.out.printf("Land ID: %s%nOwner: %s%nLocation: %s%nSize: %.2f acres%nUse: %s%n", landId, ownerName, location, sizeInAcres, landUseStatus);
        System.out.printf("Ownership Valid: %s%nZoning Compliant: %s%nTax: $%.2f%n", validateOwnership(), checkZoningCompliance(), calculateTax());
    }
}
```
### Third Project: Nursery Management System
### MainMenu
```java 
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class MainMenu {

        static Scanner scanner = new Scanner(System.in);
        static Set<String> studentIds = new HashSet<>();

        static BabyClass babyClass = new BabyClass("C001");
        static MiddleClass middleClass = new MiddleClass("C002");
        static TopClass topClass = new TopClass("C003");

        static List<Teacher> teachers = new ArrayList<>();
        static Map<String, NurseryClass> classMap = Map.of(
                "Baby", babyClass,
                "Middle", middleClass,
                "Top", topClass
        );

        public static void main(String[] args) {
            boolean running = true;
            while (running) {
                System.out.println("\n====== Nursery School Management ======");
                System.out.println("1. Assign Teacher");
                System.out.println("2. Enroll Student");
                System.out.println("3. Conduct Activity");
                System.out.println("4. Track Progress");
                System.out.println("5. Generate & Save Class Report");
                System.out.println("6. Exit");
                System.out.print("Choose option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> assignTeacher();
                    case 2 -> enrollStudent();
                    case 3 -> conductActivity();
                    case 4 -> trackClassProgress();
                    case 5 -> generateAndSaveReport();
                    case 6 -> running = false;
                    default -> System.out.println("Invalid option.");
                }
            }
        }

        static void assignTeacher() {
            System.out.print("Enter Teacher ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Role: ");
            String role = scanner.nextLine();

            Teacher t = new Teacher(id, name, role);
            teachers.add(t);

            System.out.print("Assign to (Baby/Middle/Top): ");
            String classChoice = scanner.nextLine();
            NurseryClass cls = classMap.get(classChoice);

            if (cls != null) {
                if (cls.assignTeacher(t)) {
                    System.out.println("Teacher assigned successfully.");
                }
            } else {
                System.out.println("Invalid class.");
            }
        }

        static void enrollStudent() {
            System.out.print("Student ID: ");
            String id = scanner.nextLine();
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Guardian Name: ");
            String guardian = scanner.nextLine();

            Student s = new Student(id, name, age, guardian);
            System.out.print("Enroll in (Baby/Middle/Top): ");
            String classChoice = scanner.nextLine();

            NurseryClass cls = classMap.get(classChoice);
            if (cls != null) {
                if (cls.enrollStudent(s, studentIds)) {
                    System.out.println("Student enrolled successfully.");
                }
            } else {
                System.out.println("Invalid class.");
            }
        }

        static void conductActivity() {
            System.out.print("Which class (Baby/Middle/Top): ");
            String classChoice = scanner.nextLine();
            System.out.print("Activity name: ");
            String activity = scanner.nextLine();

            NurseryClass cls = classMap.get(classChoice);
            if (cls != null) {
                cls.conductActivity(activity);
            } else {
                System.out.println("Invalid class.");
            }
        }

        static void trackClassProgress() {
            System.out.print("Which class to track (Baby/Middle/Top): ");
            String classChoice = scanner.nextLine();

            NurseryClass cls = classMap.get(classChoice);
            if (cls != null) {
                cls.trackProgress();
                System.out.println("Progress tracked.");
            } else {
                System.out.println("Invalid class.");
            }
        }

        static void generateAndSaveReport() {
            System.out.print("Generate report for (Baby/Middle/Top): ");
            String classChoice = scanner.nextLine();

            NurseryClass cls = classMap.get(classChoice);
            if (cls != null) {
                cls.generateClassReport();
                saveReportToFile(cls);
            } else {
                System.out.println("Invalid class.");
            }
        }

        static void saveReportToFile(NurseryClass cls) {
            String fileName = cls.className.replace(" ", "_") + "_Report.txt";
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write("Class Report: " + cls.className + "\n");
                writer.write("Teacher: " + (cls.assignedTeacher != null ? cls.assignedTeacher.teacherName : "None") + "\n");
                writer.write("Students Enrolled: " + cls.students.size() + "\n");
                writer.write("Activities: " + cls.activities + "\n");
                writer.write("Progress: " + cls.progressNote + "\n");
                System.out.println("Report saved to " + fileName);
            } catch (IOException e) {
                System.out.println("Failed to save report: " + e.getMessage());
            }
        }
    }
```
### BabyClass Class
```java 
import java.util.*;
public class BabyClass extends NurseryClass{
        public BabyClass(String classId) {
            super(classId, "Baby Class", 15);
        }

        @Override
        public boolean enrollStudent(Student student, Set<String> existingStudentIds) {
            if (student.age < 2 || student.age > 3) {
                System.out.println("Age not suitable for Baby Class.");
                return false;
            }
            if (students.size() >= maxCapacity) {
                System.out.println("Baby Class is full.");
                return false;
            }
            if (existingStudentIds.contains(student.studentId)) {
                System.out.println("Duplicate student ID.");
                return false;
            }
            student.registeredClass = this;
            students.add(student);
            existingStudentIds.add(student.studentId);
            return true;
        }

        @Override
        public boolean assignTeacher(Teacher teacher) {
            if (!teacher.teacherRole.equals("Early Childhood Educator")) {
                System.out.println("Teacher not qualified for Baby Class.");
                return false;
            }
            this.assignedTeacher = teacher;
            teacher.assignedClass = this;
            return true;
        }

        @Override
        public void trackProgress() {
            progressNote = "Focused on motor skills and play-based learning.";
        }

        @Override
        public void conductActivity(String activityName) {
            activities.add(activityName);
            System.out.println("Conducted: " + activityName);
        }

        @Override
        public void generateClassReport() {
            System.out.println("----- Baby Class Report -----");
            System.out.println("Teacher: " + (assignedTeacher != null ? assignedTeacher.teacherName : "None"));
            System.out.println("Students Enrolled: " + students.size());
            System.out.println("Activities: " + activities);
            System.out.println("Progress: " + progressNote);
        }
    }
```
### MiddleClass Class
```java 
import java.util.*;
public class MiddleClass extends NurseryClass {
         public MiddleClass(String classId) {
            super(classId, "Middle Class", 20);
        }

        @Override
        public boolean enrollStudent(Student student, Set<String> existingStudentIds) {
            if (student.age < 3 || student.age > 4) {
                System.out.println("Age not suitable for Middle Class.");
                return false;
            }
            if (students.size() >= maxCapacity) {
                System.out.println("Middle Class is full.");
                return false;
            }
            if (existingStudentIds.contains(student.studentId)) {
                System.out.println("Duplicate student ID.");
                return false;
            }
            student.registeredClass = this;
            students.add(student);
            existingStudentIds.add(student.studentId);
            return true;
        }

        @Override
        public boolean assignTeacher(Teacher teacher) {
            this.assignedTeacher = teacher;
            teacher.assignedClass = this;
            return true;
        }

        @Override
        public void trackProgress() {
            progressNote = "Language development and storytelling.";
        }

        @Override
        public void conductActivity(String activityName) {
            activities.add(activityName);
            System.out.println("Conducted: " + activityName);
        }

        @Override
        public void generateClassReport() {
            System.out.println("----- Middle Class Report -----");
            System.out.println("Teacher: " + (assignedTeacher != null ? assignedTeacher.teacherName : "None"));
            System.out.println("Students Enrolled: " + students.size());
            System.out.println("Activities: " + activities);
            System.out.println("Progress: " + progressNote);
        }
}
```
### NurseryClass Class
```java 
import java.util.*;
abstract class NurseryClass {

        String classId;
        String className;
        int maxCapacity;
        Teacher assignedTeacher;
        List<Student> students = new ArrayList<>();
        List<String> activities = new ArrayList<>();
        String progressNote = "";

        public NurseryClass(String classId, String className, int maxCapacity) {
            this.classId = classId;
            this.className = className;
            this.maxCapacity = maxCapacity;
        }

        public abstract boolean enrollStudent(Student student, Set<String> existingStudentIds);
        public abstract void trackProgress();
        public abstract void conductActivity(String activityName);
        public abstract void generateClassReport();
        public abstract boolean assignTeacher(Teacher teacher);
    }
```
### Student Class
```java 
class Student {
        String studentId;
        String studentName;
        int age;
        String guardianName;
        NurseryClass registeredClass;

        public Student(String studentId, String studentName, int age, String guardianName) {
            this.studentId = studentId;
            this.studentName = studentName;
            this.age = age;
            this.guardianName = guardianName;
        }
    }
```
### Teacher Class
```java 

class Teacher {
        String teacherId;
        String teacherName;
        String teacherRole;
        NurseryClass assignedClass;

        public Teacher(String teacherId, String teacherName, String teacherRole) {
            this.teacherId = teacherId;
            this.teacherName = teacherName;
            this.teacherRole = teacherRole;
        }
    }
```
### TopClass Class
```java 
import java.util.*;
public class TopClass extends NurseryClass{

        public TopClass(String classId) {
            super(classId, "Top Class", 25);
        }

        @Override
        public boolean enrollStudent(Student student, Set<String> existingStudentIds) {
            if (student.age < 4 || student.age > 5) {
                System.out.println("Age not suitable for Top Class.");
                return false;
            }
            if (students.size() >= maxCapacity) {
                System.out.println("Top Class is full.");
                return false;
            }
            if (existingStudentIds.contains(student.studentId)) {
                System.out.println("Duplicate student ID.");
                return false;
            }
            student.registeredClass = this;
            students.add(student);
            existingStudentIds.add(student.studentId);
            return true;
        }

        @Override
        public boolean assignTeacher(Teacher teacher) {
            this.assignedTeacher = teacher;
            teacher.assignedClass = this;
            return true;
        }

        @Override
        public void trackProgress() {
            progressNote = "Basic reading, writing, and arithmetic with term assessments.";
        }

        @Override
        public void conductActivity(String activityName) {
            activities.add(activityName);
            System.out.println("Conducted: " + activityName);
        }

        @Override
        public void generateClassReport() {
            System.out.println("----- Top Class Report -----");
            System.out.println("Teacher: " + (assignedTeacher != null ? assignedTeacher.teacherName : "None"));
            System.out.println("Students Enrolled: " + students.size());
            System.out.println("Activities: " + activities);
            System.out.println("Progress: " + progressNote);
        }
    }
```
