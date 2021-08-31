package hexlet.code.renders;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

import hexlet.code.renders.interfaces.RenderInterface;

public final class Stylish implements RenderInterface {
    private final String tab = " ";
    private final int unchangerTabSize = 4;
    private final int defaultTabSize = 2;
    private final String unchangerTab = tab.repeat(unchangerTabSize);
    private final String defaultTab = tab.repeat(defaultTabSize);

    @Override
    public String render(Map<String, Map<String, Object>> diff) {
        final Set<String> keys = diff.keySet();
        final Function<String, List<String>> renderDiff = key -> {
            final Map<String, Object> node = diff.get(key);
            final String nodeType = (String) node.get("type");
            final List<String> result = new ArrayList<>();
            switch (nodeType) {
                case "added" :
                    result.add(defaultTab + "+ " + key + ": " + node.get("after"));
                    break;
                case "deleted" :
                    result.add(defaultTab + "- " + key + ": " + node.get("before"));
                    break;
                case "changed" :
                    result.add(defaultTab + "- " + key + ": " + node.get("before"));
                    result.add(defaultTab + "+ " + key + ": " + node.get("after"));
                    break;
                case "unchanged" :
                    result.add(unchangerTab + key + ": " + node.get("before"));
                    break;
                default :
                    break;
            }
            return result;
        };

        return keys.stream()
                .map(renderDiff)
                .flatMap(List::stream)
                .collect(Collectors.joining(System.lineSeparator(),
                        "{" + System.lineSeparator(),
                        System.lineSeparator() + "}"));
    }
}
