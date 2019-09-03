package testcase.testsuite.TestStudy;

import net.sf.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//遍历简单的jsonstr,JSON转Map
public class testJsonToMap {

    public static Map<String, String> getMapFromJson(String jsonString) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Map<String, String> map = new HashMap<String, String>();
        for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
            String key = (String) iter.next();
            String value=jsonObject.getString(key);
            if(org.apache.commons.lang.StringUtils.isNotBlank(value)&&!"null".equalsIgnoreCase(value))
            {
                map.put(key, jsonObject.getString(key));
            }
        }
        return map;
    }

    public static void main(String[] args) {
        String jsonString = "{\"data\":\"test\",\"result\":\"200\",\"codeUrl\":\"url\",\"imageValue\":\"html\"}";
        Map<String, String> map = new HashMap<String, String>();
        map = getMapFromJson(jsonString);
        System.out.print(map.get("data")+",");
        System.out.print(map.get("result")+",");
        System.out.print(map.get("codeUrl")+",");
        System.out.print(map.get("imageValue"));
    }
}