package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.nio.file.Path;

@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true
)

public class App implements Runnable {

    @Option(
            names = { "-f", "--format" },
            paramLabel = "format",
            defaultValue = "stylish",
            description = "output format [default: stylish]"
    )
    private String file;

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
    public void run() {
        System.out.println("Hello, World!");

        try {
            var result = FileUtils.readJson(filepath1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);

    }
}
