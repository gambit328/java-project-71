package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true
)

public class App implements Runnable {

    @Override
    public void run() {
        System.out.println("Hello, World!");
    }

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
}
