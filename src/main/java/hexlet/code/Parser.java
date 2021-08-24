package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;

final class Parser {
  private static Parser instance;
  private ObjectMapper objectMapper;
  private Path filePath;

  private Parser(String fPath) {
  }

  public static Parser getParser(String fPath) throws IOException {
    if (instance == null) {
      instance = new Parser(fPath);
    }
    instance.filePath = Paths.get(fPath);
    if (fPath.endsWith("json")) {
      instance.objectMapper = new ObjectMapper();
    } else if (fPath.endsWith("yaml")) {
      instance.objectMapper = new ObjectMapper(new YAMLFactory());
    } else {
      throw new IOException("Incorrect file format");
    }
    return instance;
  }

  public Map<String, Object> parse() throws IOException {
    return this.objectMapper.readValue(this.filePath.toFile(), new TypeReference<Map<String, Object>>() {
    });
  }
}
