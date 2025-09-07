package de.dele1907.Controller;

import de.dele1907.Model.Archer;
import de.dele1907.Service.ArcherService;
import io.javalin.Javalin;

import java.util.UUID;

public class ArcherController extends BaseController<Archer>{

    public ArcherController(ArcherService archerService) {
        super(archerService);
    }

    public void registerRoutes(Javalin app) {
        registerGetAllArchers(app);
        registerGetUserById(app);
        registerCreateNewArcher(app);
        registerDeleteArcher(app);
    }

    private void registerGetAllArchers(Javalin app) {
        app.get("/archers" , ctx -> ctx.json(this.getService().getAllEntities()));
    }

    private void registerGetUserById(Javalin app) {
        app.get("/archers/{id}", ctx -> {
            String id = ctx.pathParam("id");
            var archer = this.getService().getEntityById(id);

            if (archer != null) {
                ctx.json(archer);
            } else {
                ctx.status(404).result("Archer not found");
            }
        });
    }

    private void registerCreateNewArcher(Javalin app) {
        app.post("/archers", ctx -> {
            var result = ctx.bodyAsClass(de.dele1907.Model.Archer.class);
            var archer = new Archer(
                    UUID.randomUUID().toString(),
                    result.getName(),
                    result.getFirstName(),
                    result.getClub(),
                    result.getClubNumber(),
                    result.getBowType(),
                    result.getAgeCategory(),
                    result.getPassportNumber()
            );
            boolean success = this.getService().saveNewEntity(archer);

            if (success) {
                ctx.status(201).result("Archer created");
            } else {
                ctx.status(500).result("Failed to create archer");
            }
        });
    }

    private void registerDeleteArcher(Javalin app) {
        app.delete("/archers/{id}", ctx -> {
            String id = ctx.pathParam("id");
            boolean success = this.getService().deleteEntityById(id);

            if (success) {
                ctx.status(200).result("Archer deleted");
            } else {
                ctx.status(404).result("Archer not found");
            }
        });
    }
}
