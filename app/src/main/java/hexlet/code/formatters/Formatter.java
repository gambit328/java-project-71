package hexlet.code.formatters;

import java.util.List;
import hexlet.code.common.FileData;

public final class Formatter {

    private Formatter() { }

    public static String formatString(List<FileData> diff, String formatteString)
        throws Exception {
        return switch (formatteString) {
            case "json" -> JsonFormatter.formatString(diff);
            default -> throw new UnsupportedOperationException(
                "Unknown format: " + formatteString
            );
        };
    }
}

