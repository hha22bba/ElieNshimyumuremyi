package land;
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


