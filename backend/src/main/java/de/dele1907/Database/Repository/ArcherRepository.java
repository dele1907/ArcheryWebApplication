package de.dele1907.Database.Repository;

import de.dele1907.Exception.NoArchesFoundException;
import de.dele1907.Model.Archer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArcherRepository implements IBaseRepository<Archer, String>{
    private static final Logger logger = LoggerFactory.getLogger(ArcherRepository.class);
    // Dummy data
    private final List<Archer> archers = new ArrayList<>();

    public ArcherRepository() {
        // Dummy data
        archers.add(new Archer("4711", "Napp", "Karl", "Beispielverein", 1234, "Recurve", "Adult", 4711));
        archers.add(new Archer("0815","Mustermann", "Max", "Musterverein", 5678, "Compound", "Junior", 815));
    }

    @Override
    public List<Archer> findAll() {
        try {
            if (archers.isEmpty()) {
                throw new NoArchesFoundException();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        //TODO: Implement database logic here

        return archers;
    }

    @Override
    public Optional<Archer> findById(String id) {
        // Find archer by ID in the dummy data
        for (Archer archer : archers) {
            if (archer.getId().equals(id)) {
                return Optional.of(archer);
            }
        }

        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public boolean deleteById(String id) {
        // Dummy implementation
        return archers.removeIf(archer -> archer.getId().equals(id));
        // TODO Auto-generated method stub
    }

    @Override
    public boolean save(Archer entity) {
        // Dummy implementation
        return archers.add(entity);
        // TODO Auto-generated method stub
    }

    @Override
    public boolean update(Archer entity) {
        // Dummy implementation
        for (int i = 0; i < archers.size(); i++) {
            if (archers.get(i).getId().equals(entity.getId())) {
                archers.set(i, entity);
                return true;
            }
        }

        // TODO Auto-generated method stub
        return false;
    }
}
