package hexlet.code;

import hexlet.code.renders.interfaces.RenderInterface;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import hexlet.code.renders.Stylish;
import hexlet.code.renders.Plain;
import hexlet.code.renders.Json;

class Render {
  public static RenderInterface getRender(String format) throws IOException, JsonProcessingException {
    switch (format) {
      case "stylish" :
        return new Stylish();
      case "plain" :
        return new Plain();
      case "json" :
        return new Json();

      default :
        throw new IOException("Format not support.");
    }
  }
}
