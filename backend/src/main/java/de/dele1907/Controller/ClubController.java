package de.dele1907.Controller;

import de.dele1907.Model.Club.ShootersClub;
import de.dele1907.Service.ClubService;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClubController extends BaseController<ShootersClub>{
    public static final Logger LOGGER = LoggerFactory.getLogger(ClubController.class);

    public ClubController(ClubService service) {
        super(service);
        this.PATH = "/clubs";
    }

    @Override
    public void registerRoutes(Javalin app) {
        registerGetAllClubs(app);
        registerUpdateClub(app);
        registerCreateNewClub(app);
        registerDeleteClub(app);
    }

    public void registerGetAllClubs(Javalin app) {
        try {
            app.get(this.PATH, ctx -> ctx.json(this.getService().getAllEntities()));
        } catch (Exception e) {
            LOGGER.error("Error retrieving clubs: {}", e.getMessage());
            app.get(this.PATH, ctx -> ctx.status(500).result("Error retrieving clubs"));
        }
    }

    public void registerUpdateClub(Javalin app) {
        try {
            app.put(this.PATH + "/{id}", ctx -> {
                String id = ctx.pathParam("id");
                var result = ctx.bodyAsClass(ShootersClub.class);
                if (!id.equals(result.clubId())) {
                    ctx.status(400).result("ID in path and body do not match");
                    return;
                }
                 this.getService().updateEntity(result);

                ctx.status(200).result("Club updated successfully");
            });
        } catch (Exception e) {
            LOGGER.error("Error updating club: {}", e.getMessage());
            app.put(this.PATH + "/{id}", ctx -> ctx.status(500).result("Error updating club"));
        }
    }

    public void registerCreateNewClub(Javalin app) {
        try {
            app.post(this.PATH, ctx -> {
                var result = ctx.bodyAsClass(ShootersClub.class);
                var club = new ShootersClub(
                        result.clubId(),
                        result.clubName()
                );
                this.getService().saveNewEntity(club);

                ctx.status(201).result("Club created successfully");
            });
        } catch (Exception e) {
            LOGGER.error("Error creating new club: {}", e.getMessage());
            app.post(this.PATH, ctx -> ctx.status(500).result("Error creating new club"));
        }
    }

    public void registerDeleteClub(Javalin app) {
        try {
            app.delete(this.PATH + "/{id}", ctx -> {
                String id = ctx.pathParam("id");
                this.getService().deleteEntityById(id);

                ctx.status(200).result("Club deleted successfully");
            });
        } catch (Exception e) {
            LOGGER.error("Error deleting club: {}", e.getMessage());
            app.delete(this.PATH + "/{id}", ctx -> ctx.status(500).result("Error deleting club"));
        }
    }
}
