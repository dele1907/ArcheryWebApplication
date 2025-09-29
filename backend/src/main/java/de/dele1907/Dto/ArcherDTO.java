package de.dele1907.Dto;

import java.sql.Date;

public record ArcherDTO(String id, String name, String firstName, String clubId, String clubName, String bowType, Date birthDate, String ageCategory, int passportNumber) {
}
