package land;
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

