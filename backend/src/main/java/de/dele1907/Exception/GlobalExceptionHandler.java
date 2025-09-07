package de.dele1907.Exception;

import io.javalin.Javalin;
import org.slf4j.LoggerFactory;

public class GlobalExceptionHandler {
    public static void registerExceptionHandlers(Javalin app) {
        var logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

        app.exception(RepositoryException.class, (e, ctx) -> {

            logger.error(e.getMessage());

            if (e instanceof NoArchesFoundException) {
                ctx.status(404).result(e.getMessage());
            } else {
                ctx.status(500).result("Internal Server Error: " + e.getMessage());
            }
        });
    }
}
