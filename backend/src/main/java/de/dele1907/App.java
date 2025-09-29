package de.dele1907;

import de.dele1907.Controller.ArcherController;
import de.dele1907.Controller.ClubController;
import de.dele1907.Database.Repository.ArcherRepository;
import de.dele1907.Database.Repository.ShootersClubRepository;
import de.dele1907.Exception.GlobalExceptionHandler;
import de.dele1907.Service.ArcherService;
import de.dele1907.Service.ClubService;
import io.javalin.Javalin;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(rule -> {
                    rule.allowHost("http://localhost:5174");
                });
            });
        }).start(7070);

        GlobalExceptionHandler.registerExceptionHandlers(app);

        //#region Services
        var clubService = new ClubService(new ShootersClubRepository());
        var archerService = new ArcherService(new ArcherRepository(), clubService);
        //#endregion Services

        //#region Controllers
        var archerController = new ArcherController(archerService);
        var clubController = new ClubController(clubService);
        //#endregion Controllers

        archerController.registerRoutes(app);
        clubController.registerRoutes(app);
    }
}
