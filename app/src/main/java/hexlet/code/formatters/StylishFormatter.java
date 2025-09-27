package hexlet.code.formatters;

import java.util.List;

import hexlet.code.common.FileData;

public final class StylishFormatter {

    private StylishFormatter() { }

    public static String formatString(List<FileData> diff)
        throws Exception {
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (var elem: diff) {
            switch (elem.status()) {
                case REMOVED -> result.append(String.format(
                    "  - %s: %s\n", elem.key(), elem.oldVal()
                ));
                case ADDED -> result.append(String.format(
                    "  + %s: %s\n", elem.key(), elem.newVal()
                ));
                case CHANGED -> result.append(String.format(
                    "  - %s: %s\n  + %s: %s\n",
                    elem.key(), elem.oldVal(), elem.key(), elem.newVal()
                ));
                case UNCHANGED -> result.append(String.format(
                    "    %s: %s\n", elem.key(), elem.oldVal()
                ));
                default -> throw new UnsupportedOperationException(
                    "[ERROR] wrong status " + elem.status()
                );
            }
        }

        result.append("}");

        return result.toString();
    }

}
