package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties;

    static {
        try {
            properties = new Properties();
            String dataFileName = Constants.TEST_DATA_FILE;

            InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(dataFileName);
            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(Constants.DATA_NOT_FOUND_MESSAGE, e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}