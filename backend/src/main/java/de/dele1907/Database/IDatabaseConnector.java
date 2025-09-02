package de.dele1907.Database;

import java.sql.Connection;
import java.util.Optional;

public interface IDatabaseConnector {
    Optional<Connection> connect();
    void disconnect();
    boolean isConnected();
}
