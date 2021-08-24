package hexlet.code.renders.interfaces;

import java.util.Map;

public interface RenderInterface {
  String render(Map<String, Map<String, Object>> diff);
}
