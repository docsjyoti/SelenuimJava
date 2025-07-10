package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import model.UserData;

import java.io.InputStream;
import java.util.Map;

public class TestDataReader {

    private static Map<String, UserData> users;

    static {
        loadYamlData();  // Ensures data is loaded at class load time
    }

    private static void loadYamlData() {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            InputStream inputStream = TestDataReader.class
                    .getClassLoader()
                    .getResourceAsStream(Constants.TEST_DATA_FILE);

            if (inputStream == null) {
                throw new RuntimeException("YAML file not found at path: " + Constants.TEST_DATA_FILE);
            }

            users = mapper.readValue(inputStream, new TypeReference<Map<String, UserData>>() {});
        } catch (Exception e) {
            throw new RuntimeException(Constants.DATA_NOT_FOUND_MESSAGE, e);
        }
    }

    public static UserData getUser(String userKey) {
        System.out.println("Loaded user data keys: " + users.keySet());
        return users.get(userKey);
    }
}