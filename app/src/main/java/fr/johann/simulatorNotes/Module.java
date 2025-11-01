package fr.johann.simulatorNotes;

import java.io.Serializable;

public class Module implements Serializable {
    private String name;
    private double coefficient;
    private double grade;

    // Constructeur
    public Module(String name, double grade, double coefficient) {
        this.name = name;
        this.grade = grade;
        this.coefficient = coefficient;
    }

    // Getters et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
