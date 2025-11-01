package fr.johann.simulatorNotes;

import java.util.List;

public class Ue {
    private String title;
    private List<Module> modules;
    private Double average;
    private double coefficient;

    // Constructeur
    public Ue(String title, List<Module> modules, double coefficient) {
        this.title = title;
        this.modules = modules;
        this.coefficient = coefficient;
        this.average = calculateAverage();
    }

    // Méthode pour calculer la moyenne pondérée des modules dans l'UE


    private Double calculateAverage() {
        if (modules == null || modules.isEmpty()) return null;

        double total = 0;
        double totalCoefficient = 0;
        for (Module module : modules) {
            if (module.getGrade() != null) {
                total += module.getGrade() * module.getCoefficient();
                totalCoefficient += module.getCoefficient();
            }
        }

        if (totalCoefficient == 0) return null;

        return total / totalCoefficient; // Valeur brute
    }

    public void updateAverage() {
        double total = 0;
        double coefSum = 0;

        for (Module m : modules) {
            total += m.getGrade() * m.getCoefficient();
            coefSum += m.getCoefficient();
        }

        average = coefSum != 0 ? total / coefSum : 0;
    }





    // Getters et Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
        this.average = calculateAverage();
    }

    public Double getAverage() {
        return average;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
