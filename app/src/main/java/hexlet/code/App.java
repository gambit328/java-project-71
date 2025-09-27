package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        description = "Compares two configuration "
            + "files and shows a difference.",
        mixinStandardHelpOptions = true
)

public final class App implements Callable<Integer> {

    @Spec
    private CommandSpec spec;

    @Option(
            names = { "-f", "--format" },
            paramLabel = "format",
            defaultValue = "stylish",
            description = "output format [default: stylish]"
    )
    private String format;

    @Parameters(
            index = "0",
            paramLabel = "filepath1",
            description = "path to first file"
    )
    private Path filepath1;

    @Parameters(
            index = "1",
            paramLabel = "filepath2",
            description = "path to second file"
    )
    private Path filepath2;

    @Override
    public Integer call() throws Exception {
        var result = Diff.generate(filepath1, filepath2, format);
        System.out.println(result);
        return 0;
    }

    public static void main(final String[] args) {
        int code = new CommandLine(new App()).execute(args);
        System.exit(code);
    }
}
