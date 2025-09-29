package de.dele1907.Service;

import de.dele1907.Database.Repository.ArcherRepository;
import de.dele1907.Database.Repository.ShootersClubRepository;
import de.dele1907.Dto.ArcherDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArcherServiceTest {

    private static ArcherService mockArcherService;
    private static final List<ArcherDTO> archers = new ArrayList<>();

    @BeforeAll
    static void setup() {
        mockArcherService = new ArcherService(new ArcherRepository(), new ClubService(new ShootersClubRepository()));
        archers.add(new ArcherDTO("4711", "Napp", "Karl", "1234","BSC", "Recurve", Date.valueOf(LocalDate.of(1970, 1, 1)),  "Adult", 4711));
        archers.add(new ArcherDTO("0815","Mustermann", "Max", "5678","Gilde", "Compound", Date.valueOf(LocalDate.of(1970, 1, 1)), "Junior", 815));
    }

    @Test
    void testGetAllEntities() {
        var result = mockArcherService.getAllEntities();
        assert result.getFirst().firstName().equals(archers.getFirst().firstName());
    }

    @Test
    void testGetEntityById() {
        var result = mockArcherService.getEntityById("4711");

        assert result != null;
        assert result.firstName().equals("Karl");
    }

    @Test
    void testSaveNewEntity() {
        var newArcher = new ArcherDTO("1234", "Doe", "Jane", "4321","Gilde", "Recurve", Date.valueOf(LocalDate.of(1970, 1, 1)), "Adult", 1234);
        boolean isSaved = mockArcherService.saveNewEntity(newArcher);

        assert isSaved;
        assert mockArcherService.getEntityById("1234") != null && mockArcherService.getEntityById("1234").firstName().equals("Jane");
    }

    @Test
    void testUpdateEntity() {
        var updatedArcher = new ArcherDTO("0815", "Nappos", "Karlos", "1234","BSC", "Recurve", Date.valueOf(LocalDate.of(1970, 1, 1)), "Adult", 4711);
        mockArcherService.updateEntity(updatedArcher);

        var result = mockArcherService.getEntityById("0815");

        assert result != null;
    }
}
