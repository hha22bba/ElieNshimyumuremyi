package land;
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
