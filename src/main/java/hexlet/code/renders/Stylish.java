package hexlet.code.renders;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import hexlet.code.Utils;
import hexlet.code.renders.interfaces.RenderInterface;

public final class Stylish implements RenderInterface {
  @Override
  public String render(Map<String, Map<String, Object>> diff) {
    final List<String> result = new ArrayList<>();
    final String tab = " ";
    final int repeatTab = 3;
    final Set<String> keys = Utils.getDicKeys(diff);
    final Consumer<String> renderDiff = key -> {
      final Map<String, Object> node = diff.get(key);
      final String nodeType = (String) node.get("type");
      switch (nodeType) {
        case "added" :
          result.add(tab + "+ " + key + ": " + node.get("after"));
          break;
        case "deleted" :
          result.add(tab + "- " + key + ": " + node.get("before"));
          break;
        case "changed" :
          result.add(tab + "- " + key + ": " + node.get("before"));
          result.add(tab + "+ " + key + ": " + node.get("after"));
          break;
        case "unchanged" :
          result.add(tab.repeat(repeatTab) + key + ": " + node.get("before"));
          break;
        default :
          break;
      }
    };
    keys.stream().forEach(renderDiff);
    return result.stream().collect(
        Collectors.joining(System.lineSeparator(), "{" + System.lineSeparator(),
            System.lineSeparator() + "}"));
  }
}
