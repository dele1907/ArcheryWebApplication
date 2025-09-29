package de.dele1907.Controller;

import de.dele1907.Dto.ArcherDTO;
import de.dele1907.Service.ArcherService;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class ArcherController extends BaseController<ArcherDTO>{
    public static final Logger LOGGER = LoggerFactory.getLogger(ArcherController.class);

    public ArcherController(ArcherService archerService) {
        super(archerService);
        this.PATH = "/archers";
    }

    public void registerRoutes(Javalin app) {
        registerGetAllArchers(app);
        registerGetArcherById(app);
        registerCreateNewArcher(app);
        registerDeleteArcher(app);
        registerUpdateArcher(app);
    }

    private void registerGetAllArchers(Javalin app) {
        try {
            app.get(this.PATH, ctx -> ctx.json(this.getService().getAllEntities()));
        } catch (Exception e) {
            LOGGER.error("Error retrieving archers: {}", e.getMessage());
            app.get(this.PATH, ctx -> ctx.status(500).result("Error retrieving archers"));
        }
    }

    private void registerGetArcherById(Javalin app) {
        try {
            app.get(this.PATH + "/{id}", ctx -> {
                String id = ctx.pathParam("id");
                var archer = this.getService().getEntityById(id);
                ctx.json(archer);
            });
        } catch (Exception e) {
            LOGGER.error("Error retrieving archer by ID: {}", e.getMessage());
            app.get(this.PATH + "/{id}", ctx -> ctx.status(500).result("Error retrieving archer by ID"));
        }
    }

    private void registerCreateNewArcher(Javalin app) {
        try {
            app.post(this.PATH, ctx -> {
                var result = ctx.bodyAsClass(ArcherDTO.class);
                var archer = new ArcherDTO(
                        UUID.randomUUID().toString(),
                        result.name(),
                        result.firstName(),
                        result.clubId(),
                        "", // Club name is not provided when creating a new archer
                        result.bowType(),
                        result.birthDate(),
                        "", // Age category is not provided when creating a new archer
                        result.passportNumber()
                );
                this.getService().saveNewEntity(archer);

                ctx.status(201).result("Archer created");
            });
        } catch (Exception e) {
            LOGGER.error("Error creating new archer: {}", e.getMessage());
            app.post(this.PATH, ctx -> ctx.status(500).result("Error creating new archer"));
        }
    }

    private void registerDeleteArcher(Javalin app) {
        try {
            app.delete(this.PATH + "/{id}", ctx -> {
                String id = ctx.pathParam("id");
                this.getService().deleteEntityById(id);

                ctx.status(200).result("Archer deleted");
            });
        } catch (Exception e) {
            LOGGER.error("Error deleting archer: {}", e.getMessage());
            app.delete(this.PATH + "/{id}", ctx -> ctx.status(500).result("Error deleting archer"));
        }
    }

    private void registerUpdateArcher(Javalin app) {
        try {
            app.put(this.PATH + "/{id}", ctx -> {
                String id = ctx.pathParam("id");
                var result = ctx.bodyAsClass(ArcherDTO.class);
                var archer = new ArcherDTO(
                        id,
                        result.name(),
                        result.firstName(),
                        result.clubId(),
                        "",
                        result.bowType(),
                        result.birthDate(),
                        "",
                        result.passportNumber()
                );
                this.getService().updateEntity(archer);

                ctx.status(200).result("Archer updated");
            });
        } catch (Exception e) {
            LOGGER.error("Error updating archer: {}", e.getMessage());
            app.put(this.PATH + "/{id}", ctx -> ctx.status(500).result("Error updating archer"));
        }
    }
}
