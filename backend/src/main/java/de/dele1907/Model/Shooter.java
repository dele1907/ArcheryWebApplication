package de.dele1907.Model;

public abstract class Shooter {
    private final String id;
    private final String name;
    private final String firstName;
    private final String club;
    private final int clubNumber;
    private final int passportNumber;


    public Shooter(String id, String name, String firstName, String club, int clubNumber, int passportNumber) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.club = club;
        this.clubNumber = clubNumber;
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

    public String getClub() {
        return club;
    }

    public int getClubNumber() {
        return clubNumber;
    }

    public int getPassportNumber() {
        return passportNumber;
    }
}