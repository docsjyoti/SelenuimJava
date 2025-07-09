package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

    static {
        try {
            prop = new Properties();
            FileInputStream input = new FileInputStream(Constants.TEST_DATA_FILE);
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
