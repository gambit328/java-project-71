package hexlet.code.formatters;

import java.util.List;

import hexlet.code.common.DiffRow;


public final class Formatter {

    private Formatter() { }

    public static String formatString(List<DiffRow> diff, String formatteString)
        throws Exception {
        return switch (formatteString) {
            case "stylish" -> StylishFormatter.formatString(diff);
            case "plain" -> PlainFormatter.formatString(diff);
            case "json" -> JsonFormatter.formatString(diff);
            default -> throw new UnsupportedOperationException(
                "Unknown format: " + formatteString
            );
        };
    }
}
