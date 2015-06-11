package cl.exec.android.dev.exec.classes;

/**
 * Created by fran on 03-06-15.
 */
public class Projects {

    private Integer projectId;
    private String projectName;
    private String projectDescription;
    private String projectReq;

    /**
     * @param projectId Project Id
     * @param projectName Project Name
     * @param projectDescription Project Description
     * @param projectReq Project Requieriment
     *
     * */
    public Projects(Integer projectId, String projectName, String projectDescription, String projectReq) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectReq = projectReq;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName(int projectId) {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectdescription(int projectId) {
        return projectDescription;
    }

    public void setProjectdescription(String projectdescription) {
        this.projectDescription = projectdescription;
    }

    public String getProjectReq(int projectId) {
        return projectReq;
    }

    public void setProjectReq(String projectReq) {
        this.projectReq = projectReq;
    }


}
