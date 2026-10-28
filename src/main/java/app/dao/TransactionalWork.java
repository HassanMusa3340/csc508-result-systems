package app.dao;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface TransactionalWork {
    void execute(Connection connection) throws SQLException;
}
