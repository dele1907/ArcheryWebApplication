package de.dele1907.Controller;

import de.dele1907.Service.IBaseService;
import io.javalin.Javalin;

public abstract class BaseController {
    private final IBaseService<?> service;

    public BaseController(IBaseService<?> service) {
        this.service = service;
    }

    public IBaseService<?> getService() {
        return service;
    }

    public abstract void registerRoutes(Javalin app);
}
