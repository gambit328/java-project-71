package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

import hexlet.code.common.FileData;
import hexlet.code.common.Status;


public final class ComparatorFiles {

    private ComparatorFiles() { }

    public static List<FileData> generate(Map<String, Object> content1,
        Map<String, Object> content2) {
        var result = new ArrayList<FileData>();
        var keys = new TreeSet<String>();

        keys.addAll(content1.keySet());
        keys.addAll(content2.keySet());

        for (var key: keys) {
            var val1 = content1.get(key);
            var val2 = content2.get(key);
            if (!Objects.equals(val1, val2)) {
                if (Objects.isNull(val2)) {
                    result.add(new FileData(key, Status.REMOVED,
                        val1, val2));
                } else if (Objects.isNull(val1)) {
                    result.add(new FileData(key, Status.ADDED,
                        val1, val2));
                } else {
                    result.add(new FileData(key, Status.CHANGED,
                        val1, val2));
                }
            } else {
                result.add(new FileData(key, Status.UNCHANGED,
                    val1, val2));
            }
        }

        return result;
    }
}
