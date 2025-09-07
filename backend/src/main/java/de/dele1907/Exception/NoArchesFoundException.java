package de.dele1907.Exception;

public class NoArchesFoundException extends RuntimeException {
    public NoArchesFoundException() {
      super("No archers found in the database.");
    }
}
