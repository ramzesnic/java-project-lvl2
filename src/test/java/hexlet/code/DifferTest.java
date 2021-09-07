package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class DifferTest {
    private String fixturePath = "src/test/resources/";

    private String makeResourcePath(String fileName, String subdir) {
        return this.fixturePath + subdir + "/" + fileName;
    }

    private String readFile(String fileName) throws IOException {
        final String expectedFilepath = this.makeResourcePath(fileName, "expected");
        final Path expectedPath = Paths.get(expectedFilepath);
        return Files.readString(expectedPath);
    }

    @Test
    void testDiffSimpleJsonToStylish() throws IOException {
        final String filePath1 = this.makeResourcePath("file1-simple.json", "fixtures");
        final String filePath2 = this.makeResourcePath("file2-simple.json", "fixtures");
        final String expected = this.readFile("stylish-simple.txt");
        final String actual = Differ.generate(filePath1, filePath2, "stylish");
        assertEquals(expected, actual + System.lineSeparator());
    }

    @Test
    void testDiffSimpleJsonToStylishDefault() throws IOException {
        final String filePath1 = this.makeResourcePath("file1-simple.json", "fixtures");
        final String filePath2 = this.makeResourcePath("file2-simple.json", "fixtures");
        final String expected = this.readFile("stylish-simple.txt");
        final String actual = Differ.generate(filePath1, filePath2);
        assertEquals(expected, actual + System.lineSeparator());
    }

    @Test
    void testDiffSimpleYamlToStylish() throws IOException {
        final String filePath1 = this.makeResourcePath("file1-simple.yml", "fixtures");
        final String filePath2 = this.makeResourcePath("file2-simple.yml", "fixtures");
        final String expected = this.readFile("stylish-simple.txt");
        final String actual = Differ.generate(filePath1, filePath2, "stylish");
        assertEquals(expected, actual + System.lineSeparator());
    }

    @Test
    void testDiffNestedJsonToStylish() throws IOException {
        final String filePath1 = this.makeResourcePath("file1-nested.json", "fixtures");
        final String filePath2 = this.makeResourcePath("file2-nested.json", "fixtures");
        final String expected = this.readFile("stylish-nested.txt");
        final String actual = Differ.generate(filePath1, filePath2, "stylish");
        assertEquals(expected, actual + System.lineSeparator());
    }

    @Test
    void testDiffNestedYamlToStylish() throws IOException {
        final String filePath1 = this.makeResourcePath("file1-nested.yml", "fixtures");
        final String filePath2 = this.makeResourcePath("file2-nested.yml", "fixtures");
        final String expected = this.readFile("stylish-nested.txt");
        final String actual = Differ.generate(filePath1, filePath2, "stylish");
        assertEquals(expected, actual + System.lineSeparator());
    }

    @Test
    void testDiffSimpleJsonToPlain() throws IOException {
        final String filePath1 = this.makeResourcePath("file1-simple.json", "fixtures");
        final String filePath2 = this.makeResourcePath("file2-simple.json", "fixtures");
        final String expected = this.readFile("plain-simple.txt");
        final String actual = Differ.generate(filePath1, filePath2, "plain");
        assertEquals(expected, actual + System.lineSeparator());
    }

    @Test
    void testDiffSimpleYamlToPlain() throws IOException {
        final String filePath1 = this.makeResourcePath("file1-simple.yml", "fixtures");
        final String filePath2 = this.makeResourcePath("file2-simple.yml", "fixtures");
        final String expected = this.readFile("plain-simple.txt");
        final String actual = Differ.generate(filePath1, filePath2, "plain");
        assertEquals(expected, actual + System.lineSeparator());
    }

    @Test
    void testDiffNestedJsonToPlain() throws IOException {
        final String filePath1 = this.makeResourcePath("file1-nested.json", "fixtures");
        final String filePath2 = this.makeResourcePath("file2-nested.json", "fixtures");
        final String expected = this.readFile("plain-nested.txt");
        final String actual = Differ.generate(filePath1, filePath2, "plain");
        assertEquals(expected, actual + System.lineSeparator());
    }

    @Test
    void testDiffNestedYamlToPlain() throws IOException {
        final String filePath1 = this.makeResourcePath("file1-nested.yml", "fixtures");
        final String filePath2 = this.makeResourcePath("file2-nested.yml", "fixtures");
        final String expected = this.readFile("plain-nested.txt");
        final String actual = Differ.generate(filePath1, filePath2, "plain");
        assertEquals(expected, actual + System.lineSeparator());
    }

    @Test
    void testDiffSimpleJsonToJson() throws IOException {
        final String filePath1 = this.makeResourcePath("file1-simple.json", "fixtures");
        final String filePath2 = this.makeResourcePath("file2-simple.json", "fixtures");
        final String expected = this.readFile("json-simple.txt");
        final String actual = Differ.generate(filePath1, filePath2, "json");
        assertEquals(expected, actual + System.lineSeparator());
    }

    @Test
    void testDiffSimpleYamlToJson() throws IOException {
        final String filePath1 = this.makeResourcePath("file1-simple.yml", "fixtures");
        final String filePath2 = this.makeResourcePath("file2-simple.yml", "fixtures");
        final String expected = this.readFile("json-simple.txt");
        final String actual = Differ.generate(filePath1, filePath2, "json");
        assertEquals(expected, actual + System.lineSeparator());
    }

    @Test
    void testDiffNestedJsonToJson() throws IOException {
        final String filePath1 = this.makeResourcePath("file1-nested.json", "fixtures");
        final String filePath2 = this.makeResourcePath("file2-nested.json", "fixtures");
        final String expected = this.readFile("json-nested.txt");
        final String actual = Differ.generate(filePath1, filePath2, "json");
        assertEquals(expected, actual + System.lineSeparator());
    }

    @Test
    void testDiffNestedYamlToJson() throws IOException {
        final String filePath1 = this.makeResourcePath("file1-nested.yml", "fixtures");
        final String filePath2 = this.makeResourcePath("file2-nested.yml", "fixtures");
        final String expected = this.readFile("json-nested.txt");
        final String actual = Differ.generate(filePath1, filePath2, "json");
        assertEquals(expected, actual + System.lineSeparator());
    }
}
