package hexlet.code;

import hexlet.code.renders.interfaces.RenderInterface;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import hexlet.code.renders.Stylish;
import hexlet.code.renders.Plain;
import hexlet.code.renders.Json;

class Render {
    public static RenderInterface getRender(String format) throws IOException, JsonProcessingException {
        return switch (format) {
            case Constants.Formats.STYLISH -> new Stylish();
            case Constants.Formats.PLAIN -> new Plain();
            case Constants.Formats.JSON -> new Json();

            default -> throw new IOException("Format not support.");
        };
    }
}
