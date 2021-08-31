package hexlet.code;

import java.io.IOException;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Parameters(index = "0", paramLabel = "filePath1", description = "path to first file")
    private String filePath1;
    @Parameters(index = "1", paramLabel = "filePath2", description = "path to second file")
    private String filePath2;
    @Option(names = {"-f", "--format"},
            paramLabel = "format",
            description = "output format [default: ${DEFAULT-VALUE}]",
            defaultValue = "stylish")
    private String format;

    @Override
    public final Integer call() {
        try {
            final String diff = Differ.generate(filePath1, filePath2, format);
            System.out.println(diff);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
