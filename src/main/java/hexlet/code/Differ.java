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
        diff.put(Constants.NodeProps.TYPE, type);
        diff.put(Constants.NodeProps.BEFORE, before);
        diff.put(Constants.NodeProps.AFTER, after);
        return diff;
    }

    private static Map<String, Map<String, Object>> makeDiff(String key,
            Map<String, Object> dicOne, Map<String, Object> dicTwo) {
        final Map<String, Map<String, Object>> diff = new LinkedHashMap<>();
        if (!dicOne.containsKey(key)) {
            final Map<String, Object> nodeDiff = makeNodeDiff(Constants.NodeTypes.ADDED, null, dicTwo.get(key));
            diff.put(key, nodeDiff);
        } else if (!dicTwo.containsKey(key)) {
            final Map<String, Object> nodeDiff = makeNodeDiff(Constants.NodeTypes.DELETED, dicOne.get(key), null);
            diff.put(key, nodeDiff);
        } else if (!Objects.equals(dicOne.get(key), dicTwo.get(key))) {
            final Map<String, Object> nodeDiff = makeNodeDiff(Constants.NodeTypes.CHANGED,
                    dicOne.get(key),
                    dicTwo.get(key));
            diff.put(key, nodeDiff);
        } else {
            final Map<String, Object> nodeDiff = makeNodeDiff(Constants.NodeTypes.UNCHANGED, dicOne.get(key), null);
            diff.put(key, nodeDiff);
        }
        return diff;
    }

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        final String file1 = Files.readString(Paths.get(filePath1));
        final String file2 = Files.readString(Paths.get(filePath2));
        final String file1Ext = Utils.getFileExt(filePath1);
        final String file2Ext = Utils.getFileExt(filePath1);

        final Map<String, Object> fileData1 = Parser.parse(file1, file1Ext);
        final Map<String, Object> fileData2 = Parser.parse(file2, file2Ext);

        final Set<String> keys = new TreeSet<>(fileData1.keySet());
        keys.addAll(fileData2.keySet());
        final Map<String, Map<String, Object>> diff = keys.stream()
                .map(key -> makeDiff(key, fileData1, fileData2))
                .flatMap(node -> node.entrySet().stream())
                .collect(LinkedHashMap::new, (map, node) -> map.put(node.getKey(), node.getValue()), Map::putAll);
        return Render.getRender(format).render(diff);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        final String defaultFormat = Constants.Formats.STYLISH;
        return generate(filePath1, filePath2, defaultFormat);
    }
}
