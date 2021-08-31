package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedHashMap;

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

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        final String file1 = Files.readString(Paths.get(filePath1));
        final String file2 = Files.readString(Paths.get(filePath2));
        final String file1Ext = Utils.getFileExt(filePath1);
        final String file2Ext = Utils.getFileExt(filePath1);

        final Map<String, Object> fileData1 = Parser.getParser(file1, file1Ext).parse();
        final Map<String, Object> fileData2 = Parser.getParser(file2, file2Ext).parse();

        final Set<String> keys = new TreeSet<>(fileData1.keySet());
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
