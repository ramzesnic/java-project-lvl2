package hexlet.code;

import hexlet.code.renders.interfaces.RenderInterface;
import java.io.IOException;
import hexlet.code.renders.Stylish;

class Render {
  public static RenderInterface getRender(String format) throws IOException {
    switch (format) {
      case "stylish" :
        return new Stylish();

      default :
        throw new IOException("Format not support.");
    }
  }
}
