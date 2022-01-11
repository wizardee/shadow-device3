package net.herit.ami.commons.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonUtil {
	
	private ObjectMapper objectMapper;
	
	public JsonUtil() {
		objectMapper = new ObjectMapper();
	}

    public String objectToJson(Object object){
        String requestBody = null;
        try {
			requestBody = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
        }
        return requestBody;
    }
    
    public String stringToPrettyJson(String message) throws JsonProcessingException {
		Object jsonObject = objectMapper.readValue(message, Object.class);
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
    }
	
}
