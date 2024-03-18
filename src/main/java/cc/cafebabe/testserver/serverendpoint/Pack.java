package cc.cafebabe.testserver.serverendpoint;

import cc.cafebabe.testserver.util.Util;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pack {

    private Map<String, Object> map = new HashMap<String, Object>();

    private Pack(String key) {
        this.map.put("k", key);
    }

    private Pack(String key, Map<String, Object> map) {
        this.map.put("k", key);
        this.map.put("v", map);
    }

    private Pack(String key, List<Map<String, Object>> list) {
        this.map.put("k", key);
        this.map.put("v", list);
    }

    public Pack(String key, JsonNode value) {
        this.map.put("k", key);
        this.map.put("v", value);
    }

    public static Pack buildKeyPack(String key) {
        Pack pack = new Pack(key);
        return pack;
    }

    public static Pack buildKVPack(String key, Map<String, Object> map) {
        Pack pack = new Pack(key, map);
        return pack;
    }

    public static Pack buildKVPack(String key, JsonNode value) {
        return new Pack(key, value);
    }

    public static Pack buildKVPack(String key, List<Map<String, Object>> list) {
        Pack pack = new Pack(key, list);
        return pack;
    }

    public static Pack buildKVPack(String key, Object... values) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < values.length; i += 2) {
            map.put((String) values[i], values[i + 1]);
        }
        Pack pack = new Pack(key, map);
        return pack;
    }


    public String toJSON() {
        return Util.toJsonString(map);
    }
}