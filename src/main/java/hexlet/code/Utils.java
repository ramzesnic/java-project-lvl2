package hexlet.code;

import java.util.Optional;

public class Utils {
    public static String getFileExt(String path) {
        return Optional.ofNullable(path)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(path.lastIndexOf(".") + 1))
                .get();
    }
}
