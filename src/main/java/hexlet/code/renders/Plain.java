package hexlet.code.renders;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import hexlet.code.Utils;
import hexlet.code.renders.interfaces.RenderInterface;

public final class Plain implements RenderInterface {
  private Object getNodeValue(Map<String, Object> node, String key) {
    final Object value = node.get(key);
    final boolean isComplex = value instanceof ArrayList || value instanceof HashMap;
    return isComplex ? "[complex value]" : value;
  }

  @Override
  public String render(Map<String, Map<String, Object>> diff) {
    final List<String> result = new ArrayList<>();
    final Set<String> keys = Utils.getDicKeys(diff);
    final Consumer<String> renderDiff = key -> {
      final Map<String, Object> node = diff.get(key);
      final String nodeType = (String) node.get("type");
      switch (nodeType) {
        case "added" :
          result.add("Property " + key + " was added with value " + this.getNodeValue(node, "after"));
          break;
        case "deleted" :
          result.add("Property " + key + " was removed");
          break;
        case "changed" :
          result.add("Property " + key + " was updated. From " + this.getNodeValue(node, "before") + " to "
              + this.getNodeValue(node, "after"));
          break;
        default :
          break;
      }
    };
    keys.stream().forEach(renderDiff);
    return result.stream().collect(
        Collectors.joining(System.lineSeparator()));
  }
}
