package de.dele1907.Service;

import de.dele1907.Database.Repository.ArcherRepository;
import de.dele1907.Exception.NoArchesFoundException;
import de.dele1907.Model.Archer;

import java.util.List;

public class ArcherService implements IBaseService<Archer> {
    private final ArcherRepository archerRepository;

    public ArcherService(ArcherRepository archerRepository) {
        this.archerRepository = archerRepository;
    }

    public List<Archer> getAllEntities() throws NoArchesFoundException {
        return archerRepository.findAll();
    }

    public Archer getEntityById(String id) {
        return archerRepository.findById(id).orElse(null);
    }

    public boolean deleteEntityById(String id) {
        return archerRepository.deleteById(id);
    }

    public boolean saveNewEntity(Archer archer) {
        return archerRepository.save(archer);
    }

    public boolean updateEntity(Archer entity) {
        return archerRepository.update(entity);
    }
}
