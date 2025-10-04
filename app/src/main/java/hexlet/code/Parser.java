package hexlet.code;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;


public final class Parser {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new YAMLMapper();

    private Parser() { }

    public static Map<String, Object> parseFile(String content,
        String fileFormat) throws Exception {

        return switch (fileFormat) {
            case "JSON" -> JSON_MAPPER.readValue(content,
                new TypeReference<Map<String, Object>>() { });
            case "YAML" -> YAML_MAPPER.readValue(content,
                new TypeReference<Map<String, Object>>() { });
            default -> throw new IllegalArgumentException(
                "[ERROR] Unknown format: " + fileFormat
            );
        };
    }
}
