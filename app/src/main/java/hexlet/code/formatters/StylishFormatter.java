package hexlet.code.formatters;

import java.util.List;

import hexlet.code.common.DiffRow;
import hexlet.code.common.Added;
import hexlet.code.common.Changed;
import hexlet.code.common.Removed;
import hexlet.code.common.Unchanged;


public final class StylishFormatter {

    private StylishFormatter() { }

    public static String formatString(List<DiffRow> diff)
        throws Exception {
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (var elem : diff) {
            switch (elem.status()) {
                case REMOVED -> {
                    var removed = (Removed) elem;
                    result.append(String.format(
                        "  - %s: %s\n",
                        elem.key(),
                        removed.oldValue()));
                }
                case ADDED -> {
                    var added = (Added) elem;
                    result.append(String.format(
                        "  + %s: %s\n",
                        elem.key(),
                        added.newValue()));
                }
                case CHANGED -> {
                    var changed = (Changed) elem;
                    result.append(String.format(
                        "  - %s: %s\n  + %s: %s\n",
                        elem.key(), changed.oldValue(),
                        elem.key(), changed.newValue()));
                }
                case UNCHANGED -> {
                    var unchanged = (Unchanged) elem;
                    result.append(String.format(
                        "    %s: %s\n",
                        elem.key(),
                        unchanged.oldValue()));
                }
                default -> throw new UnsupportedOperationException(
                        "[ERROR] wrong status " + elem.status());
            }
        }

        result.append("}");

        return result.toString();
    }

}
