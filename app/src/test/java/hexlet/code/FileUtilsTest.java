package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

final class FileUtilsTest {

    private static Path fileJson1;
    private static Path fileJson2;
    private static Path fileJson3;

    private static Path fileYaml1;
    private static Path fileYaml2;
    private static Path fileYaml3;

    @BeforeAll
    static void beforeAll() {
        fileJson1 = Path.of("file1.json");
        fileJson2 = Path.of("src", "test", "resources",
                "fixtures", "file2.json").toAbsolutePath();
        fileJson3 = Path.of("file3.json");

        fileYaml1 = Path.of("file1.yaml");
        fileYaml2 = Path.of("src", "test", "resources",
                "fixtures", "file2.yaml").toAbsolutePath();
        fileYaml3 = Path.of("file3.yaml");
    }

    @Test
    void testReadJson() throws IOException {
        var expected = Map.of("host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false);
        var actual = FileUtils.readJson(fileJson1);
        assertEquals(expected, actual);
    }

    @Test
    void testReadJsonAbsolutePath() throws IOException {
        var expected = Map.of("timeout", 20,
                "verbose", true,
                "host", "hexlet.io");
        var actual = FileUtils.readJson(fileJson2);
        assertEquals(expected, actual);
    }

    @Test
    void testReadJsonFileNoExist() {
        assertThrows(
                IOException.class,
                () -> {
                    FileUtils.readJson(fileJson3);
                });
    }

    @Test
    void testReadYaml() throws IOException {
        var expected = Map.of("host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false);
        var actual = FileUtils.readYaml(fileYaml1);
        assertEquals(expected, actual);
    }

    @Test
    void testReadYamlAbsolutePath() throws IOException {
        var expected = Map.of("timeout", 20,
                "verbose", true,
                "host", "hexlet.io");
        var actual = FileUtils.readYaml(fileYaml2);
        assertEquals(expected, actual);
    }

    @Test
    void testReadYamlFileNoExist() {
        assertThrows(
                IOException.class,
                () -> {
                    FileUtils.readYaml(fileYaml3);
                });
    }
}
