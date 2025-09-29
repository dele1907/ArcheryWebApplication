package de.dele1907.Controller;

import de.dele1907.Database.Repository.ShootersClubRepository;
import de.dele1907.Model.Club.ShootersClub;
import de.dele1907.Service.ClubService;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClubControllerTest {
    private static ClubService mockService;

    @BeforeAll
    static void setup() {
        mockService = new ClubService(new ShootersClubRepository()) {
            @Override
            public List<ShootersClub> getAllEntities() {
                return List.of(
                        new ShootersClub("66693", "BogenschützenClub"),
                        new ShootersClub("44251", "Schützengilde"),
                        new ShootersClub("12345", "TestClub")
                );
            }
        };
    }

    @Test
    void testGetAllClubsEndpoint() {
        Javalin app = Javalin.create();
        new ClubController(mockService).registerRoutes(app);

        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/clubs");
            assert(response.code() == 200);
            String json = response.body().string();

            assert(json.contains("BogenschützenClub"));
            assert(json.contains("Schützengilde"));
        });
    }

    @Test
    void testDeleteClubByIdEndpoint() {
        String clubIdToDelete = "12345";

        Javalin app = Javalin.create();
        new ClubController(mockService).registerRoutes(app);

        JavalinTest.test(app, (server, client) -> {
            var response = client.delete("/clubs/" + clubIdToDelete);
            assertEquals(200, response.code());
            String result = response.body().string();
            assertEquals("Club deleted successfully", result);
        });
    }

    @Test
    void testCreateNewClubEndpoint() {
        Javalin app = Javalin.create();
        new ClubController(mockService).registerRoutes(app);

        String newClubJson = """
                {
                    "clubId": "54321",
                    "clubName": "Neuer Club"
                }
                """;

        JavalinTest.test(app, (server, client) -> {
            var response = client.post("/clubs", newClubJson);
            assertEquals(201, response.code());
            String result = response.body().string();
            assertEquals("Club created successfully", result);
        });
    }

    @Test
    void testUpdateClubEndpoint() {
        Javalin app = Javalin.create();
        new ClubController(mockService).registerRoutes(app);

        String updatedClubJson = """
                {
                    "clubId": "44251",
                    "clubName": "AktualisierterClub"
                }
                """;

        JavalinTest.test(app, (server, client) -> {
            var response = client.put("/clubs/44251", updatedClubJson);
            assertEquals(200, response.code());
            String result = response.body().string();
            assertEquals("Club updated successfully", result);
        });
    }
}
