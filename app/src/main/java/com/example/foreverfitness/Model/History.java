package com.example.foreverfitness.Model;

//Model for history logs of weight
public class History {
    private String username;
    private String entryDate;
    private String weighEntered;

    public History(String username, String entryDate, String weighEntered) {
        this.entryDate = entryDate;
        this.weighEntered = weighEntered;
        this.username = username;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public String getWeighEntered() {
        return weighEntered;
    }
    public String getUsername(){
        return username;
    }
}
