package hexlet.code;

public final class Constants {
    public final class PlainMessages {
        public static final String ADDED = "Property \'%s\' was added with value: %s";
        public static final String DELETED = "Property \'%s\' was removed";
        public static final String CHANGED = "Property \'%s\' was updated. From %s to %s";
        public static final String COMPLEX = "[complex value]";
    }

    public final class NodeTypes {
        public static final String ADDED = "added";
        public static final String DELETED = "deleted";
        public static final String CHANGED = "changed";
        public static final String UNCHANGED = "unchanged";
    }

    public final class NodeProps {
        public static final String TYPE = "type";
        public static final String BEFORE = "before";
        public static final String AFTER = "after";
    }

    public final class Formats {
        public static final String STYLISH = "stylish";
        public static final String PLAIN = "plain";
        public static final String JSON = "json";
    }

    public final class Parsers {
        public static final String YAML = "yml";
        public static final String JSON = "json";
    }
}
