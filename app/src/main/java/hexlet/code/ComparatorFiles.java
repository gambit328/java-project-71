package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

import hexlet.code.common.DiffRow;
import hexlet.code.common.Added;
import hexlet.code.common.Changed;
import hexlet.code.common.Removed;
import hexlet.code.common.Unchanged;
import hexlet.code.common.DiffRow.Status;


public final class ComparatorFiles {

    private ComparatorFiles() { }

    public static List<DiffRow> generate(Map<String, Object> content1,
        Map<String, Object> content2) {
        var result = new ArrayList<DiffRow>();
        var keys = new TreeSet<String>();

        keys.addAll(content1.keySet());
        keys.addAll(content2.keySet());

        for (var key: keys) {
            var oldVal = content1.get(key);
            var newVal = content2.get(key);
            if (!content2.containsKey(key)) {
                result.add(new Removed(key, Status.REMOVED, oldVal));
            } else if (!content1.containsKey(key)) {
                result.add(new Added(key, Status.ADDED, newVal));
            } else if (Objects.equals(oldVal, newVal)) {
                result.add(new Unchanged(key, Status.UNCHANGED, oldVal));
            } else if (!Objects.equals(oldVal, newVal)) {
                result.add(new Changed(key, Status.CHANGED, oldVal, newVal));
            }
        }

        return result;
    }
}
