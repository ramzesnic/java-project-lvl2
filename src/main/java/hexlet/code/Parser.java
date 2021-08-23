package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;

class Parser {
  private ObjectMapper objectMapper;
  private Path filePath;

  Parser(String fPath) throws IOException {
    this.filePath = Paths.get(fPath);
    if (fPath.endsWith("json")) {
      this.objectMapper = new ObjectMapper();
    } else if (fPath.endsWith("yaml")) {
      this.objectMapper = new ObjectMapper(new YAMLFactory());
    } else {
      throw new IOException("Incorrect file format");
    }
  }

  public Map<String, Object> parse() throws IOException {
    return this.objectMapper.readValue(this.filePath.toFile(), new TypeReference<Map<String, Object>>() {
    });
  }
}
