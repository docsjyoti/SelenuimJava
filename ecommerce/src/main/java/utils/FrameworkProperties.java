package utils;

import java.io.InputStream;
import java.util.Properties;

public class FrameworkProperties {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            String propFileName = Constants.PROP_FILE_NAME;

            InputStream inputStream = FrameworkProperties.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new RuntimeException(Constants.FILE_NOT_FOUND_MESSAGE);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load properties file: " + e.getMessage(), e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
