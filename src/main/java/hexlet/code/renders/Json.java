package hexlet.code.renders;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hexlet.code.renders.interfaces.RenderInterface;

public final class Json implements RenderInterface {
    @Override
    public String render(Map<String, Map<String, Object>> diff) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(diff);
    }
}
