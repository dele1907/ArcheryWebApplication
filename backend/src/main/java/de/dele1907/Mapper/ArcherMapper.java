package de.dele1907.Mapper;

import de.dele1907.Dto.ArcherDTO;
import de.dele1907.Model.Club.ShootersClub;
import de.dele1907.Model.Shooter.Archer;

public class ArcherMapper {
    public static ArcherDTO toDTO(Archer archer, ShootersClub club, String ageCategory) {
        return new ArcherDTO(
                archer.getId(),
                archer.getName(),
                archer.getFirstName(),
                archer.getClubId(),
                club != null ? club.clubName() : "",
                archer.getBowType(),
                archer.getBirthDate(),
                ageCategory != null ? ageCategory : "",
                archer.getPassportNumber()
        );
    }

    public static Archer toEntity(ArcherDTO dto) {
        return new Archer(
                dto.id(),
                dto.name(),
                dto.firstName(),
                dto.clubId(),
                dto.bowType(),
                dto.birthDate(),
                dto.passportNumber()
        );
    }
}
