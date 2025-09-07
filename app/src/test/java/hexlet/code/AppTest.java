package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;

final class AppTest {

    private StringWriter out;

    @BeforeEach
    void beforeEach() {
        out = new StringWriter();
    }

    @Test
    void testCallsuccess() {
        var expected = 0;
        var actual = new CommandLine(new App())
                .setOut(new PrintWriter(out, true))
                .execute("file1.json", "file2.json");

        assertFalse(out.toString().isBlank());
        assertEquals(expected, actual);
    }

    @Test
    void testCallFail() {
        var expected = 1;
        var actual = new CommandLine(new App())
                .setOut(new PrintWriter(out, true))
                .execute("file3.json", "file2.json");

        assertTrue(out.toString().isBlank());
        assertEquals(expected, actual);
    }
}
