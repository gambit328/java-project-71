package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true
)

public class App implements Callable<Integer> {

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
    public Integer call() {
        var result = "";
        try {
            var resultPath1 = FileUtils.readJson(filepath1);
            var resultPath2 = FileUtils.readJson(filepath2);
            result += Diff.generate(resultPath1, resultPath2);

            System.out.println(result);
            return 0;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    public static void main(String[] args) {
        int code = new CommandLine(new App()).execute(args);
        System.exit(code);
    }
}
