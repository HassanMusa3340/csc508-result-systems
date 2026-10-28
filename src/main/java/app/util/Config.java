package app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** Loads settings from config.properties (never hard-code credentials in Java). */
public final class Config {
    private Config() { }

    public static Properties load() {
        try (InputStream in = Config.class.getResourceAsStream("/config.properties")) {
            if (in == null) {
                throw new IllegalStateException("config.properties not found. Copy "
                        + "src/main/resources/config.properties.example to config.properties.");
            }
            Properties p = new Properties();
            p.load(in);
            return p;
        } catch (IOException e) {
            throw new IllegalStateException("Could not read config.properties", e);
        }
    }
}
