package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class FileUtilsTest {

    private static Path file1;
    private static Path file2;
    private static Path file3;

    @BeforeAll
    static void beforeAll() {
        file1 = Path.of("file1.json");
        file2 = Path.of("src", "test", "resources", "fixtures", "file2.json").toAbsolutePath();
        file3 = Path.of("file3.json");
    }

    @Test
    public void testReadJson() throws IOException {
        var expected =
                Map.of(
                        "host",
                        "hexlet.io",
                        "timeout",
                        50,
                        "proxy",
                        "123.234.53.22",
                        "follow",
                        false);
        var actual = FileUtils.readJson(file1);
        assertEquals(expected, actual);
    }

    @Test
    public void testReadJsonabsolutePath() throws IOException {
        var expected = Map.of("timeout", 20, "verbose", true, "host", "hexlet.io");
        var actual = FileUtils.readJson(file2);
        assertEquals(expected, actual);
    }

    @Test
    public void testReadJsonfileNoExist() throws IOException {
        assertThrows(
                IOException.class,
                () -> {
                    FileUtils.readJson(file3);
                });
    }
}
