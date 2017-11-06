package com.lokey.student.web.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.*;


/**
 * Created by yinhaijin on 15/9/8.
 */
public class JsonUtil {
    public static Map<String, Object> json2map(String str) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        try {
            map = mapper.readValue(str, Map.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }


    public static String map2json(Map<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper();
        String returnStr="";
        try {
            returnStr = mapper.writeValueAsString(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnStr;
    }

    /**
     * 将JSONArray对象转换成list集合
     *
     * @param jsonArr
     * @return
     */
    public static List<Object> jsonToList(JSONArray jsonArr) {
        List<Object> list = new ArrayList<Object>();
        for (Object obj : jsonArr) {
            if (obj instanceof JSONArray) {
                list.add(jsonToList((JSONArray) obj));
            } else if (obj instanceof JSONObject) {
                list.add(jsonToMap((JSONObject) obj));
            } else {
                list.add(obj);
            }
        }
        return list;
    }
    /**
     * 将JSONObject转换成map对象
     *
     * @return
     */
    public static Map<String, Object> jsonToMap(JSONObject obj) {
        Set<?> set = obj.keySet();
        Map<String, Object> map = new HashMap<String, Object>(set.size());
        for (Object key : obj.keySet()) {
            Object value = obj.get(key);
            if (value instanceof JSONArray) {
                map.put(key.toString(), jsonToList((JSONArray) value));
            } else if (value instanceof JSONObject) {
                map.put(key.toString(), jsonToMap((JSONObject) value));
            } else {
                map.put(key.toString(), obj.get(key));
            }

        }
        return map;
    }



}
