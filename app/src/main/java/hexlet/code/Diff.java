package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


import hexlet.code.formatters.Formatter;

public final class Diff {

    private static final Path FIXTURES = Path.of(
        "src", "test", "resources", "fixtures", "input_files"
    );

    public Diff() { }

    public static String generate(Path filepath1, Path filepath2, String format)
        throws Exception {
        var fileContent1 = readFile(filepath1);
        var fileContent2 = readFile(filepath2);

        var fileFormat1 = getFormatFile(filepath1);
        var fileData1 = Parser.parseFile(fileContent1, fileFormat1);
        var fileFormat2 = getFormatFile(filepath2);
        var fileData2 = Parser.parseFile(fileContent2, fileFormat2);

        var diff = ComparatorFiles.generate(fileData1, fileData2);

        return Formatter.formatString(diff, format);
    }

    public static String generate(Path filepath1, Path filepath2)
        throws Exception {

        return generate(filepath1, filepath2, "stylish");
    }

    private static String readFile(Path filePath) throws IOException {
        var normalizedPath = filePath.isAbsolute()
                ? filePath.normalize() : getPath(filePath);

        return Files.readString(normalizedPath);
    }

    private static Path getPath(Path filePath) {
        Path resultPath = FIXTURES.resolve(filePath);
        return resultPath.toAbsolutePath().normalize();
    }

    private static String getFormatFile(Path filepath) throws Exception {
        var fileName = filepath.getFileName().toString();

        if (fileName.endsWith(".json")) {
            return "JSON";
        } else if (fileName.endsWith(".yaml")
            || fileName.endsWith(".yml")) {
            return "YAML";
        }

        throw new IllegalArgumentException(
            "[ERROR] Unsupported file extension"
        );
    }
}
