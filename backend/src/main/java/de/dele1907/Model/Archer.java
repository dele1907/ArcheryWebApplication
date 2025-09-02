package de.dele1907.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Archer extends Shooter {
    private final String bowType;
    private final String ageCategory;

    public Archer(String id, String name, String firstName, String club, int clubNumber, String bowType, String ageCategory) {
        super(id, name, firstName, club, clubNumber);
        this.bowType = bowType;
        this.ageCategory = ageCategory;
    }

    public String getBowType() {
        return bowType;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    @Override
    public String toString() {
        return "Archer: name= " + getName() + ", bowType= " + bowType + ", category= " + ageCategory;
    }
}
