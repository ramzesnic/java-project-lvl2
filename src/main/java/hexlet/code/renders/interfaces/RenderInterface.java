package hexlet.code.renders.interfaces;

import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RenderInterface {
    String render(Map<String, Map<String, Object>> diff) throws JsonProcessingException;
}
