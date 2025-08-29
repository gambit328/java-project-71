package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class FileUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> readJson(Path filePath) throws IOException {
        var normalizePath = filePath.isAbsolute() ? filePath.normalize() : filePath.toAbsolutePath().normalize();

        return objectMapper.readValue(normalizePath.toFile(), new TypeReference<Map<String, Object>>() {});
    }
}
