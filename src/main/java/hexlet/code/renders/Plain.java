package hexlet.code.renders;

import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import hexlet.code.renders.interfaces.RenderInterface;

public final class Plain implements RenderInterface {
    private Object getNodeValue(Map<String, Object> node, String key) {
        final Object value = node.get(key);
        final boolean isComplex = value instanceof ArrayList || value instanceof HashMap;
        if (isComplex) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "\'" + value + "\'";
        }
        return value;
    }

    @Override
    public String render(Map<String, Map<String, Object>> diff) {
        final Set<String> keys = diff.keySet();
        final Function<String, String> renderDiff = key -> {
            final Map<String, Object> node = diff.get(key);
            final String nodeType = (String) node.get("type");
            switch (nodeType) {
                case "added" :
                    return "Property \'" + key + "\' was added with value: " + this.getNodeValue(node, "after");
                case "deleted" :
                    return "Property \'" + key + "\' was removed";
                case "changed" :
                    return "Property \'" + key + "\' was updated. From " + this.getNodeValue(node, "before") + " to "
                            + this.getNodeValue(node, "after");
                default :
                    return "";
            }
        };
        return keys.stream()
                .map(renderDiff)
                .filter(node -> !node.isBlank())
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
