package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Connexion {
    private static Connexion instance;
    private Connection conn;

    private static final String PROPERTIES_FILE = "base.properties/base.properties";

    private static String URL;
    private static String USER;
    private static String PASS;
    private static String DRIVER;

    // Chargement unique au démarrage (static block)
    static {
        Properties p = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            p.load(fis);
            DRIVER = p.getProperty("jdbc.driver");
            URL    = p.getProperty("jdbc.url");
            USER   = p.getProperty("jdbc.username");
            PASS   = p.getProperty("jdbc.password");

            if (URL == null || USER == null || PASS == null) {
                throw new IOException("Paramètres manquants dans " + PROPERTIES_FILE);
            }

            // Chargement du driver (comme dans les anciens cours)
            Class.forName(DRIVER);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Erreur chargement configuration JDBC : " + e.getMessage(), e);
        }
    }

    private Connexion() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASS);
        conn.setAutoCommit(true);
    }

    public static synchronized Connexion getInstance() throws SQLException {
        if (instance == null || instance.conn == null || instance.conn.isClosed()) {
            instance = new Connexion();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ignored) {}
        }
    }
}