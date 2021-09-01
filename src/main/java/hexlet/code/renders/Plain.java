package hexlet.code.renders;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import hexlet.code.renders.interfaces.RenderInterface;
import hexlet.code.Constants;

public final class Plain implements RenderInterface {
    @Override
    public String render(Map<String, Map<String, Object>> diff) {
        return diff.entrySet().stream()
                .map(this::renderDiff)
                .filter(node -> !node.isBlank())
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private Object getNodeValue(Map<String, Object> node, String key) {
        final Object value = node.get(key);
        final boolean isComplex = value instanceof ArrayList || value instanceof HashMap;
        if (isComplex) {
            return Constants.PlainMessages.COMPLEX;
        }
        if (value instanceof String) {
            return "\'" + value + "\'";
        }
        return value;
    }

    private String renderDiff(Map.Entry<String, Map<String, Object>> nodeDiff) {
        final Map<String, Object> node = nodeDiff.getValue();
        final String key = nodeDiff.getKey();
        final String nodeType = (String) node.get(Constants.NodeProps.TYPE);
        return switch (nodeType) {
            case Constants.NodeTypes.ADDED -> String.format(Constants.PlainMessages.ADDED,
                    key,
                    this.getNodeValue(node, Constants.NodeProps.AFTER));
            case Constants.NodeTypes.DELETED -> String.format(Constants.PlainMessages.DELETED, key);
            case Constants.NodeTypes.CHANGED -> String.format(Constants.PlainMessages.CHANGED,
                    key,
                    this.getNodeValue(node, Constants.NodeProps.BEFORE),
                    this.getNodeValue(node, Constants.NodeProps.AFTER));
            default -> "";
        };
    }
}
