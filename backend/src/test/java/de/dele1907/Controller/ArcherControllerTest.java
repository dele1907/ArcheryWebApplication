package de.dele1907.Controller;

import de.dele1907.Database.Repository.ArcherRepository;
import de.dele1907.Database.Repository.ShootersClubRepository;
import de.dele1907.Dto.ArcherDTO;
import de.dele1907.Service.ArcherService;
import de.dele1907.Service.ClubService;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArcherControllerTest {

    private static ArcherService mockService;

    @BeforeAll
    static void setup() {
        mockService = new ArcherService(new ArcherRepository(), new ClubService(new ShootersClubRepository())) {
            @Override
            public List<ArcherDTO> getAllEntities() {
                return List.of(
                        new ArcherDTO("4711","Doe", "John", "42", "BSC","Recurve", Date.valueOf(LocalDate.of(1970, 1, 1)), "Adult", 4711),
                        new ArcherDTO("0815","Smith", "Jane", "99",  "Gilde","Compound", Date.valueOf(LocalDate.of(1970, 1, 1)), "Junior", 815)
                );
            }

            @Override
            public ArcherDTO getEntityById(String id) {
                if (id.equals("4711")) {
                    return new ArcherDTO("4711","Napp", "Karl", "1234","BSC", "Recurve", Date.valueOf(LocalDate.of(1970, 1, 1)), "Adult", 4711);
                }
                return null;
            }
        };
    }

    @Test
    void testGetAllArchersEndpoint() {
        Javalin app = Javalin.create();
        new ArcherController(mockService).registerRoutes(app);

        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/archers");
            assertEquals(200, response.code());
            String json = response.body().string();

            assertTrue(json.contains("Doe"));
            assertTrue(json.contains("Smith"));
        });
    }

    @Test
    void getArcherById() {
        String id = "4711";

        Javalin app = Javalin.create();
        new ArcherController(mockService).registerRoutes(app);

        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/archers/" + id);
            assertEquals(200, response.code());
            assertTrue(response.body().string().contains("Napp"));
        });
    }

    @Test
    void deleteArcherById() {
        String id = "0815";

        Javalin app = Javalin.create();
        new ArcherController(mockService).registerRoutes(app);

        JavalinTest.test(app, (server, client) -> {
            var response = client.delete("/archers/" + id);
            assertEquals(200, response.code());
            assertTrue(response.body().string().contains("Archer deleted"));
        });
    }

    @Test
    void createNewArcher() {
        Javalin app = Javalin.create();
        new ArcherController(mockService).registerRoutes(app);
        String newArcherJson = """
                {
                    "name": "Doe",
                    "firstName": "Jane",
                    "clubId": 1234,
                    "clubName": "NewClub",                
                    "bowType": "Recurve",
                    "birthDate": "1970-01-01",
                    "ageCategory": "Adult",
                    "passportNumber": 5678
                }
                """;
        JavalinTest.test(app, (server, client) -> {
            var response = client.post("/archers", newArcherJson);
            assertEquals(201, response.code());
            assertTrue(response.body().string().contains("Archer created"));
        });
    }

    @Test
    void updateArcher() {
        Javalin app = Javalin.create();
        new ArcherController(mockService).registerRoutes(app);
        String updatedArcherJson = """
                {
                    "id": "4711",
                    "name": "Nappos",
                    "firstName": "Karlos",
                    "clubId": 1234,
                    "clubName": "BSC",
                    "bowType": "Recurve",
                    "birthDate": "1970-01-01",
                    "ageCategory": "Adult",
                    "passportNumber": 4711
                }
                """;
        JavalinTest.test(app, (server, client) -> {
            var response = client.put("/archers/" + "4711", updatedArcherJson);
            assertEquals(200, response.code());
            assertTrue(response.body().string().contains("Archer updated"));
        });
    }
}
