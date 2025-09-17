package de.dele1907.Service;

import de.dele1907.Database.Repository.ArcherRepository;
import de.dele1907.Model.Shooter.Archer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ArcherServiceTest {

    private static ArcherService mockArcherService;
    private static final List<Archer> archers = new ArrayList<>();

    @BeforeAll
    static void setup() {
        mockArcherService = new ArcherService(new ArcherRepository());
        archers.add(new Archer("4711", "Napp", "Karl", "Beispielverein", 1234, "Recurve", "Adult", 4711));
        archers.add(new Archer("0815","Mustermann", "Max", "Musterverein", 5678, "Compound", "Junior", 815));
    }

    @Test
    void testGetAllEntities() {
        List<Archer> result = mockArcherService.getAllEntities();
        assert result.getFirst().getFirstName().equals(archers.getFirst().getFirstName());
    }

    @Test
    void testGetEntityById() {
        Archer result = mockArcherService.getEntityById("4711");

        assert result != null;
        assert result.getFirstName().equals("Karl");
    }

    @Test
    void testSaveNewEntity() {
        Archer newArcher = new Archer("1234", "Doe", "Jane", "NewClub", 4321, "Recurve", "Adult", 1234);
        boolean isSaved = mockArcherService.saveNewEntity(newArcher);

        assert isSaved;
        assert mockArcherService.getEntityById("1234") != null && mockArcherService.getEntityById("1234").getFirstName().equals("Jane");
    }

    @Test
    void testUpdateEntity() {
        Archer updatedArcher = new Archer("0815", "Nappos", "Karlos", "Beispielverein", 1234, "Recurve", "Adult", 4711);
        mockArcherService.updateEntity(updatedArcher);

        Archer result = mockArcherService.getEntityById("0815");

        assert result != null;
    }
}
