package hexlet.code;

import java.nio.file.Path;
import hexlet.code.formatters.Formatter;

public final class Diff {

    private Diff() { }

    public static String generate(Path filepath1, Path filepath2) throws Exception {
        var filecontent1 = Parser.readFile(filepath1);
        var filecontent2 = Parser.readFile(filepath2);

        var diff = ComparatorFiles.generate(filecontent1, filecontent2);

        return Formatter.formatString(diff, "json");
    }
}
