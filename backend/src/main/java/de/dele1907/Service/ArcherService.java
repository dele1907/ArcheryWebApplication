package de.dele1907.Service;

import de.dele1907.Database.Repository.ArcherRepository;
import de.dele1907.Database.Repository.ShootersClubRepository;
import de.dele1907.Dto.ArcherDTO;
import de.dele1907.Exception.NoArchesFoundException;
import de.dele1907.Mapper.ArcherMapper;

import java.util.List;

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
                    return ArcherMapper.toDTO(archer, club);
                }).toList();
    }

    public ArcherDTO getEntityById(String id) throws NoArchesFoundException {
        var archer = archerRepository.findById(id).orElse(null);

        if (archer == null) {
            throw new NoArchesFoundException("No archer found with id: " + id);
        }

        return ArcherMapper.toDTO(archer, new ClubService(new ShootersClubRepository()).getEntityById(archer.getClubId()));
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
