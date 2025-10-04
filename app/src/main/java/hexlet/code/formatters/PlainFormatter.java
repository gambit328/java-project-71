package hexlet.code.formatters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import hexlet.code.common.DiffRow;
import hexlet.code.common.Added;
import hexlet.code.common.Changed;


public final class PlainFormatter {

    private PlainFormatter() { }

    public static String formatString(List<DiffRow> diff)
        throws Exception {
        StringBuilder result = new StringBuilder();

        for (var elem: diff) {
            if (elem.status() == DiffRow.Status.UNCHANGED) {
                continue;
            }

            var key = elem.key();
            var part2 = switch (elem.status()) {
                case REMOVED -> {
                    yield "was removed";
                }
                case ADDED -> {
                    var added = (Added) elem;
                    yield String.format(
                        "was added with value: %s",
                        normalizeValue(added.newValue())
                    );
                }
                case CHANGED -> {
                    var changed = (Changed) elem;
                    yield String.format(
                        "was updated. From %s to %s",
                        normalizeValue(changed.oldValue()),
                        normalizeValue(changed.newValue())
                    );
                }
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
