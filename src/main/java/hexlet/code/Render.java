package hexlet.code;

import hexlet.code.renders.interfaces.RenderInterface;
import java.io.IOException;
import hexlet.code.renders.Stylish;
import hexlet.code.renders.Plain;

class Render {
  public static RenderInterface getRender(String format) throws IOException {
    switch (format) {
      case "stylish" :
        return new Stylish();
      case "plain" :
        return new Plain();

      default :
        throw new IOException("Format not support.");
    }
  }
}
