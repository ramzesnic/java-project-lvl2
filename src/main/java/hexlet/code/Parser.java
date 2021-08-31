package hexlet.code;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;

final class Parser {
    private static Parser instance;
    private ObjectMapper objectMapper;
    private String data;

    private Parser() {
    }

    public static Parser getParser(String fileData, String type) throws IOException {
        if (instance == null) {
            instance = new Parser();
        }
        instance.data = fileData;
        if (type.equals("json")) {
            instance.objectMapper = new ObjectMapper();
        } else if (type.equals("yml")) {
            instance.objectMapper = new ObjectMapper(new YAMLFactory());
        } else {
            throw new IOException("Incorrect file format");
        }
        return instance;
    }

    public Map<String, Object> parse() throws IOException {
        return this.objectMapper.readValue(this.data, new TypeReference<Map<String, Object>>() {
        });
    }
}
