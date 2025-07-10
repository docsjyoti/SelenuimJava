package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            String propFileName = Constants.TEST_DATA_FILE;

            InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new RuntimeException(Constants.FILE_NOT_FOUND_MESSAGE);
            }

        } catch (Exception e) {
            throw new RuntimeException(Constants.DATA_NOT_FOUND_MESSAGE, e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}