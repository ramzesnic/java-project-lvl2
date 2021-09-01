package hexlet.code.renders;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import hexlet.code.renders.interfaces.RenderInterface;
import hexlet.code.Constants;

public final class Stylish implements RenderInterface {
    private final String tab = " ";
    private final int unchangerTabSize = 4;
    private final int defaultTabSize = 2;
    private final String unchangerTab = tab.repeat(unchangerTabSize);
    private final String defaultTab = tab.repeat(defaultTabSize);

    @Override
    public String render(Map<String, Map<String, Object>> diff) {
        return diff.entrySet().stream()
                .map(this::renderDiff)
                .flatMap(List::stream)
                .collect(Collectors.joining(System.lineSeparator(),
                        "{" + System.lineSeparator(),
                        System.lineSeparator() + "}"));
    }

    private List<String> renderDiff(Map.Entry<String, Map<String, Object>> nodeDiff) {
        final Map<String, Object> node = nodeDiff.getValue();
        final String key = nodeDiff.getKey();
        final String nodeType = (String) node.get(Constants.NodeProps.TYPE);
        return switch (nodeType) {
            case Constants.NodeTypes.ADDED -> Arrays.asList(defaultTab
                    + "+ " + key + ": "
                    + node.get(Constants.NodeProps.AFTER));
            case Constants.NodeTypes.DELETED -> Arrays.asList(defaultTab
                    + "- " + key + ": "
                    + node.get(Constants.NodeProps.BEFORE));
            case Constants.NodeTypes.CHANGED -> Arrays.asList(
                    defaultTab + "- " + key + ": " + node.get(Constants.NodeProps.BEFORE),
                    defaultTab + "+ " + key + ": " + node.get(Constants.NodeProps.AFTER));
            case Constants.NodeTypes.UNCHANGED -> Arrays.asList(unchangerTab
                    + key + ": "
                    + node.get(Constants.NodeProps.BEFORE));
            default -> new ArrayList<String>();
        };
    }
}
