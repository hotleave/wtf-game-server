package cc.cafebabe.testserver.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Util {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows(JsonProcessingException.class)
    public static String toJsonString(Object obj) {
        return objectMapper.writeValueAsString(obj);
    }

    @SneakyThrows(JsonProcessingException.class)
    public static JsonNode parseJson(String json) {
        return objectMapper.readTree(json);
    }


    public static String encode(String code) {
        String asB64 = "";
        try {
            asB64 = Base64.getEncoder().encodeToString(code.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return asB64;


    }

    public static String decode(String code) {
        byte[] asBytes = Base64.getDecoder().decode(code);
        String decodedString = "";
        try {
            decodedString = new String(asBytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodedString;
    }
}
