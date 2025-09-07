package de.dele1907.Controller;

import de.dele1907.Service.ArcherService;
import io.javalin.Javalin;

public class ArcherController extends BaseController{

    public ArcherController(ArcherService archerService) {
        super(archerService);
    }

    public void registerRoutes(Javalin app) {
        registerGetAllArchers(app);
        registerGetUserById(app);
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
}
