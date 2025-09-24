package de.dele1907.Service;

import de.dele1907.Model.Club.ShootersClub;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ClubServiceTest {

    private static ClubService mockClubService;
    private static final List<ShootersClub> clubs = new ArrayList<>();

    @BeforeAll
    static void setup() {
        mockClubService = new ClubService(new de.dele1907.Database.Repository.ShootersClubRepository());
        clubs.add(new ShootersClub("4711", "BSC"));
        clubs.add(new ShootersClub("815", "Gilde"));
    }

    @Test
    void getAllEntities() throws Exception {
        List<ShootersClub> result = mockClubService.getAllEntities();
        assert result.getFirst().clubName().equals(clubs.getFirst().clubName());
    }

    @Test
    void getEntityById() {
        ShootersClub result = mockClubService.getEntityById("4711");

        assert result != null;
        assert result.clubName().equals("BSC");
    }

    @Test
    void saveNewEntity() {
        ShootersClub newClub = new ShootersClub("1234", "NewClub");
        boolean isSaved = mockClubService.saveNewEntity(newClub);

        assert isSaved;
        assert mockClubService.getEntityById("1234") != null && mockClubService.getEntityById("1234").clubName().equals("NewClub");
    }

    @Test
    void updateEntity() {
        ShootersClub updatedClub = new ShootersClub("815", "NewGilde");
        mockClubService.updateEntity(updatedClub);

        ShootersClub result = mockClubService.getEntityById("815");

        assert result != null;
        assert result.clubName().equals("NewGilde");
    }
}
