package app.dao;

import app.util.Config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/** Hands out JDBC connections. The URL comes from config.properties, never from Java source. */
public class Database {
    private final String url;

    public Database(String url) { this.url = url; }

    public static Database fromConfig() {
        Properties p = Config.load();
        return new Database(p.getProperty("db.url"));
    }

    public Connection getConnection() throws SQLException {
        Connection c = DriverManager.getConnection(url);
        try (Statement s = c.createStatement()) {
            s.execute("PRAGMA foreign_keys = ON"); // SQLite ignores FKs unless enabled per connection
        }
        return c;
    }

    /** Runs a static DDL/DML script from the classpath (e.g. /db/schema.sql). */
    public void runScript(String resourcePath) {
        try (InputStream in = Database.class.getResourceAsStream(resourcePath)) {
            if (in == null) throw new IllegalStateException("Script not found: " + resourcePath);
            String text = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            StringBuilder sql = new StringBuilder();
            for (String line : text.split("\\R")) {
                if (!line.trim().startsWith("--")) sql.append(line).append('\n');
            }
            try (Connection c = getConnection(); Statement s = c.createStatement()) {
                for (String stmt : sql.toString().split(";")) {
                    if (!stmt.isBlank()) s.execute(stmt);
                }
            }
        } catch (IOException | SQLException e) {
            throw new DataAccessException("Could not run script " + resourcePath, e);
        }
    }
}
