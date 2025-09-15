package de.dele1907.Exception;

public class NoArchesFoundException extends RepositoryException {
    public NoArchesFoundException() {
      super("No archers found in the database.");
    }

    public NoArchesFoundException(String id) {
        super("No archers found having the following id: " + id);
    }
}
