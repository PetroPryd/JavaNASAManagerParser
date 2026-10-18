package hr.algebra.dal.sql;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import hr.algebra.dal.DataAccessException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public final class DataSourceSingleton {

    private static final String PATH = "/config/db.properties";

    private static final String SERVER_NAME = "SERVER_NAME";
    private static final String DATABASE_NAME = "DATABASE_NAME";
    private static final String USER = "USER";
    private static final String PASSWORD = "PASSWORD";

    private static DataSource instance;

    private DataSourceSingleton() {
    }

    public static synchronized DataSource getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }

    private static DataSource createInstance() {
        Properties properties = loadProperties();
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setURL(String.format(
                "jdbc:sqlserver://%s;databaseName=%s;user=%s;password=%s;encrypt=false;trustServerCertificate=true",
                required(properties, SERVER_NAME),
                required(properties, DATABASE_NAME),
                properties.getProperty(USER),
                properties.getProperty(PASSWORD)));
        return dataSource;
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream is = DataSourceSingleton.class.getResourceAsStream(PATH)) {
            if (is == null) {
                throw new DataAccessException("Cannot find " + PATH + " on the classpath. Copy db.properties.example to db.properties and fill in your SQL Server login.");
            }
            properties.load(is);
        } catch (IOException ex) {
            throw new DataAccessException("Failed to read " + PATH, ex);
        }
        return properties;
    }

    private static String required(Properties properties, String key) {
        String value = properties.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new DataAccessException("Missing required setting '" + key + "' in " + PATH);
        }
        return value;
    }
}
