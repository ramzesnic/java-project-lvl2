package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.HashMap;
import java.util.Set;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.core.JsonParseException;

public class Differ {
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
      final Map<String, Object> nodeDiff = makeNodeDiff("added", null, dicTwo.get(key));
      diff.put(key, nodeDiff);
    } else if (dicOne.containsKey(key) && !dicTwo.containsKey(key)) {
      final Map<String, Object> nodeDiff = makeNodeDiff("deleted", dicOne.get(key), null);
      diff.put(key, nodeDiff);
    } else if (!Objects.equals(dicOne.get(key), dicTwo.get(key))) {
      final Map<String, Object> nodeDiff = makeNodeDiff("changed", dicOne.get(key), dicTwo.get(key));
      diff.put(key, nodeDiff);
    } else {
      final Map<String, Object> nodeDiff = makeNodeDiff("unchanged", dicOne.get(key), null);
      diff.put(key, nodeDiff);
    }
  }

  public static String generate(String filePath1, String filePath2, String format)
      throws IOException, JsonParseException {
    final Map<String, Object> fileData1 = Parser.getParser(filePath1).parse();
    final Map<String, Object> fileData2 = Parser.getParser(filePath2).parse();

    final Set<String> keys = Utils.getDicKeys(fileData1);
    keys.addAll(Utils.getDicKeys(fileData2));
    final Map<String, Map<String, Object>> diff = new LinkedHashMap<>();
    keys.stream().forEach(key -> addToDiff(key, fileData1, fileData2, diff));
    return Render.getRender(format).render(diff);
  }

  public static String generate(String filePath1, String filePath2) throws IOException {
    final String defaultFormat = "stylish";
    return generate(filePath1, filePath2, defaultFormat);
  }
}
