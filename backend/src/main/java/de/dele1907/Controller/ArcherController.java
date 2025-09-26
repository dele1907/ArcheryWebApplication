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
            app.get(this.PATH , ctx -> ctx.json(this.getService().getAllEntities()));
    }

    private void registerGetArcherById(Javalin app) {
        app.get(this.PATH + "/{id}", ctx -> {
            String id = ctx.pathParam("id");
            var archer = this.getService().getEntityById(id);

            if (archer != null) {
                ctx.json(archer);
            } else {
                ctx.status(404).result("Archer not found");
                loggerLogArcherNotFoundById(id);
            }
        });
    }

    private void registerCreateNewArcher(Javalin app) {
        app.post(this.PATH, ctx -> {
            var result = ctx.bodyAsClass(ArcherDTO.class);
            var archer = new ArcherDTO(
                    UUID.randomUUID().toString(),
                    result.name(),
                    result.firstName(),
                    result.clubId(),
                    result.clubName(),
                    result.bowType(),
                    result.ageCategory(),
                    result.passportNumber()
            );
            boolean success = this.getService().saveNewEntity(archer);

            if (success) {
                ctx.status(201).result("Archer created");
            } else {
                ctx.status(500).result("Failed to create archer");
                LOGGER.error("Failed to create archer");
            }
        });
    }

    private void registerDeleteArcher(Javalin app) {
        app.delete(this.PATH + "/{id}", ctx -> {
            String id = ctx.pathParam("id");
            boolean success = this.getService().deleteEntityById(id);

            if (success) {
                ctx.status(200).result("Archer deleted");
            } else {
                ctx.status(404).result("Archer not found");
                loggerLogArcherNotFoundById(id);
            }
        });
    }

    private void registerUpdateArcher(Javalin app) {
        app.put(this.PATH + "/{id}", ctx -> {
            String id = ctx.pathParam("id");
            var result = ctx.bodyAsClass(ArcherDTO.class);
            var archer = new ArcherDTO(
                    id,
                    result.name(),
                    result.firstName(),
                    result.clubId(),
                    result.clubName(),
                    result.bowType(),
                    result.ageCategory(),
                    result.passportNumber()
            );
            boolean success = this.getService().updateEntity(archer);

            if (success) {
                ctx.status(200).result("Archer updated");
            } else {
                ctx.status(404).result("Archer not found");
                loggerLogArcherNotFoundById(id);
            }
        });
    }

    private void loggerLogArcherNotFoundById(String id) {
        LOGGER.error("Archer with id {} not found", id);
    }
}
