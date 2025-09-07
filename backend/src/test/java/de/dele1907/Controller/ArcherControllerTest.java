package de.dele1907.Controller;

import de.dele1907.Model.Archer;
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
        mockService = new ArcherService() {
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
}
