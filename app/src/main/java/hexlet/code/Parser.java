package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;


public final class Parser {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final YAMLMapper MAPPER_YAML = new YAMLMapper();
    private static final Path FIXTURES = Path.of(
        "src/test/resources/fixtures/input_files"
    );

    private Parser() { }

    public static Map<String, Object> readFile(Path filePath)
        throws IOException {
        var normalizedPath = filePath.isAbsolute()
                ? filePath.normalize() : getPath(filePath);

        var fileName = normalizedPath.getFileName().toString();
        var fileType = fileName.substring(fileName.lastIndexOf('.') + 1);

        return switch (fileType) {
            case "yaml", "yml" -> readYaml(normalizedPath);
            case "json" -> readJson(normalizedPath);
            default -> throw new UnsupportedOperationException(
                "[ERROR] wrong type file"
                );
        };
    }

    private static Map<String, Object> readJson(Path filePath)
        throws IOException {

        return MAPPER.readValue(
            filePath.toFile(),
            new TypeReference<Map<String, Object>>() { }
        );
    }

    private static Map<String, Object> readYaml(Path filePath)
        throws IOException {

        return MAPPER_YAML.readValue(
            filePath.toFile(),
            new TypeReference<Map<String, Object>>() { }
        );
    }

    private static Path getPath(Path filePath) {
        Path resultPath = FIXTURES.resolve(filePath);
        return resultPath.toAbsolutePath().normalize();
    }
}
