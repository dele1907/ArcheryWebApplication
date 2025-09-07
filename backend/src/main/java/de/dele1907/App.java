package de.dele1907;

import de.dele1907.Controller.ArcherController;
import de.dele1907.Database.Repository.ArcherRepository;
import de.dele1907.Service.ArcherService;
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

        new ArcherController(new ArcherService(new ArcherRepository())).registerRoutes(app);
    }
}
