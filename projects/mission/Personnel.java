package mission;
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

