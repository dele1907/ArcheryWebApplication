package de.dele1907.Service;

import de.dele1907.Model.Archer;

import java.util.ArrayList;
import java.util.List;

public class ArcherService implements IBaseService<Archer> {
    private final List<Archer> archers = new ArrayList<>();

    public ArcherService() {
        archers.add(new Archer("4711", "Napp", "Karl", "Beispielverein", 1234, "Recurve", "Adult", 4711));
        archers.add(new Archer("0815","Mustermann", "Max", "Musterverein", 5678, "Compound", "Junior", 815));
    }

    public List<Archer> getAllEntities() {
        return archers;
    }

    public Archer getEntityById(String id) {
        return archers.stream()
                .filter(archer -> archer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteEntityById(String id) {
        return archers.removeIf(archer -> archer.getId().equals(id));
    }

    public boolean saveNewEntity(Archer archer) {
        return archers.add(archer);
    }

    public void updateEntity(Archer entity) {
        for (int i = 0; i < archers.size(); i++) {
            if (archers.get(i).getId().equals(entity.getId())) {
                archers.set(i, entity);
                return;
            }
        }
    }
}
