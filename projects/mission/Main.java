package mission;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create some resources
        List<Resource> allResources = new ArrayList<>();
        allResources.add(new Resource("R001", "Drone", 2, "Equipment"));
        allResources.add(new Resource("R002", "Medical Kit", 3, "Medical Supplies"));
        allResources.add(new Resource("R003", "Ambulance", 1, "Vehicle"));
        allResources.add(new Resource("R004", "Food Pack", 5, "Food Supplies"));

        // Create personnel based on user input
        List<Personnel> personnelList = new ArrayList<>();
        System.out.print("Enter number of personnel to create: ");
        int personnelCount = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 1; i <= personnelCount; i++) {
            System.out.println("\nEnter details for Personnel " + i);
            System.out.print("ID: ");
            String id = scanner.nextLine();
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Role (e.g., Soldier, Medic, Logistics): ");
            String role = scanner.nextLine();
            personnelList.add(new Personnel(id, name, role));
        }

        // Mission details
        System.out.print("\nEnter mission ID: ");
        String missionId = scanner.nextLine();
        System.out.print("Enter mission name: ");
        String missionName = scanner.nextLine();

        System.out.println("Choose mission type:");
        System.out.println("1. Recon");
        System.out.println("2. Rescue");
        System.out.println("3. Combat");
        System.out.println("4. Humanitarian");
        System.out.print("Enter choice (1-4): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MAY, 1);
        Date startDate = calendar.getTime();
        calendar.set(2025, Calendar.MAY, 10);
        Date endDate = calendar.getTime();

        Mission mission = null;
        switch (choice) {
            case 1: mission = new ReconMission(missionId, missionName, startDate, endDate); break;
            case 2: mission = new RescueMission(missionId, missionName, startDate, endDate); break;
            case 3: mission = new CombatMission(missionId, missionName, startDate, endDate); break;
            case 4: mission = new HumanitarianMission(missionId, missionName, startDate, endDate); break;
            default:
                System.out.println("Invalid choice. Exiting.");
                System.exit(0);
        }

        // Display personnel list for user to assign
        System.out.println("\nAvailable Personnel:");
        for (int i = 0; i < personnelList.size(); i++) {
            Personnel p = personnelList.get(i);
            System.out.println((i + 1) + ". " + p.getPersonnelName() + " (" + p.getPersonnelRole() + ")");
        }

        System.out.print("Enter number of personnel to assign to the mission: ");
        int assignCount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < assignCount; i++) {
            System.out.print("Enter personnel number to assign (from above list): ");
            int index = scanner.nextInt();
            scanner.nextLine();
            if (index >= 1 && index <= personnelList.size()) {
                mission.addPersonnel(personnelList.get(index - 1));
            } else {
                System.out.println("Invalid index.");
            }
        }

        // Run mission lifecycle
        System.out.println("\n--- Mission Execution ---");
        mission.assignTask();
        mission.allocateResources(allResources);
        mission.trackMissionProgress();
        mission.generateMissionReport();

        scanner.close();
    }
}
