package hexlet.code;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;

final class Parser {
    public static Map<String, Object> parse(String fileData, String type) throws IOException {
        ObjectMapper objectMapper;
        if (type.equals(Constants.Parsers.JSON)) {
            objectMapper = new ObjectMapper();
        } else if (type.equals(Constants.Parsers.YAML)) {
            objectMapper = new ObjectMapper(new YAMLFactory());
        } else {
            throw new IOException("Incorrect file format");
        }
        return objectMapper.readValue(fileData, new TypeReference<Map<String, Object>>() {
        });
    }
}
