package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public final class FileUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Path FIXTURES = Path.of("src/test/resources/fixtures");

    private FileUtils() { }

    private static Path getPath(final Path filePath) {
        Path resultPath = filePath.isAbsolute()
            ? filePath : FIXTURES.resolve(filePath);
        return resultPath.toAbsolutePath().normalize();
    }

    public static Map<String, Object> readJson(final Path filePath)
        throws IOException {
        var normalizePath = filePath.isAbsolute()
                ? filePath.normalize() : getPath(filePath);

        return MAPPER.readValue(
            normalizePath.toFile(),
            new TypeReference<Map<String, Object>>() { });
    }
}
