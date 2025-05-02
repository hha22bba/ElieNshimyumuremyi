package land;
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
