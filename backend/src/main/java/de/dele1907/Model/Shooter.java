package de.dele1907.Model;

public abstract class Shooter {
    private final String name;
    private final String firstName;
    private final String club;
    private final int clubNumber;
    private final String id;

    public Shooter(String id, String name, String firstName, String club, int clubNumber) {
        this.name = name;
        this.firstName = firstName;
        this.club = club;
        this.clubNumber = clubNumber;
        this.id = id;
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

    public String getId() {
        return id;
    }
}