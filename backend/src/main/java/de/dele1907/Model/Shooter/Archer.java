package de.dele1907.Model.Shooter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Archer extends Shooter {
    private final String bowType;
    private final Date birthdate;

    @JsonCreator
    public Archer(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("clubId") String clubId,
            @JsonProperty("bowType") String bowType,
            @JsonProperty("birthDate") Date birthDate,
            @JsonProperty("passportNumber") int passportNumber) {
        super(id, name, firstName, clubId, passportNumber);
        this.bowType = bowType;
        this.birthdate = birthDate;
    }

    public String getBowType() {
        return bowType;
    }

    public Date getBirthDate() {
        return birthdate;
    }

    @Override
    public String toString() {
        return "Archer: name= " + getName() + ", bowType= " + bowType + ", category= " + birthdate.toString();
    }
}
