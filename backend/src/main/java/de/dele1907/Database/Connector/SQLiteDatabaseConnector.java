package de.dele1907.Database.Connector;

import java.sql.Connection;
import java.util.Optional;

public class SQLiteDatabaseConnector implements IDatabaseConnector {
    @Override
    public Optional<Connection> connect() {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public void disconnect() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isConnected() {
        // TODO Auto-generated method stub
        return false;
    }
}
