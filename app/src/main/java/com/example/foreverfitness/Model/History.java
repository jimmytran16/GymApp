package com.example.foreverfitness.Model;

//Model for history logs of weight
public class History {
    private String entryDate;
    private double weighEntered;

    public History(String entryDate, double weighEntered) {
        this.entryDate = entryDate;
        this.weighEntered = weighEntered;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public double getWeighEntered() {
        return weighEntered;
    }
}
