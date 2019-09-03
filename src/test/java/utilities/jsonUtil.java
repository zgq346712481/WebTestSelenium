package utilities;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.io.*;
import java.util.*;


/**
 * 读取json文件，返回json串
 * @return
 */
public class jsonUtil {

    public static String readJsonFile(String filePathName) {
        String jsonStr ="";//预定义返回json字符串
        try {
            File jsonFile = new File(filePathName);//json文件路径名称
//            System.out.println("jsonFile:"+jsonFile);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**依赖为参考：net.sf.json.JSONObject maven依赖
     * java实体,MAP,json之间的转换
     * 将json字符串转为Map结构
     * 如果json复杂，结果可能是map嵌套map
     * @param jsonStr 入参，json格式字符串
     * @return 返回一个map
     */
    public static Map<String, Object> json2Map(String jsonStr) {
        Map<String, Object> map = new HashMap<>();
        if(jsonStr != null && !"".equals(jsonStr)){
            //最外层解析
            JSONObject json = JSONObject.fromObject(jsonStr);
            for (Object k : json.keySet()) {
                Object v = json.get(k);
                //如果内层还是数组的话，继续解析
                if (v instanceof JSONArray) {
                    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                    Iterator<JSONObject> it = ((JSONArray) v).iterator();
                    while (it.hasNext()) {
                        JSONObject json2 = it.next();
                        list.add(json2Map(json2.toString()));
                    }
                    map.put(k.toString(), list);
                } else {
                    map.put(k.toString(), v);
                }
            }
            return map;
        }else{
            return null;
        }
    }

    //main方法测试
    public static void main(String[] args) {
        String jsonFilePath ="E:\\IntelliJ_IDEA_workspace\\WebTestSelenium\\src\\test\\java\\testcase\\testdata\\commonCaseTemp.json";//commonCasetest01
//        String path = jsonUtil.class.getClassLoader().getResource("test.json").getPath();
        String jsonString = jsonUtil.readJsonFile(jsonFilePath);
        Map jsonMap =jsonUtil.json2Map(jsonString);//读取json文件，然后jsonString转为Map

        List<JSONObject> array = (List<JSONObject>) jsonMap.get("CaseSuit");//获取json对象解析为列表,key为数组解析
        for(Object o:array){
            Map subMap = (HashMap) o;//遍历数组列表转为map
            System.out.println("jsonMapArry---->TestCaseID:"+subMap.get("TestCaseID"));
        }

    }

}
