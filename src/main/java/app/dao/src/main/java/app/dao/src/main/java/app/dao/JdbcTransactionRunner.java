package app.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcTransactionRunner implements TransactionRunner {
    private final Database db;

    public JdbcTransactionRunner(Database db) { this.db = db; }

    @Override
    public void run(TransactionalWork work) {
        try (Connection c = db.getConnection()) {
            try {
                c.setAutoCommit(false);          // 1. disable auto-commit
                work.execute(c);                 // 2. all writes share this one connection
                c.commit();                      // 3. commit only if every write succeeded
            } catch (SQLException | RuntimeException e) {
                try {
                    c.rollback();                // 4. undo every write on any failure
                } catch (SQLException rollbackFailure) {
                    e.addSuppressed(rollbackFailure);
                }
                if (e instanceof RuntimeException re) throw re;
                throw new DataAccessException("Transaction failed, changes rolled back", e);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Could not open or close connection", e);
        }
    }
}
