package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class DifferTest {
  @Test
  void testDiffSimpleJson() throws IOException {
    final String filePath1 = "src/test/resources/fixtures/file1-simple.json";
    final String filePath2 = "src/test/resources/fixtures/file2-simple.json";
    final String expectedFilepath = "src/test/resources/expected/stylish-simple.txt";
    final Path expectedPath = Paths.get(expectedFilepath);
    final String expected = Files.readString(expectedPath);
    final String actual = Differ.generate(filePath1, filePath2, "stylish");
    assertEquals(expected, actual + System.lineSeparator());
  }

  @Test
  void testDiffSimpleYaml() throws IOException {
    final String filePath1 = "src/test/resources/fixtures/file1-simple.yaml";
    final String filePath2 = "src/test/resources/fixtures/file2-simple.yaml";
    final String expectedFilepath = "src/test/resources/expected/stylish-simple.txt";
    final Path expectedPath = Paths.get(expectedFilepath);
    final String expected = Files.readString(expectedPath);
    final String actual = Differ.generate(filePath1, filePath2, "stylish");
    assertEquals(expected, actual + System.lineSeparator());
  }

  @Test
  void testDiffNestedJson() throws IOException {
    final String filePath1 = "src/test/resources/fixtures/file1-nested.json";
    final String filePath2 = "src/test/resources/fixtures/file2-nested.json";
    final String expectedFilepath = "src/test/resources/expected/stylish-nested.txt";
    final Path expectedPath = Paths.get(expectedFilepath);
    final String expected = Files.readString(expectedPath);
    final String actual = Differ.generate(filePath1, filePath2, "stylish");
    assertEquals(expected, actual + System.lineSeparator());
  }

  @Test
  void testDiffNestedYaml() throws IOException {
    final String filePath1 = "src/test/resources/fixtures/file1-nested.yaml";
    final String filePath2 = "src/test/resources/fixtures/file2-nested.yaml";
    final String expectedFilepath = "src/test/resources/expected/stylish-nested.txt";
    final Path expectedPath = Paths.get(expectedFilepath);
    final String expected = Files.readString(expectedPath);
    final String actual = Differ.generate(filePath1, filePath2, "stylish");
    assertEquals(expected, actual + System.lineSeparator());
  }

  @Test
  void testDiffSimpleJsonToPlain() throws IOException {
    final String filePath1 = "src/test/resources/fixtures/file1-simple.json";
    final String filePath2 = "src/test/resources/fixtures/file2-simple.json";
    final String expectedFilepath = "src/test/resources/expected/plain-simple.txt";
    final Path expectedPath = Paths.get(expectedFilepath);
    final String expected = Files.readString(expectedPath);
    final String actual = Differ.generate(filePath1, filePath2, "plain");
    assertEquals(expected, actual + System.lineSeparator());
  }

  @Test
  void testDiffSimpleYamlToPlain() throws IOException {
    final String filePath1 = "src/test/resources/fixtures/file1-simple.yaml";
    final String filePath2 = "src/test/resources/fixtures/file2-simple.yaml";
    final String expectedFilepath = "src/test/resources/expected/plain-simple.txt";
    final Path expectedPath = Paths.get(expectedFilepath);
    final String expected = Files.readString(expectedPath);
    final String actual = Differ.generate(filePath1, filePath2, "plain");
    assertEquals(expected, actual + System.lineSeparator());
  }

  @Test
  void testDiffNestedJsonToPlain() throws IOException {
    final String filePath1 = "src/test/resources/fixtures/file1-nested.json";
    final String filePath2 = "src/test/resources/fixtures/file2-nested.json";
    final String expectedFilepath = "src/test/resources/expected/plain-nested.txt";
    final Path expectedPath = Paths.get(expectedFilepath);
    final String expected = Files.readString(expectedPath);
    final String actual = Differ.generate(filePath1, filePath2, "plain");
    assertEquals(expected, actual + System.lineSeparator());
  }

  @Test
  void testDiffNestedYamlToPlain() throws IOException {
    final String filePath1 = "src/test/resources/fixtures/file1-nested.yaml";
    final String filePath2 = "src/test/resources/fixtures/file2-nested.yaml";
    final String expectedFilepath = "src/test/resources/expected/plain-nested.txt";
    final Path expectedPath = Paths.get(expectedFilepath);
    final String expected = Files.readString(expectedPath);
    final String actual = Differ.generate(filePath1, filePath2, "plain");
    assertEquals(expected, actual + System.lineSeparator());
  }
}
