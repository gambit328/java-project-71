package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class DiffTest {

    private static String expectedJson;
    private static String expectedJson1;

    public static Path getAbsPath(String fileName) {
        return Paths.get("src/test/resources/fixtures", fileName)
            .toAbsolutePath().normalize();
    }

    public static String readFile(String fileName) throws IOException {
        Path filePath = getAbsPath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void setup() throws IOException {
        expectedJson = readFile("expected/file.json");
        expectedJson1 = readFile("expected/file1.json");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void testGenerateDefaultOutput(String inputFormat) throws Exception {
        var file1 = getAbsPath("input_files/file1." + inputFormat);
        var file2 = getAbsPath("input_files/file2." + inputFormat);

        String actual = Diff.generate(file1, file2);
        assertEquals(expectedJson, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void testGenerateDefaultOutput1(String inputFormat) throws Exception {
        var file1 = getAbsPath("input_files/file3." + inputFormat);
        var file2 = getAbsPath("input_files/file4." + inputFormat);

        String actual = Diff.generate(file1, file2);
        assertEquals(expectedJson1, actual);
    }
}
