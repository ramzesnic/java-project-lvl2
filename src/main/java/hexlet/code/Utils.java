package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Utils {
  public static <T> Set<String> getDicKeys(Map<String, T> dic) {
    return dic.entrySet().stream().map(en -> en.getKey()).collect(Collectors.toCollection(TreeSet::new));
  }
}
