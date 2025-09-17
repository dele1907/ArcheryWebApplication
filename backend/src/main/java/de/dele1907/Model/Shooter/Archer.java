package de.dele1907.Model.Shooter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Archer extends Shooter {
    private final String bowType;
    private final String ageCategory;

    @JsonCreator
    public Archer(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("club") String club,
            @JsonProperty("clubNumber") int clubNumber,
            @JsonProperty("bowType") String bowType,
            @JsonProperty("ageCategory") String ageCategory,
            @JsonProperty("passportNumber") int passportNumber) {
        super(id, name, firstName, club, clubNumber, passportNumber);
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
