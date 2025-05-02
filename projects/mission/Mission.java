package mission;
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
