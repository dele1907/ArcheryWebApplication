package de.dele1907.Exception;

public class NoArchesFoundException extends RepositoryException {
    public NoArchesFoundException() {
      super("No archers found in the database.");
    }
}
