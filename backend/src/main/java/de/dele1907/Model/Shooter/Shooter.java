package de.dele1907.Model.Shooter;

public abstract class Shooter {
    private final String id;
    private final String name;
    private final String firstName;
    private final String clubId;
    private final int passportNumber;


    public Shooter(String id, String name, String firstName, String clubId, int passportNumber) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.clubId = clubId;
        this.passportNumber = passportNumber;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getClubId() {
        return clubId;
    }

    public int getPassportNumber() {
        return passportNumber;
    }
}