package fr.johann.simulatorNotes;

import java.util.List;

public class Semester {
    private String semesterName;
    private String enrollmentDate;
    private Double overallAverage;
    private List<Ue> ues;

    // Constructeur
    public Semester(String semesterName, String enrollmentDate, List<Ue> ues) {
        this.semesterName = semesterName;
        this.enrollmentDate = enrollmentDate;
        this.ues = ues;
        this.overallAverage = calculateOverallAverage();
    }

    // Méthode pour calculer la moyenne globale du semestre
    private Double calculateOverallAverage() {
        if (ues == null || ues.isEmpty()) return null;

        double total = 0;
        int ueCount = 0;
        for (Ue ue : ues) {
            if (ue.getAverage() != null) {
                total += ue.getAverage();
                ueCount++;
            }
        }
        return ueCount != 0 ? total / ueCount : null;
    }

    // Getters et Setters
    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Double getOverallAverage() {
        return overallAverage;
    }

    public List<Ue> getUes() {
        return ues;
    }

    public void setUes(List<Ue> ues) {
        this.ues = ues;
        this.overallAverage = calculateOverallAverage();
    }
}
