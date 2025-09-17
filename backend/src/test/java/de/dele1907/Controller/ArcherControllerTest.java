package de.dele1907.Controller;

import de.dele1907.Database.Repository.ArcherRepository;
import de.dele1907.Model.Shooter.Archer;
import de.dele1907.Service.ArcherService;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArcherControllerTest {

    private static ArcherService mockService;

    @BeforeAll
    static void setup() {
        mockService = new ArcherService(new ArcherRepository()) {
            @Override
            public List<Archer> getAllEntities() {
                return List.of(
                        new Archer("4711","Doe", "John", "TestClub", 42, "Recurve", "Adult", 4711),
                        new Archer("0815","Smith", "Jane", "AnotherClub", 99, "Compound", "Junior", 815)
                );
            }

            @Override
            public Archer getEntityById(String id) {
                if (id.equals("4711")) {
                    return new Archer("4711","Napp", "Karl", "Beispielverein", 1234, "Recurve", "Adult", 4711);
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
                    "club": "NewClub",
                    "clubNumber": 1234,
                    "bowType": "Recurve",
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
                    "club": "Beispielverein",
                    "clubNumber": 1234,
                    "bowType": "Recurve",
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
