package de.dele1907.Controller;

import de.dele1907.Service.IBaseService;
import io.javalin.Javalin;

public abstract class BaseController<P> {
    private final IBaseService<P> service;

    public BaseController(IBaseService<P> service) {
        this.service = service;
    }

    public IBaseService<P> getService() {
        return service;
    }

    public abstract void registerRoutes(Javalin app);
}
