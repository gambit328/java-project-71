package hexlet.code.formatters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import hexlet.code.common.FileData;
import hexlet.code.common.Status;

public final class PlainFormatter {

    private PlainFormatter() { }

    public static String formatString(List<FileData> diff)
        throws Exception {
        StringBuilder result = new StringBuilder();

        for (var elem: diff) {
            if (elem.status() == Status.UNCHANGED) {
                continue;
            }

            var key = elem.key();
            var oldValue = normalizeValue(elem.oldVal());
            var newValue = normalizeValue(elem.newVal());
            var part2 = switch (elem.status()) {
                case REMOVED -> "was removed";
                case ADDED -> String.format(
                    "was added with value: %s", newValue
                );
                case CHANGED -> String.format(
                    "was updated. From %s to %s", oldValue, newValue
                );
                default -> throw new UnsupportedOperationException(
                    "[ERROR] wrong status " + elem.status()
                );
            };

            result.append(String.format("Property '%s' ", key))
                .append(part2).append("\n");
        }

        return result.toString().trim();
    }

    private static String normalizeValue(Object val) {
        return switch (val) {
            case null -> "null";
            case String str -> String.format("'%s'", val);
            case Map<?, ?> m -> "[complex value]";
            case Collection<?> collections -> "[complex value]";
            default -> {
                if (val.getClass().isArray()) {
                    yield "[complex value]";
                }
                yield val.toString();
            }
        };
    }

}
