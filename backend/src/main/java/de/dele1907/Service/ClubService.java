package de.dele1907.Service;

import de.dele1907.Database.Repository.ShootersClubRepository;
import de.dele1907.Model.Club.ShootersClub;

import java.util.List;

public class ClubService implements IBaseService<ShootersClub>{
    private final ShootersClubRepository shootersClubRepository;

    public ClubService(ShootersClubRepository shootersClubRepository) {
        this.shootersClubRepository = shootersClubRepository;
    }

    @Override
    public List<ShootersClub> getAllEntities() throws Exception {
        return shootersClubRepository.findAll();
    }

    @Override
    public ShootersClub getEntityById(String id) {
        return shootersClubRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteEntityById(String id) {
        return shootersClubRepository.deleteById(id);
    }

    @Override
    public boolean saveNewEntity(ShootersClub entity) {
        return shootersClubRepository.save(entity);
    }

    @Override
    public boolean updateEntity(ShootersClub entity) {
        return shootersClubRepository.update(entity);
    }
}
