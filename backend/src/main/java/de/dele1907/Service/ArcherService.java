package de.dele1907.Service;

import de.dele1907.Model.Archer;

import java.util.ArrayList;
import java.util.List;

public class ArcherService implements IArcherService {
    private final List<Archer> archers = new ArrayList<>();

    public ArcherService() {
        archers.add(new Archer("4711", "Napp", "Karl", "Beispielverein", 1234, "Recurve", "Adult"));
        archers.add(new Archer("0815","Mustermann", "Max", "Musterverein", 5678, "Compound", "Junior"));
    }

    public List<Archer> getAllArchers() {
        return archers;
    }

    public Archer getArcherById(String id) {
        return archers.stream()
                .filter(archer -> archer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
