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
            var oldVal = content1.get(key);
            var newVal = content2.get(key);
            if (!content2.containsKey(key)) {
                result.add(new FileData(key, Status.REMOVED,
                    oldVal, newVal));
            } else if (!content1.containsKey(key)) {
                result.add(new FileData(key, Status.ADDED,
                    oldVal, newVal));
            } else if (Objects.equals(oldVal, newVal)) {
                result.add(new FileData(key, Status.UNCHANGED,
                    oldVal, newVal));
            } else if (!Objects.equals(oldVal, newVal)) {
                result.add(new FileData(key, Status.CHANGED,
                    oldVal, newVal));
            }
        }

        return result;
    }
}
