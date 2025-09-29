package de.dele1907.Service;

import de.dele1907.Database.Repository.ArcherRepository;
import de.dele1907.Dto.ArcherDTO;
import de.dele1907.Exception.NoArchesFoundException;
import de.dele1907.Mapper.ArcherMapper;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class ArcherService implements IBaseService<ArcherDTO> {
    private final ArcherRepository archerRepository;
    private final ClubService clubService;

    public ArcherService(ArcherRepository archerRepository, ClubService clubService) {
        this.archerRepository = archerRepository;
        this.clubService = clubService;
    }

    public List<ArcherDTO> getAllEntities() throws NoArchesFoundException {
        var archers = archerRepository.findAll();

        return archers.stream()
                .map(archer -> {
                    var club = clubService.getEntityById(archer.getClubId());
                    return ArcherMapper.toDTO(archer, club, getAgeCategoryByBirthdate(archer.getBirthDate()));
                }).toList();
    }

    private String getAgeCategoryByBirthdate(Date birthdate) {
        LocalDate birthDateLocalDate = birthdate.toLocalDate();

        int age = Period.between(birthDateLocalDate, LocalDate.now()).getYears();

        NavigableMap<Integer, String> ageCategories = new TreeMap<>();
        ageCategories.put(12, "Schüler");
        ageCategories.put(18, "Jugend");
        ageCategories.put(20, "Junioren");
        ageCategories.put(54, "Erwachsene");
        ageCategories.put(Integer.MAX_VALUE, "Senioren");

        return ageCategories.ceilingEntry(age).getValue();
    }

    public ArcherDTO getEntityById(String id) throws NoArchesFoundException {
        var archer = archerRepository.findById(id).orElse(null);
        var club = clubService.getEntityById(archer.getClubId());

        return ArcherMapper.toDTO(archer, club, getAgeCategoryByBirthdate(archer.getBirthDate()));
    }

    public boolean deleteEntityById(String id) throws NoArchesFoundException {
        return archerRepository.deleteById(id);
    }

    public boolean saveNewEntity(ArcherDTO archer) {
        return archerRepository.save(ArcherMapper.toEntity(archer));
    }

    public boolean updateEntity(ArcherDTO entity) throws NoArchesFoundException {
        return archerRepository.update(ArcherMapper.toEntity(entity));
    }
}
