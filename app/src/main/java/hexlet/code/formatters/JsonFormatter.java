package hexlet.code.formatters;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import hexlet.code.common.DiffRow;
import hexlet.code.common.Added;
import hexlet.code.common.Changed;
import hexlet.code.common.Removed;
import hexlet.code.common.Unchanged;


public final class JsonFormatter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonFormatter() { }

    public static String formatString(List<DiffRow> diff)
        throws Exception {

        ArrayNode arrNode = MAPPER.createArrayNode();

        for (var elem: diff) {
            ObjectNode item = MAPPER.createObjectNode();
            var key = elem.key();
            var statusElem = elem.status();

            item.put("FIELD", key);
            item.put("STATUS", statusElem.toString());

            switch (statusElem) {
                case REMOVED -> {
                    var removed = (Removed) elem;
                    item.set("OLD_VALUE", toJsonNode(removed.oldValue()));
                }
                case ADDED -> {
                    var added = (Added) elem;
                    item.set("NEW_VALUE", toJsonNode(added.newValue()));
                }
                case CHANGED -> {
                    var changed = (Changed) elem;
                    item.set("OLD_VALUE", toJsonNode(changed.oldValue()));
                    item.set("NEW_VALUE", toJsonNode(changed.newValue()));
                }
                case UNCHANGED -> {
                    var unchanged = (Unchanged) elem;
                    item.set("OLD_VALUE", toJsonNode(unchanged.oldValue()));
                }
                default -> throw new UnsupportedOperationException(
                    "[ERROR] wrong status " + elem.status()
                );
            }

            arrNode.add(item);
        }

        return arrNode.toString();
    }

    private static JsonNode toJsonNode(Object object) {
        return MAPPER.valueToTree(object);
    }
}

