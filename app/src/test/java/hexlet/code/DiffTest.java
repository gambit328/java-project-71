package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

final class DiffTest {

    @ParameterizedTest(name = "{3}")
    @MethodSource("cases")
    void testGenerate(
            final String expected,
            final Map<String, Object> content1,
            final Map<String, Object> content2,
            final String testName) {
        var actual = Diff.generate(content1, content2);
        assertEquals(expected.stripTrailing(), actual.stripTrailing());
    }

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        """
                        {
                          - follow: false
                            host: hexlet.io
                          - proxy: 123.234.53.22
                          - timeout: 50
                          + timeout: 20
                          + verbose: true
                        }
                        """,
                        Map.of(
                                "follow", false,
                                "host", "hexlet.io",
                                "proxy", "123.234.53.22",
                                "timeout", 50),
                        Map.of("timeout", 20,
                                "verbose", true,
                                "host", "hexlet.io"),
                        "basic"),
                Arguments.of("{\n\n}", Map.of(), Map.of(), "empty"),
                Arguments.of(
                        """
                        {
                          - follow: false
                          - host: hexlet.io
                          - proxy: 123.234.53.22
                          - timeout: 50
                          - verbose: true
                        }
                        """,
                        Map.of(
                                "follow", false,
                                "host", "hexlet.io",
                                "proxy", "123.234.53.22",
                                "timeout", 50,
                                "verbose", true),
                        Map.of(),
                        "second map is empty"),
                Arguments.of(
                        """
                        {
                          + follow: false
                          + host: hexlet.io
                          + proxy: 123.234.53.22
                          + timeout: 50
                          + verbose: true
                        }
                        """,
                        Map.of(),
                        Map.of(
                                "follow", false,
                                "host", "hexlet.io",
                                "proxy", "123.234.53.22",
                                "timeout", 50,
                                "verbose", true),
                        "first map is empty"));
    }
}
