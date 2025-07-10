package utils;

import java.io.InputStream;
import java.util.Properties;

public class FrameworkProperties {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = FrameworkProperties.class.getClassLoader()
                .getResourceAsStream(Constants.PROP_FILE_NAME)) {

            if (input != null) {
                properties.load(input);
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
