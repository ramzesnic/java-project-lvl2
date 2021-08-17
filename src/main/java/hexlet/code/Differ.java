package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

class Differ {
  private static <T> Set<String> getDicKeys(Map<String, T> dic) {
    return dic.entrySet().stream().map(en -> en.getKey()).collect(Collectors.toCollection(TreeSet::new));
  }

  public static Map<String, Object> parser(String filePath) throws IOException, JsonParseException {
    final Path file1Path = Paths.get(filePath);
    final ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(file1Path.toFile(), new TypeReference<Map<String, Object>>() {
    });
  }

  private static Map<String, Object> makeNodeDiff(String type, Object before, Object after) {
    final Map<String, Object> diff = new HashMap<>();
    diff.put("type", type);
    diff.put("before", before);
    diff.put("after", after);
    return diff;
  }

  private static void addToDiff(String key, Map<String, Object> dicOne, Map<String, Object> dicTwo,
      Map<String, Map<String, Object>> diff) {
    if (!dicOne.containsKey(key) && dicTwo.containsKey(key)) {
      diff.put(key, makeNodeDiff("added", null, dicTwo.get(key)));
    } else if (dicOne.containsKey(key) && !dicTwo.containsKey(key)) {
      diff.put(key, makeNodeDiff("deleted", dicOne.get(key), null));
    } else if (!dicOne.get(key).equals(dicTwo.get(key))) {
      diff.put(key, makeNodeDiff("changed", dicOne.get(key), dicTwo.get(key)));
    } else {
      diff.put(key, makeNodeDiff("unchanged", dicOne.get(key), null));
    }
  }

  private static String render(Map<String, Map<String, Object>> diff) {
    final List<String> result = new ArrayList<>();
    final String tab = " ";
    final int repeatTab = 3;
    final Set<String> keys = getDicKeys(diff);
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
    result.add("{");
    keys.stream().forEach(renderDiff);
    result.add("}");
    return result.stream().collect(Collectors.joining("\n"));
  }

  public static String generate(String filePath1, String filePath2) throws IOException, JsonParseException {
    final Map<String, Object> fileData1 = Differ.parser(filePath1);
    final Map<String, Object> fileData2 = Differ.parser(filePath2);

    final Set<String> keys = getDicKeys(fileData1);
    keys.addAll(getDicKeys(fileData2));
    final Map<String, Map<String, Object>> diff = new LinkedHashMap<>();
    keys.stream().forEach(key -> addToDiff(key, fileData1, fileData2, diff));
    return render(diff);
  }
}
