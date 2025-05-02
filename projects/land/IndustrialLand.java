package land;
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

