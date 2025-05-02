package land;
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


