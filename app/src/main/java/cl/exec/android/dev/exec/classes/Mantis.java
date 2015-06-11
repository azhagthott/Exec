package cl.exec.android.dev.exec.classes;

import java.util.Date;

/**
 * Created by fran on 03-06-15.
 */

public class Mantis extends Projects{

    private Integer incidenceId;
    private String incidenceCategory;
    private String incidenceSeverity;
    private String incidenceState;
    private String incidenceSummary;
    private Date incidenceCreationDate;
    private Date incidenceUpgradeDate;
    private double incidenceEstimatedHH;
    private double incidenceRealHH;
    private double incidenceReportHH;

    public Mantis(Integer projectId, String projectName, String projectDescription, String projectReq, Integer incidenceId, String incidenceCategory, String incidenceSeverity, String incidenceState, String incidenceSummary, Date incidenceCreationDate, Date incidenceUpgradeDate, double incidenceEstimatedHH, double incidenceRealHH, double incidenceReportHH) {
        super(projectId, projectName, projectDescription, projectReq);
        this.incidenceId = incidenceId;
        this.incidenceCategory = incidenceCategory;
        this.incidenceSeverity = incidenceSeverity;
        this.incidenceState = incidenceState;
        this.incidenceSummary = incidenceSummary;
        this.incidenceCreationDate = incidenceCreationDate;
        this.incidenceUpgradeDate = incidenceUpgradeDate;
        this.incidenceEstimatedHH = incidenceEstimatedHH;
        this.incidenceRealHH = incidenceRealHH;
        this.incidenceReportHH = incidenceReportHH;
    }

    public Integer getIncidenceId() {
        return incidenceId;
    }

    public void setIncidenceId(Integer incidenceId) {
        this.incidenceId = incidenceId;
    }

    public String getIncidenceCategory() {
        return incidenceCategory;
    }

    public void setIncidenceCategory(String incidenceCategory) {
        this.incidenceCategory = incidenceCategory;
    }

    public String getIncidenceSeverity() {
        return incidenceSeverity;
    }

    public void setIncidenceSeverity(String incidenceSeverity) {
        this.incidenceSeverity = incidenceSeverity;
    }

    public String getIncidenceState() {
        return incidenceState;
    }

    public void setIncidenceState(String incidenceState) {
        this.incidenceState = incidenceState;
    }

    public String getIncidenceSummary() {
        return incidenceSummary;
    }

    public void setIncidenceSummary(String incidenceSummary) {
        this.incidenceSummary = incidenceSummary;
    }

    public Date getIncidenceCreationDate() {
        return incidenceCreationDate;
    }

    public void setIncidenceCreationDate(Date incidenceCreationDate) {
        this.incidenceCreationDate = incidenceCreationDate;
    }

    public Date getIncidenceUpgradeDate() {
        return incidenceUpgradeDate;
    }

    public void setIncidenceUpgradeDate(Date incidenceUpgradeDate) {
        this.incidenceUpgradeDate = incidenceUpgradeDate;
    }

    public double getIncidenceEstimatedHH() {
        return incidenceEstimatedHH;
    }

    public void setIncidenceEstimatedHH(double incidenceEstimatedHH) {
        this.incidenceEstimatedHH = incidenceEstimatedHH;
    }

    public double getIncidenceRealHH() {
        return incidenceRealHH;
    }

    public void setIncidenceRealHH(double incidenceRealHH) {
        this.incidenceRealHH = incidenceRealHH;
    }

    public double getIncidenceReportHH() {
        return incidenceReportHH;
    }

    public void setIncidenceReportHH(double incidenceReportHH) {
        this.incidenceReportHH = incidenceReportHH;
    }
}
