package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Diff {

    public static String generate(Map<String, Object> content1,
                                  Map<String, Object> content2) {
        var keys = new TreeSet<String>();
        keys.addAll(content1.keySet());
        keys.addAll(content2.keySet());

        String result = keys.stream()
                .map(key -> {
                    var inContent1 = content1.containsKey(key);
                    var inContent2 = content2.containsKey(key);
                    var val1 = content1.get(key);
                    var val2 = content2.get(key);
                    var tmpResult = "";

                    if (inContent1 && inContent2) {
                        if (val1.equals(val2)) {
                            tmpResult += String.format("    %s: %s", key, val1);
                        } else {
                            tmpResult += String.format("  - %s: %s\n  + %s: %s", key, val1, key, val2);
                        }
                    } else {
                        if (inContent1 && !inContent2) {
                            tmpResult += String.format("  - %s: %s", key, val1);
                        } else if (!inContent1 && inContent2) {
                            tmpResult += String.format("  + %s: %s", key, val2);
                        }
                    }
                    return tmpResult;
                })
                .collect(Collectors.joining("\n", "{\n", "\n}"));

        return result;
    }
}
