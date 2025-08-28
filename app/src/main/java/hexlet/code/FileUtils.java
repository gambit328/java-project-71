package hexlet.code;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class FileUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    // нужно поправить метод, чтобы он работал с абсолютными и относительными путями
//    private static Path getPath(String fileName) {
//        return Paths.get("src", "main", "resources", fileName)
//                .toAbsolutePath().normalize();
//    }

    public static Map<String, Object> readJson(Path fileName) throws IOException {
        var normalizePath = fileName.isAbsolute() ? fileName.normalize() : fileName.toAbsolutePath().normalize();
        return objectMapper.readValue(normalizePath.toFile(), new TypeReference<Map<String, Object>>() {});
    }


}
